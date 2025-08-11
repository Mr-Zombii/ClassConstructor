package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.bytecode.BytecodeContainer;
import com.github.puzzle.cc.parsing.bytecode.BytecodeReader;
import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Supplier;

public class CodeAttribute extends AttributeInfo {

    int maxStack;
    int maxLocals;

    short[] code;
    BytecodeContainer codeContainer;

    ExceptionValue[] exceptionTable;

    Attributes attributes;

    ConstantPool pool;

    public CodeAttribute(ConstantPool pool, int nameIndex, int maxStack, int maxLocals, int csize, short[] code, ExceptionValue[] exceptionTable, Attributes attributes) {
        super(nameIndex, ((Supplier<Integer>)() -> {
            int size = 0;
            size += 12;
            size += csize;
            size += (8 * exceptionTable.length);
            size += attributes.size();
            return size;
        }).get(), null);
        this.pool = pool;
        this.nameIndex = nameIndex;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
        this.codeContainer = BytecodeContainer.make(code);
    }

    public CodeAttribute(ConstantPool pool, int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
        this.pool = pool;
        maxStack = inp.readUnsignedShort();
        maxLocals = inp.readUnsignedShort();

        code = new short[inp.readInt()];
        for (int i = 0; i < code.length; i++) {
            code[i] = (short) inp.readUnsignedByte();
        }

        codeContainer = BytecodeContainer.make(code);

        exceptionTable = new ExceptionValue[inp.readUnsignedShort()];
        for (int i = 0; i < exceptionTable.length; i++) {
            exceptionTable[i] = new ExceptionValue(inp);
        }

        attributes = AttributeInfo.readAttributes(pool, inp.readUnsignedShort(), inp);
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);
        outputStream.writeShort(maxStack);
        outputStream.writeShort(maxLocals);
        outputStream.writeInt(code.length);
        for (short bc : code) {
            outputStream.writeByte(bc);
        }
        outputStream.writeShort(exceptionTable.length);
        for (ExceptionValue e : exceptionTable) {
            e.writeToStream(outputStream);
        }
        attributes.writeToStream(outputStream);
    }

    @Override
    public String toString() {
        return "CodeAttribute{" +
                "\nmaxStack=" + maxStack +
                "\nmaxLocals=" + maxLocals +
                "\ncode=" + Arrays.toString(code) +
                "\ncodeContainer=" + codeContainer.asString(pool) +
                "\nexceptionTable=" + Arrays.toString(exceptionTable) +
                "\nattributes=" + attributes +
                '}';
    }

    public short[] getCode() {
        return code;
    }

    public static class ExceptionValue {
        int startPc;
        int endPc;
        int handlerPc;
        int catchType;

        public ExceptionValue() {}

        public void setStartPc(int startPc) {
            this.startPc = startPc;
        }

        public void setEndPc(int endPc) {
            this.endPc = endPc;
        }

        public void setCatchType(int catchType) {
            this.catchType = catchType;
        }

        public void setHandlerPc(int handlerPc) {
            this.handlerPc = handlerPc;
        }

        public ExceptionValue(DataInputStream inp) throws IOException {
            startPc = inp.readUnsignedShort();
            endPc = inp.readUnsignedShort();
            handlerPc = inp.readUnsignedShort();
            catchType = inp.readUnsignedShort();
        }

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(startPc);
            outputStream.writeShort(endPc);
            outputStream.writeShort(handlerPc);
            outputStream.writeShort(catchType);
        }
    }

    @Override
    public String getType() {
        return "Code";
    }

    public Attributes getAttributes() {
        return attributes;
    }
}
