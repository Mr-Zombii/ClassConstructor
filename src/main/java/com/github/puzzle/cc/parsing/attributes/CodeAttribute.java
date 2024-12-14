package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.function.Supplier;

public class CodeAttribute extends AttributeInfo {

    int maxStack;
    int maxLocals;

    byte[] code;

    ExceptionValue[] exceptionTable;

    Attributes attributes;

    public CodeAttribute(int nameIndex, int maxStack, int maxLocals, byte[] code, ExceptionValue[] exceptionTable, Attributes attributes) {
        super(nameIndex, ((Supplier<Integer>)() -> {
            int size = 0;
            size += 12;
            size += code.length;
            size += (8 * exceptionTable.length);
            size += attributes.size();
            return size;
        }).get(), null);
        this.nameIndex = nameIndex;
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;
        this.code = code;
        this.exceptionTable = exceptionTable;
        this.attributes = attributes;
    }

    public CodeAttribute(ConstantPool pool, int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
        maxStack = inp.readUnsignedShort();
        maxLocals = inp.readUnsignedShort();

        code = new byte[inp.readInt()];
        for (int i = 0; i < code.length; i++) {
            code[i] = inp.readByte();
        }

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
        for (byte bc : code) {
            outputStream.writeByte(bc);
        }
        outputStream.writeShort(exceptionTable.length);
        for (ExceptionValue e : exceptionTable) {
            e.writeToStream(outputStream);
        }
        attributes.writeToStream(outputStream);
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
}
