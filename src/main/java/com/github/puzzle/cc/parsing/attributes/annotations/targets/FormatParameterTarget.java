package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class FormatParameterTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    byte formalParameterTarget;

    public FormatParameterTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;
        formalParameterTarget = inp.readByte();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
