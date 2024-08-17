package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class EmptyTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;

    public EmptyTarget(Pair<Byte, TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
