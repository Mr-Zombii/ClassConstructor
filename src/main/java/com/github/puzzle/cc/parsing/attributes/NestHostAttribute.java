package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class NestHostAttribute extends AttributeInfo {

    int hostClassIndex;

    public NestHostAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        hostClassIndex = inp.readUnsignedShort();
    }
}
