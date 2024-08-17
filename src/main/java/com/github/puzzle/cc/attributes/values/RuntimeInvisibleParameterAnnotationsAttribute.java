package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;
import com.github.puzzle.cc.attributes.values.annotations.RtAnnotation;
import com.github.puzzle.cc.util.Pair;

public class RuntimeInvisibleParameterAnnotationsAttribute extends AttributeInfo {

    short parameterCount;
    Pair<Short, RtAnnotation[]>[] annotationPairs;

    public RuntimeInvisibleParameterAnnotationsAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        parameterCount = reader.consumeU2();
        annotationPairs = new Pair[parameterCount];

        for (int i = 0; i < parameterCount; i++) {
            short annotationCount = reader.consumeU2();
            RtAnnotation[] values = new RtAnnotation[annotationCount];
            for (int j = 0; j < annotationCount; j++) {
                values[j] = new RtAnnotation(reader);
            }
            annotationPairs[i] = new Pair<>(annotationCount, values);
        }
    }
    
}
