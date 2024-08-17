package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class ConstantAttribute extends AttributeInfo {

    short valueIndex;

    public ConstantAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
        valueIndex = reader.consumeU2();
    }
}
