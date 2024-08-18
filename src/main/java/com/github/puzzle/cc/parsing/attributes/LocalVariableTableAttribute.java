package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LocalVariableTableAttribute extends AttributeInfo {

    LocalVariableType[] localVariables;

    public LocalVariableTableAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        localVariables = new LocalVariableType[inp.readUnsignedShort()];
        for (int i = 0; i < localVariables.length; i++) {
            localVariables[i] = new LocalVariableType(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(localVariables.length);
        for (LocalVariableType localVariableType : localVariables) {
            localVariableType.writeToStream(outputStream);
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

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(startPc);
            outputStream.writeShort(length);
            outputStream.writeShort(nameIndex);
            outputStream.writeShort(signatureIndex);
            outputStream.writeShort(index);
        }

    }
}
