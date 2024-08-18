package com.github.puzzle.cc.parsing.attributes.annotations;

import com.github.puzzle.cc.parsing.attributes.annotations.targets.TypeTargetInfo;
import com.github.puzzle.cc.parsing.attributes.annotations.values.ElementValue;
import com.github.puzzle.cc.parsing.attributes.annotations.values.TypePath;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TypeAnnotation {

    byte targetType;
    TypeTargetInfo info;
    TypePath targetPath;
    int typeIndex;
    Pair<Integer, ElementValue>[] valuePairs;

    public TypeAnnotation(DataInputStream inp) throws IOException {
        targetType = inp.readByte();

        info = TypeTargetInfo.readTargetInfo(inp);
        targetPath = new TypePath(inp);

        typeIndex = inp.readUnsignedShort();

        valuePairs = new Pair[inp.readUnsignedShort()];
        for (int i = 0; i < valuePairs.length; i++) {
            valuePairs[i] = new Pair<>(inp.readUnsignedShort(), new ElementValue(inp));
        }
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeByte(targetType);
        stream.writeByte(info.getType().ordinal() + 1);
        info.writeToStream(stream);
        targetPath.writeToStream(stream);
        stream.writeShort(typeIndex);
        stream.writeShort(valuePairs.length);
        for (Pair<Integer, ElementValue> valuePair : valuePairs) {
            stream.writeShort(valuePair.a);
            valuePair.b.writeToStream(stream);
        }
    }

}
