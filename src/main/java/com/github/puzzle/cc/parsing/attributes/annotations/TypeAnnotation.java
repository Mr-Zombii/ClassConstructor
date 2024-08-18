package com.github.puzzle.cc.parsing.attributes.annotations;

import com.github.puzzle.cc.parsing.attributes.annotations.targets.TypeTargetInfo;
import com.github.puzzle.cc.parsing.attributes.annotations.values.ElementValue;
import com.github.puzzle.cc.parsing.attributes.annotations.values.TypePath;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class TypeAnnotation {

    byte targetType;
    TypeTargetInfo info;
    TypePath targetPath;
    int typeIndex;
    int elementValuePairsCount;
    Pair<Integer, ElementValue>[] valuePairs;

    public TypeAnnotation(DataInputStream inp) throws IOException {
        targetType = inp.readByte();

        info = TypeTargetInfo.readTargetInfo(inp);
        targetPath = new TypePath(inp);

        typeIndex = inp.readUnsignedShort();

        elementValuePairsCount = inp.readUnsignedShort();
        valuePairs = new Pair[elementValuePairsCount];
        for (int i = 0; i < elementValuePairsCount; i++) {
            valuePairs[i] = new Pair<>(inp.readUnsignedShort(), new ElementValue(inp));
        }
    }

}
