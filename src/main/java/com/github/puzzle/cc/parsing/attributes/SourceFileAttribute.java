package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class SourceFileAttribute extends AttributeInfo {

    int sourcefileAttribute;

    public SourceFileAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        sourcefileAttribute = inp.readUnsignedShort();
    }
}
