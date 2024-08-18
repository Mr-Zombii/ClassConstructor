package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class ThrowsTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    int throwsTypeIndex;

    public ThrowsTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;
        throwsTypeIndex = inp.readUnsignedShort();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
