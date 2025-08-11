package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

public class LocalVariableTableAttribute extends AttributeInfo {

    LocalVariableType[] localVariables;

    public LocalVariableTableAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        localVariables = new LocalVariableType[inp.readUnsignedShort()];
        for (int i = 0; i < localVariables.length; i++) {
            localVariables[i] = new LocalVariableType(inp);
        }
    }

    public LocalVariableTableAttribute(int nameIdx, int length, Collection<LocalVariableTableAttribute.LocalVariableType> localVariables) {
        super(nameIdx, length, null);

        this.localVariables = localVariables.toArray(new LocalVariableType[0]);
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
        int descriptorIndex;
        int index;


        public LocalVariableType(int startPc, int length, int nameIndex, int descriptorIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.descriptorIndex = descriptorIndex;
            this.index = index;
        }

        public LocalVariableType(DataInputStream inp) throws IOException {
            startPc = inp.readUnsignedShort();
            length = inp.readUnsignedShort();
            nameIndex = inp.readUnsignedShort();
            descriptorIndex = inp.readUnsignedShort();
            index = inp.readUnsignedShort();
        }

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(startPc);
            outputStream.writeShort(length);
            outputStream.writeShort(nameIndex);
            outputStream.writeShort(descriptorIndex);
            outputStream.writeShort(index);
        }

    }
}
