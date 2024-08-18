package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.annotations.values.ElementValue;

import java.io.DataInputStream;
import java.io.IOException;

public class AnnotationDefaultAttribute extends AttributeInfo {

    ElementValue value;

    public AnnotationDefaultAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);
        value = new ElementValue(inp);
    }
    
}
