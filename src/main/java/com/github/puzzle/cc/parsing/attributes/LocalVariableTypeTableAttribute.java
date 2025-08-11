package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Collection;

public class LocalVariableTypeTableAttribute extends AttributeInfo {

    LocalVariableType[] localVariableTypes;

    public LocalVariableTypeTableAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        localVariableTypes = new LocalVariableType[inp.readUnsignedShort()];
        for (int i = 0; i < localVariableTypes.length; i++) {
            localVariableTypes[i] = new LocalVariableType(inp);
        }
    }

    public LocalVariableTypeTableAttribute(int nameIdx, int length, Collection<LocalVariableType> localVariableTypes) {
        super(nameIdx, length, null);

        this.localVariableTypes = localVariableTypes.toArray(new LocalVariableType[0]);
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(localVariableTypes.length);
        for (LocalVariableType localVariableType : localVariableTypes) {
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

        public LocalVariableType(int startPc, int length, int nameIndex, int signatureIndex, int index) {
            this.startPc = startPc;
            this.length = length;
            this.nameIndex = nameIndex;
            this.signatureIndex = signatureIndex;
            this.index = index;
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
