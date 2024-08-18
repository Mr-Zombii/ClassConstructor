package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.parsing.attributes.stackmap.verification.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
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
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(type.a);
        outputStream.writeShort(offsetDelta);

        outputStream.writeShort(locals.length);
        for (VerificationInfo info : locals) {
            info.writeToStream(outputStream);
        }
        outputStream.writeShort(stack.length);
        for (VerificationInfo info : stack) {
            info.writeToStream(outputStream);
        }
    }

    @Override
    public Pair<Integer, StackMapFrameType> getType() {
        return type;
    }

}
