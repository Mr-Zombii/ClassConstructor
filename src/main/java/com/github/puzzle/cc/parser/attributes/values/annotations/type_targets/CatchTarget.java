package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class CatchTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    short exceptionTableIndex;

    public CatchTarget(Pair<Byte, TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
        exceptionTableIndex = reader.consumeU2();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
