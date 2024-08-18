package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.stackmap.frames.StackMapFrame;

import java.io.DataInputStream;
import java.io.IOException;

public class StackMapAttribute extends AttributeInfo {

    int numberOfEntries;
    StackMapFrame[] frames;

    public StackMapAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
        numberOfEntries = inp.readUnsignedShort();
        frames = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            frames[i] = StackMapFrame.readFrame(inp);
        }
    }

}
