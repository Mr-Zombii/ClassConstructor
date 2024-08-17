package com.github.puzzle.cc.attributes.values.stackmap.frames;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class SameFrameExtended implements StackMapFrame {

    Pair<Byte, StackMapFrameType> type;
    short offsetDelta;

    public SameFrameExtended(Pair<Byte, StackMapFrameType> type, ClassReader reader) {
        this.type = type;
        this.offsetDelta = reader.consumeU2();
    }

    @Override
    public Pair<Byte, StackMapFrameType> getType() {
        return type;
    }

}
