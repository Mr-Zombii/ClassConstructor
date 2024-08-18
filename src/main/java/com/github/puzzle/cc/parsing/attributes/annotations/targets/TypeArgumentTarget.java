package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TypeArgumentTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    int offset;
    byte typeArgumentIndex;

    public TypeArgumentTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;
        offset = inp.readUnsignedShort();
        typeArgumentIndex = inp.readByte();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(offset);
        outputStream.writeByte(typeArgumentIndex);
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
