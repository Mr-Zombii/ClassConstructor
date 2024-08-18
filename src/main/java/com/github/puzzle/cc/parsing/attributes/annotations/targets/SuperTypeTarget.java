package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class SuperTypeTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    int superTypeIndex;

    public SuperTypeTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;
        superTypeIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(superTypeIndex);
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
