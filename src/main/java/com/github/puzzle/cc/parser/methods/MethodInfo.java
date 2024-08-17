package com.github.puzzle.cc.parser.methods;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class MethodInfo {

    MethodAccessFlags[] accessFlags;
    short nameIndex;
    short descriptorIndex;
    short attributesCount;
    AttributeInfo[] attributes;

    public MethodInfo(ClassReader reader) {
        accessFlags = MethodAccessFlags.getFlagsFromU2(reader.consumeU2());
        nameIndex = reader.consumeU2();
        descriptorIndex = reader.consumeU2();

        attributesCount = reader.consumeU2();
        attributes = AttributeInfo.readAttributes(attributesCount, reader);
    }

}
