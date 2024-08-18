package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class CodeAttribute extends AttributeInfo {

    int maxStack;
    int maxLocals;

    byte[] code;

    ExceptionValue[] exceptionTable;

    Attributes attributes;

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
