package com.github.puzzle.cc.parsing.attributes.annotations.values;

import com.github.puzzle.cc.parsing.attributes.annotations.RtAnnotation;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class ElementValue {

    char tag;
    Value value;

    public ElementValue(DataInputStream inp) throws IOException {
        tag = (char) inp.readByte();
        value = new Value(tag, inp);
    }

    public static class Value {

        int valueIndex;
        Pair<Integer, Integer> enumConstValue;

        int classInfoIndex;

        RtAnnotation annotationValue;
        Pair<Integer, ElementValue[]> arrayValue;

        public Value(char tag, DataInputStream inp) throws IOException {
            switch (tag) {
                case 'B', 'C', 'D', 'F', 'I', 'J', 'K', 'S', 'Z', 's':
                    valueIndex = inp.readUnsignedShort();
                case 'e':
                    enumConstValue = new Pair<>(inp.readUnsignedShort(), inp.readUnsignedShort());
                case 'c':
                    classInfoIndex = inp.readUnsignedShort();
                case '@':
                    annotationValue = new RtAnnotation(inp);
                case '[':
                    int valuesCount = inp.readUnsignedShort();
                    ElementValue[] values = new ElementValue[valuesCount];
                    for (int i = 0; i < valuesCount; i++) {
                        values[i] = new ElementValue(inp);
                    }
                    arrayValue = new Pair<>(valuesCount, values);
            }

        }

    }

}
