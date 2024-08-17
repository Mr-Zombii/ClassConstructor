package com.github.puzzle.cc.attributes.values.annotations;

import com.github.puzzle.cc.ClassReader;

public class TypeAnnotation {

    byte targetType;

    public TypeAnnotation(ClassReader reader) {
        targetType = reader.consumeU1();
    }

}
