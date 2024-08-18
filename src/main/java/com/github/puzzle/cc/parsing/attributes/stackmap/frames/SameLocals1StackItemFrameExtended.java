package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.parsing.attributes.stackmap.verification.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SameLocals1StackItemFrameExtended implements StackMapFrame {

    Pair<Integer, StackMapFrameType> type;
    int offsetDelta;
    VerificationInfo[] stack;

    public SameLocals1StackItemFrameExtended(Pair<Integer, StackMapFrameType> type, DataInputStream inp) throws IOException {
        this.type = type;
        offsetDelta = inp.readUnsignedShort();

        stack = new VerificationInfo[1];
        stack[0] = VerificationInfo.readType(inp);
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(type.a);
        outputStream.writeShort(offsetDelta);
        stack[0].writeToStream(outputStream);
//        for (VerificationInfo info : stack) {
//            info.writeToStream(outputStream);
//        }
    }

    @Override
    public Pair<Integer, StackMapFrameType> getType() {
        return type;
    }
}
