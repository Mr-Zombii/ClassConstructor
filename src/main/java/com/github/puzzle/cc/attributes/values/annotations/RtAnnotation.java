package com.github.puzzle.cc.attributes.values.annotations;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class RtAnnotation {

    short typeIndex;

    short elementValuePairCounts;
    Pair<Short, ElementValue>[] valuePairs;

    public RtAnnotation(ClassReader reader) {
        typeIndex = reader.consumeU2();
        elementValuePairCounts = reader.consumeU2();
        valuePairs = new Pair[elementValuePairCounts];
        for (int i = 0; i < valuePairs.length; i++) {
            valuePairs[i] = new Pair<>(reader.consumeU2(), new ElementValue(reader));
        }
    }

}
