package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalVariableTypeTableAttribute extends AttributeInfo {

    int localVariableTableLength;
    LocalVariable[] localVariableTypes;

    public LocalVariableTypeTableAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        localVariableTableLength = inp.readUnsignedShort();
        localVariableTypes = new LocalVariable[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTypes[i] = new LocalVariable(inp);
        }
    }

    public static class LocalVariable {

        int startPc;
        int length;
        int nameIndex;
        int descriptorIndex;
        int index;

        public LocalVariable(DataInputStream inp) throws IOException {
            startPc = inp.readUnsignedShort();
            length = inp.readUnsignedShort();
            nameIndex = inp.readUnsignedShort();
            descriptorIndex = inp.readUnsignedShort();
            index = inp.readUnsignedShort();
        }

    }
}
