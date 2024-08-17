package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class TypeArgumentTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    short offset;
    byte typeArgumentIndex;

    public TypeArgumentTarget(Pair<Byte, TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
        offset = reader.consumeU2();
        typeArgumentIndex = reader.consumeU1();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
