package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class CodeAttribute extends AttributeInfo {

    int maxStack;
    int maxLocals;

    int codeLength;
    byte[] code;

    int exceptionTableLength;
    ExceptionValue[] exceptionTable;

    int attributesCount;
    Attributes attributes;

    public CodeAttribute(ConstantPool pool, int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
        maxStack = inp.readUnsignedShort();
        maxLocals = inp.readUnsignedShort();

        codeLength = inp.readInt();
        code = new byte[codeLength];
        for (int i = 0; i < codeLength; i++) {
            code[i] = inp.readByte();
        }

        exceptionTableLength = inp.readUnsignedShort();
        exceptionTable = new ExceptionValue[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionValue(inp);
        }

        attributesCount = inp.readUnsignedShort();
        attributes = AttributeInfo.readAttributes(pool, attributesCount, inp);
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
    }
}
