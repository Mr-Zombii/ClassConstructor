package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class TypeParameterTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfo.TypeTargetInfoType> type;
    byte typeParameterIndex;

    public TypeParameterTarget(Pair<Byte, TypeTargetInfo.TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
        typeParameterIndex = reader.consumeU1();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
