package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class PermittedSubclassesAttribute extends AttributeInfo {

    int[] classes;

    public PermittedSubclassesAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        classes = new int[inp.readUnsignedShort()];
        for (int i = 0; i < classes.length; i++) {
            classes[i] = inp.readUnsignedShort();
        }

    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(classes.length);
        for (int i : classes) outputStream.writeShort(i);
    }
}
