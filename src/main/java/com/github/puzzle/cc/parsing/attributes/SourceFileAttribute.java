package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SourceFileAttribute extends AttributeInfo {

    int sourcefileIndex;

    public SourceFileAttribute(int nameIndex, int utf8FileIndex) {
        super(nameIndex, 2, null);

        sourcefileIndex = utf8FileIndex;
    }

    public SourceFileAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        sourcefileIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);
        outputStream.writeShort(sourcefileIndex);
    }

    @Override
    public String getType() {
        return "SourceFile";
    }
}
