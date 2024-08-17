package com.github.puzzle.cc.attributes.values.stackmap.frames;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class ChopFrame implements StackMapFrame {

    Pair<Byte, StackMapFrameType> type;
    short offsetDelta;

    public ChopFrame(Pair<Byte, StackMapFrameType> type, ClassReader reader) {
        this.type = type;
        offsetDelta = reader.consumeU2();
    }

    @Override
    public Pair<Byte, StackMapFrameType> getType() {
        return type;
    }

}
