package com.github.puzzle.cc.parser.fields;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class FieldInfo {

    FieldAccessFlag[] accessFlags;
    short nameIndex;
    short descriptorIndex;
    short attributesCount;
    AttributeInfo[] attributes;

    public FieldInfo(ClassReader reader) {
        accessFlags = FieldAccessFlag.getFlagsFromU2(reader.consumeU2());
        nameIndex = reader.consumeU2();
        descriptorIndex = reader.consumeU2();

        attributesCount = reader.consumeU2();
        attributes = AttributeInfo.readAttributes(attributesCount, reader);
    }

}
