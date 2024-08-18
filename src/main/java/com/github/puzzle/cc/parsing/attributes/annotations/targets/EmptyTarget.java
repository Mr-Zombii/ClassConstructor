package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;

public class EmptyTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;

    public EmptyTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) {
        this.type = type;
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
