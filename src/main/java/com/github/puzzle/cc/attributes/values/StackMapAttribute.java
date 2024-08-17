package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;
import com.github.puzzle.cc.attributes.values.stackmap.frames.StackMapFrame;

public class StackMapAttribute extends AttributeInfo {

    short numberOfEntries;
    StackMapFrame[] frames;

    public StackMapAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
        numberOfEntries = reader.consumeU2();
        frames = new StackMapFrame[numberOfEntries];
        for (int i = 0; i < numberOfEntries; i++) {
            frames[i] = StackMapFrame.readFrame(reader);
        }
    }

}
