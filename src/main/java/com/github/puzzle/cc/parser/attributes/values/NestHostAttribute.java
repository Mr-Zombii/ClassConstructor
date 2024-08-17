package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class NestHostAttribute extends AttributeInfo {

    short hostClassIndex;

    public NestHostAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        hostClassIndex = reader.consumeU2();
    }
}
