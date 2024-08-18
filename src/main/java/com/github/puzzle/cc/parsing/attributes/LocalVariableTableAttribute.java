package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalVariableTableAttribute extends AttributeInfo {

    int localVariableTypeTableLength;
    LocalVariableType[] localVariables;

    public LocalVariableTableAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        localVariableTypeTableLength = inp.readUnsignedShort();
        localVariables = new LocalVariableType[localVariableTypeTableLength];
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            localVariables[i] = new LocalVariableType(inp);
        }
    }

    public static class LocalVariableType {

        int startPc;
        int length;
        int nameIndex;
        int signatureIndex;
        int index;

        public LocalVariableType(DataInputStream inp) throws IOException {
            startPc = inp.readUnsignedShort();
            length = inp.readUnsignedShort();
            nameIndex = inp.readUnsignedShort();
            signatureIndex = inp.readUnsignedShort();
            index = inp.readUnsignedShort();
        }

    }
}
