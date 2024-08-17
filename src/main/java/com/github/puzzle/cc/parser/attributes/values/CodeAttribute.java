package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class CodeAttribute extends AttributeInfo {

    short maxStack;
    short maxLocals;

    int codeLength;
    byte[] code;

    short exceptionTableLength;
    ExceptionValue[] exceptionTable;

    short attributesCount;
    AttributeInfo[] attributes;

    public CodeAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
        maxStack = reader.consumeU2();
        maxLocals = reader.consumeU2();

        codeLength = reader.consumeU4();
        code = new byte[codeLength];
        for (int i = 0; i < codeLength; i++) {
            code[i] = reader.consumeU1();
        }

        exceptionTableLength = reader.consumeU2();
        exceptionTable = new ExceptionValue[exceptionTableLength];
        for (int i = 0; i < exceptionTableLength; i++) {
            exceptionTable[i] = new ExceptionValue(reader);
        }

        attributesCount = reader.consumeU2();
        attributes = AttributeInfo.readAttributes(attributesCount, reader);
    }

    public static class ExceptionValue {
        short startPc;
        short endPc;
        short handlerPc;
        short catchType;

        public ExceptionValue(ClassReader reader) {
            startPc = reader.consumeU2();
            endPc = reader.consumeU2();
            handlerPc = reader.consumeU2();
            catchType = reader.consumeU2();
        }
    }
}
