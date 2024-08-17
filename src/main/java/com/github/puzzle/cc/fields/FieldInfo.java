package com.github.puzzle.cc.fields;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

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
