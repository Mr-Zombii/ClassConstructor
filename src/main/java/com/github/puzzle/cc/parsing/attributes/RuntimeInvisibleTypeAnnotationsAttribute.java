package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.annotations.TypeAnnotation;

import java.io.DataInputStream;
import java.io.IOException;

public class RuntimeInvisibleTypeAnnotationsAttribute extends AttributeInfo {

    int annotationCount;
    TypeAnnotation[] annotations;

    public RuntimeInvisibleTypeAnnotationsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        annotationCount = inp.readUnsignedShort();
        annotations = new TypeAnnotation[annotationCount];
        for (int i = 0; i < annotationCount; i++) {
            annotations[i] = new TypeAnnotation(inp);
        }
    }
    
}
