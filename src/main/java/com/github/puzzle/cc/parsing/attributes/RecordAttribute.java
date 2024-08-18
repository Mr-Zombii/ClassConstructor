package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class RecordAttribute extends AttributeInfo {

    int componentsCount;
    RecordComponentInfo[] components;

    public RecordAttribute(ConstantPool pool, int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        componentsCount = inp.readUnsignedShort();
        components = new RecordComponentInfo[componentsCount];
        for (int i = 0; i < componentsCount; i++) {
            components[i] = new RecordComponentInfo(pool, inp);
        }

    }

    public static class RecordComponentInfo {

        int nameIndex;
        int descriptorIndex;
        int attributesCount;
        Attributes attributes;

        public RecordComponentInfo(ConstantPool pool, DataInputStream inp) throws IOException {
            nameIndex = inp.readUnsignedShort();
            descriptorIndex = inp.readUnsignedShort();

            attributesCount = inp.readUnsignedShort();
            attributes = AttributeInfo.readAttributes(pool, attributesCount, inp);
        }

    }
}
