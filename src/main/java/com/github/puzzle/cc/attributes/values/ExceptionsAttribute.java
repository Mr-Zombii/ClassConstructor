package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class ExceptionsAttribute extends AttributeInfo {

    short numberOfExceptions;
    short[] exceptionIndexTable;

    public ExceptionsAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        numberOfExceptions = reader.consumeU2();
        for (int i = 0; i < numberOfExceptions; i++) {
            exceptionIndexTable[i] = reader.consumeU2();
        }
    }
}
