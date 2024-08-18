package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class EnclosingMethodAttribute extends AttributeInfo {

    int classIndex;
    int methodIndex;

    public EnclosingMethodAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        classIndex = inp.readUnsignedShort();
        methodIndex = inp.readUnsignedShort();
    }

}
