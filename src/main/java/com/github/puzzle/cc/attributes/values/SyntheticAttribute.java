package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class SyntheticAttribute extends AttributeInfo {

    public SyntheticAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
    }
}
