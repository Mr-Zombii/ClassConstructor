package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class SameFrameExtended implements StackMapFrame {

    Pair<Integer, StackMapFrameType> type;
    int offsetDelta;

    public SameFrameExtended(Pair<Integer, StackMapFrameType> type, DataInputStream inp) throws IOException {
        this.type = type;
        this.offsetDelta = inp.readUnsignedShort();
    }

    @Override
    public Pair<Integer, StackMapFrameType> getType() {
        return type;
    }

}
