package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ModulePackagesAttribute extends AttributeInfo {

    int[] packagesIndex;

    public ModulePackagesAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        packagesIndex = new int[inp.readUnsignedShort()];
        for (int i = 0; i < packagesIndex.length; i++) {
            packagesIndex[i] = inp.readUnsignedShort();
        }

    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(packagesIndex.length);
        for (int i : packagesIndex) outputStream.writeShort(i);
    }
}
