package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;
import com.github.puzzle.cc.attributes.values.annotations.RtAnnotation;

public class RuntimeVisibleAnnotationsAttribute extends AttributeInfo {

    short annotationCount;
    RtAnnotation[] annotations;

    public RuntimeVisibleAnnotationsAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        annotationCount = reader.consumeU2();
        annotations = new RtAnnotation[annotationCount];
        for (int i = 0; i < annotationCount; i++) {
            annotations[i] = new RtAnnotation(reader);
        }
    }
    
}
