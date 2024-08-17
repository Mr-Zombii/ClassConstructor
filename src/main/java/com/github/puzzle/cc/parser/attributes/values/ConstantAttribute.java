package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class ConstantAttribute extends AttributeInfo {

    short valueIndex;

    public ConstantAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
        valueIndex = reader.consumeU2();
    }
}
