package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class ModulePackagesAttribute extends AttributeInfo {

    int packagesCount;
    int[] packagesIndex;

    public ModulePackagesAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        packagesCount = inp.readUnsignedShort();
        packagesIndex = new int[packagesCount];
        for (int i = 0; i < packagesCount; i++) {
            packagesIndex[i] = inp.readUnsignedShort();
        }

    }
}
