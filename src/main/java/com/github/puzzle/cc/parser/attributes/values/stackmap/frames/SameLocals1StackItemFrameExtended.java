package com.github.puzzle.cc.parser.attributes.values.stackmap.frames;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.values.stackmap.verification_types.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

public class SameLocals1StackItemFrameExtended implements StackMapFrame {

    Pair<Byte, StackMapFrameType> type;
    short offsetDelta;
    VerificationInfo[] stack;

    public SameLocals1StackItemFrameExtended(Pair<Byte, StackMapFrameType> type, ClassReader reader) {
        this.type = type;
        offsetDelta = reader.consumeU2();

        stack = new VerificationInfo[1];
        stack[0] = VerificationInfo.readType(reader);
    }

    @Override
    public Pair<Byte, StackMapFrameType> getType() {
        return type;
    }
}
