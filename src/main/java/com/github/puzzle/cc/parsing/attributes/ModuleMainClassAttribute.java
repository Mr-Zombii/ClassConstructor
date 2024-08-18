package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ModuleMainClassAttribute extends AttributeInfo {

    int mainClassIndex;

    public ModuleMainClassAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        mainClassIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(mainClassIndex);
    }
}
