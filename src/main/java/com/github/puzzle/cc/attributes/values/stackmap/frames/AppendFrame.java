package com.github.puzzle.cc.attributes.values.stackmap.frames;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.values.stackmap.verification_types.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

public class AppendFrame implements StackMapFrame {

    Pair<Byte, StackMapFrameType> type;
    short offsetDelta;
    VerificationInfo[] locals;

    public AppendFrame(Pair<Byte, StackMapFrameType> type, ClassReader reader) {
        this.type = type;
        offsetDelta = reader.consumeU2();
        locals = new VerificationInfo[((int)type.a) - 251];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = VerificationInfo.readType(reader);
        }
    }

    @Override
    public Pair<Byte, StackMapFrameType> getType() {
        return type;
    }

}
