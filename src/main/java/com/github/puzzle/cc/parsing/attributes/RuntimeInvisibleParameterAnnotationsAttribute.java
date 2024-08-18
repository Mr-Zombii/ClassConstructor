package com.github.puzzle.cc.parsing.attributes;
import java.io.DataInputStream;
import java.io.IOException;

import com.github.puzzle.cc.parsing.attributes.annotations.RtAnnotation;
import com.github.puzzle.cc.util.Pair;

public class RuntimeInvisibleParameterAnnotationsAttribute extends AttributeInfo {

    int parameterCount;
    Pair<Integer, RtAnnotation[]>[] annotationPairs;

    public RuntimeInvisibleParameterAnnotationsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        parameterCount = inp.readUnsignedByte();
        annotationPairs = new Pair[parameterCount];

        for (int i = 0; i < parameterCount; i++) {
            int annotationCount = inp.readUnsignedShort();
            RtAnnotation[] values = new RtAnnotation[annotationCount];
            for (int j = 0; j < annotationCount; j++) {
                values[j] = new RtAnnotation(inp);
            }
            annotationPairs[i] = new Pair<>(annotationCount, values);
        }
    }
    
}
