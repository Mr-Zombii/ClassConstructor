package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;
import com.github.puzzle.cc.parser.attributes.values.annotations.ElementValue;

public class AnnotationDefaultAttribute extends AttributeInfo {

    ElementValue value;

    public AnnotationDefaultAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
        value = new ElementValue(reader);
    }
    
}
