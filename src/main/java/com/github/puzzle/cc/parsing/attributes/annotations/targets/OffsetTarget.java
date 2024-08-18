package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class OffsetTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    int offset;

    public OffsetTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;
        offset = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(offset);
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
