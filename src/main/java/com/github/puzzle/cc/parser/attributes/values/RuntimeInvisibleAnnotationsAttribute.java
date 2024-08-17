package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;
import com.github.puzzle.cc.parser.attributes.values.annotations.RtAnnotation;

public class RuntimeInvisibleAnnotationsAttribute extends AttributeInfo {

    short annotationCount;
    RtAnnotation[] annotations;

    public RuntimeInvisibleAnnotationsAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        annotationCount = reader.consumeU2();
        annotations = new RtAnnotation[annotationCount];
        for (int i = 0; i < annotationCount; i++) {
            annotations[i] = new RtAnnotation(reader);
        }
    }
    
}
