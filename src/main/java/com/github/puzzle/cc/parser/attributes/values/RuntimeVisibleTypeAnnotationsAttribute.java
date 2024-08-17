package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;
import com.github.puzzle.cc.parser.attributes.values.annotations.TypeAnnotation;

public class RuntimeVisibleTypeAnnotationsAttribute extends AttributeInfo {

    short annotationCount;
    TypeAnnotation[] annotations;

    public RuntimeVisibleTypeAnnotationsAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        annotationCount = reader.consumeU2();
        annotations = new TypeAnnotation[annotationCount];
        for (int i = 0; i < annotationCount; i++) {
            annotations[i] = new TypeAnnotation(reader);
        }
    }
    
}
