package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class SuperTypeTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfo.TypeTargetInfoType> type;
    short superTypeIndex;

    public SuperTypeTarget(Pair<Byte, TypeTargetInfo.TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
        superTypeIndex = reader.consumeU2();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
