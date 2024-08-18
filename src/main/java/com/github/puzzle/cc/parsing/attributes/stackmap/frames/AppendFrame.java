package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.parsing.attributes.stackmap.verification.VerificationInfo;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AppendFrame implements StackMapFrame {

    Pair<Integer, StackMapFrameType> type;
    int offsetDelta;
    VerificationInfo[] locals;

    public AppendFrame(Pair<Integer, StackMapFrameType> type, DataInputStream inp) throws IOException {
        this.type = type;
        offsetDelta = inp.readUnsignedShort();
        locals = new VerificationInfo[type.a - 251];
        for (int i = 0; i < locals.length; i++) {
            locals[i] = VerificationInfo.readType(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(type.a);
        outputStream.writeShort(offsetDelta);
        for (VerificationInfo info : locals) {
            info.writeToStream(outputStream);
        }
    }

    @Override
    public Pair<Integer, StackMapFrameType> getType() {
        return type;
    }

}
