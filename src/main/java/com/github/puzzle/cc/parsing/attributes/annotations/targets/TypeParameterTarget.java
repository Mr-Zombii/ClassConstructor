package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TypeParameterTarget implements TypeTargetInfo {

    Pair<Byte, TypeTargetInfoType> type;
    byte typeParameterIndex;

    public TypeParameterTarget(Pair<Byte, TypeTargetInfoType> type, DataInputStream inp) throws IOException {
        this.type = type;
        typeParameterIndex = inp.readByte();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(typeParameterIndex);
    }

    @Override
    public TypeTargetInfoType getType() {
        return type.b;
    }
}
