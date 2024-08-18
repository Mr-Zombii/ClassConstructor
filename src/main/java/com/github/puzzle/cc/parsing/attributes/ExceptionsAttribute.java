package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class ExceptionsAttribute extends AttributeInfo {

    int[] exceptionIndexTable;

    public ExceptionsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        exceptionIndexTable = new int[inp.readUnsignedShort()];
        for (int i = 0; i < exceptionIndexTable.length; i++) {
            exceptionIndexTable[i] = inp.readUnsignedShort();
        }
    }
}
