package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class RecordAttribute extends AttributeInfo {

    short componentsCount;
    RecordComponentInfo[] components;

    public RecordAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        componentsCount = reader.consumeU2();
        components = new RecordComponentInfo[componentsCount];
        for (int i = 0; i < componentsCount; i++) {
            components[i] = new RecordComponentInfo(reader);
        }

    }

    public static class RecordComponentInfo {

        short nameIndex;
        short descriptorIndex;
        short attributesCount;
        AttributeInfo[] attributes;

        public RecordComponentInfo(ClassReader reader) {
            nameIndex = reader.consumeU2();
            descriptorIndex = reader.consumeU2();

            attributesCount = reader.consumeU2();
            attributes = AttributeInfo.readAttributes(attributesCount, reader);
        }

    }
}
