package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.parsing.attributes.stackmap.verification.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class FullFrame implements StackMapFrame {

    Pair<Integer, StackMapFrameType> type;
    int offsetDelta;

    VerificationInfo[] locals;

    VerificationInfo[] stack;

    public FullFrame(Pair<Integer, StackMapFrameType> type, DataInputStream inp) throws IOException {
        this.type = type;
        offsetDelta = inp.readUnsignedShort();

        locals = new VerificationInfo[inp.readUnsignedShort()];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = VerificationInfo.readType(inp);
        }

        stack = new VerificationInfo[inp.readUnsignedShort()];
        for (int i = 0; i < stack.length; i++) {
            stack[i] = VerificationInfo.readType(inp);
        }
    }

    @Override
    public Pair<Integer, StackMapFrameType> getType() {
        return type;
    }

}
