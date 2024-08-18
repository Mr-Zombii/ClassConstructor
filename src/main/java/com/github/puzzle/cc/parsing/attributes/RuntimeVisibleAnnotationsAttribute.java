package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.annotations.RtAnnotation;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeVisibleAnnotationsAttribute extends AttributeInfo {

    int annotationCount;
    RtAnnotation[] annotations;

    public RuntimeVisibleAnnotationsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        annotationCount = inp.readUnsignedShort();
        annotations = new RtAnnotation[annotationCount];
        for (int i = 0; i < annotationCount; i++) {
            annotations[i] = new RtAnnotation(inp);
        }
    }
    
}
