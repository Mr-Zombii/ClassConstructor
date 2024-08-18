package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class NestMembersAttribute extends AttributeInfo {

    int numberOfClasses;
    int[] classes;

    public NestMembersAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        numberOfClasses = inp.readUnsignedShort();
        classes = new int[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = inp.readUnsignedShort();
        }

    }
}
