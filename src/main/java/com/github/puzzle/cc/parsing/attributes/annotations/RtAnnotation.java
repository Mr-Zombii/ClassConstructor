package com.github.puzzle.cc.parsing.attributes.annotations;

import com.github.puzzle.cc.parsing.attributes.annotations.values.ElementValue;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class RtAnnotation {

    int typeIndex;

    Pair<Integer, ElementValue>[] valuePairs;

    public RtAnnotation(DataInputStream inp) throws IOException {
        typeIndex = inp.readUnsignedShort();
        valuePairs = new Pair[inp.readUnsignedShort()];
        for (int i = 0; i < valuePairs.length; i++) {
            valuePairs[i] = new Pair<>(inp.readUnsignedShort(), new ElementValue(inp));
        }
    }

}
