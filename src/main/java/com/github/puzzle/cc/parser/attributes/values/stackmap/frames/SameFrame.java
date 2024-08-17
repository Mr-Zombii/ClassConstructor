package com.github.puzzle.cc.parser.attributes.values.stackmap.frames;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class SameFrame implements StackMapFrame {

    Pair<Byte, StackMapFrameType> type;

    public SameFrame(Pair<Byte, StackMapFrameType> type, ClassReader reader) {
        this.type = type;
    }

    @Override
    public Pair<Byte, StackMapFrameType> getType() {
        return type;
    }

}
