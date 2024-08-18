package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class SyntheticAttribute extends AttributeInfo {

    public SyntheticAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
    }

}
