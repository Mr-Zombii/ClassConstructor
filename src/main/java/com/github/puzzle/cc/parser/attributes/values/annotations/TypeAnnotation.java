package com.github.puzzle.cc.parser.attributes.values.annotations;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.values.annotations.type_targets.TypeTargetInfo;
import com.github.puzzle.cc.util.Pair;

public class TypeAnnotation {

    byte targetType;
    TypeTargetInfo info;
    TypePath targetPath;
    short typeIndex;
    short elementValuePairsCount;
    Pair<Short, ElementValue>[] valuePairs;

    public TypeAnnotation(ClassReader reader) {
        targetType = reader.consumeU1();

        info = TypeTargetInfo.readTargetInfo(reader);
        targetPath = new TypePath(reader);

        typeIndex = reader.consumeU2();

        elementValuePairsCount = reader.consumeU2();
        valuePairs = new Pair[elementValuePairsCount];
        for (int i = 0; i < elementValuePairsCount; i++) {
            valuePairs[i] = new Pair<>(reader.consumeU2(), new ElementValue(reader));
        }
    }

}
