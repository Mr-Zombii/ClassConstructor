package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class LocalVarTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    int tableLength;
    LocalVar[] table;

    public LocalVarTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;

        tableLength = inp.readUnsignedShort();
        table = new LocalVar[tableLength];
        for (int i = 0; i < tableLength; i++) {
            table[i] = new LocalVar(inp);
        }
    }

    public static class LocalVar {
        int startPc;
        int length;
        int index;

        public LocalVar(DataInputStream inp) throws IOException {
            startPc = inp.readUnsignedShort();
            length = inp.readUnsignedShort();
            index = inp.readUnsignedShort();
        }
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
