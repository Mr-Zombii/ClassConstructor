package com.github.puzzle.cc.attributes.values.stackmap.frames;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.values.stackmap.verification_types.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

public class FullFrame implements StackMapFrame {

    Pair<Byte, StackMapFrameType> type;
    short offsetDelta;

    short numberOfLocals;
    VerificationInfo[] locals;

    short numberOfStackItems;
    VerificationInfo[] stack;

    public FullFrame(Pair<Byte, StackMapFrameType> type, ClassReader reader) {
        this.type = type;
        offsetDelta = reader.consumeU2();

        numberOfLocals = reader.consumeU2();
        for (int i = 0; i < numberOfLocals; i++) {
            locals[i] = VerificationInfo.readType(reader);
        }

        numberOfStackItems = reader.consumeU2();
        for (int i = 0; i < numberOfStackItems; i++) {
            stack[i] = VerificationInfo.readType(reader);
        }
    }

    @Override
    public Pair<Byte, StackMapFrameType> getType() {
        return type;
    }

}
