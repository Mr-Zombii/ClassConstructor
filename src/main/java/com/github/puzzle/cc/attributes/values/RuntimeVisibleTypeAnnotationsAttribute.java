package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;
import com.github.puzzle.cc.attributes.values.annotations.RtAnnotation;
import com.github.puzzle.cc.attributes.values.annotations.TypeAnnotation;

// TODO: continue from https://docs.oracle.com/javase/specs/jvms/se17/html/jvms-4.html#jvms-4.7.20
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
