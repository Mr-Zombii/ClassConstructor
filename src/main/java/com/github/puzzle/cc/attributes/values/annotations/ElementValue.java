package com.github.puzzle.cc.attributes.values.annotations;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class ElementValue {

    byte tag;
    Value value;

    public ElementValue(ClassReader reader) {
        tag = reader.consumeU1();
        value = new Value(reader);
    }

    public static class Value {

        short valueIndex;
        Pair<Short, Short> enumConstValue;

        short classInfoIndex;

        RtAnnotation annotationValue;
        Pair<Short, ElementValue[]> arrayValue;

        public Value(ClassReader reader) {
            valueIndex = reader.consumeU2();

            enumConstValue = new Pair<>(reader.consumeU2(), reader.consumeU2());
            classInfoIndex = reader.consumeU2();
            annotationValue = new RtAnnotation(reader);

            short valuesCount = reader.consumeU2();
            ElementValue[] values = new ElementValue[valuesCount];
            for (int i = 0; i < valuesCount; i++) {
                values[i] = new ElementValue(reader);
            }
            arrayValue = new Pair<>(valuesCount, values);
        }

    }

}
