package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LocalVarTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    LocalVar[] table;

    public LocalVarTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;

        table = new LocalVar[inp.readUnsignedShort()];
        for (int i = 0; i < table.length; i++) {
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

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeByte(startPc);
            outputStream.writeByte(length);
            outputStream.writeByte(index);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(table.length);
        for (LocalVar var : table) {
            var.writeToStream(outputStream);
        }
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
