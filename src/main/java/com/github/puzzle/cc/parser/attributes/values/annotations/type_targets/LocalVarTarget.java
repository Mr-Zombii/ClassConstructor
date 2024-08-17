package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class LocalVarTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    short tableLength;
    LocalVar[] table;

    public LocalVarTarget(Pair<Byte, TypeTargetInfoType> type, ClassReader reader) {
        this.type = type;

        tableLength = reader.consumeU2();
        table = new LocalVar[tableLength];
        for (int i = 0; i < tableLength; i++) {
            table[i] = new LocalVar(reader);
        }
    }

    public static class LocalVar {
        short startPc;
        short length;
        short index;

        public LocalVar(ClassReader reader) {
            startPc = reader.consumeU2();
            length = reader.consumeU2();
            index = reader.consumeU2();
        }
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
