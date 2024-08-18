package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class ConstantAttribute extends AttributeInfo {

    int valueIndex;

    public ConstantAttribute(int nameIndex, int valueIndex) {
        super(nameIndex, 2, null);

        this.valueIndex = valueIndex;
    }

    public ConstantAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
        valueIndex = inp.readUnsignedShort();
    }

    @Override
    public String getType() {
        return "ConstantValue";
    }
}
