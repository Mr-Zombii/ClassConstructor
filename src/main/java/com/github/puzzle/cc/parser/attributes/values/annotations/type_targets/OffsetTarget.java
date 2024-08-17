package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class OffsetTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    short offset;

    public OffsetTarget(Pair<Byte, TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
        offset = reader.consumeU2();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
