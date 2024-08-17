package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class TypeParameterBoundTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfo.TypeTargetInfoType> type;
    byte typeParameterIndex;
    byte boundIndex;

    public TypeParameterBoundTarget(Pair<Byte, TypeTargetInfo.TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
        typeParameterIndex = reader.consumeU1();
        boundIndex = reader.consumeU1();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
