package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ConstantValueAttribute extends AttributeInfo {

    int valueIndex;

    public ConstantValueAttribute(int nameIndex, int valueIndex) {
        super(nameIndex, 2, null);

        this.valueIndex = valueIndex;
    }

    public ConstantValueAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
        valueIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);
        outputStream.writeShort(valueIndex);
    }

    @Override
    public String getType() {
        return "ConstantValue";
    }
}
