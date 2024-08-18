package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class PermittedSubclassesAttribute extends AttributeInfo {

    int numberOfClasses;
    int[] classes;

    public PermittedSubclassesAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        numberOfClasses = inp.readUnsignedShort();
        classes = new int[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = inp.readUnsignedShort();
        }

    }
}
