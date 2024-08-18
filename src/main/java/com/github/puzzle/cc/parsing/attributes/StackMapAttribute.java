package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.stackmap.frames.StackMapFrame;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StackMapAttribute extends AttributeInfo {

    StackMapFrame[] frames;

    public StackMapAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        frames = new StackMapFrame[inp.readUnsignedShort()];
        for (int i = 0; i < frames.length; i++) {
            frames[i] = StackMapFrame.readFrame(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(frames.length);
        for (StackMapFrame frame : frames) frame.writeToStream(outputStream);
    }
}
