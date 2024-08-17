package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class FormatParameterTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    byte formalParameterTarget;

    public FormatParameterTarget(Pair<Byte, TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;
        formalParameterTarget = reader.consumeU1();
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
