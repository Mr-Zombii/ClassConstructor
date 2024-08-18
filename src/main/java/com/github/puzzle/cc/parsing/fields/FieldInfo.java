package com.github.puzzle.cc.parsing.fields;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.FieldAccessFlag;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class FieldInfo {

    AccessFlag[] accessFlags;
    int nameIndex;
    int descriptorIndex;
    int attributesCount;
    Attributes attributes;

    public FieldInfo(ConstantPool pool, DataInputStream inp) throws IOException {
        accessFlags = FieldAccessFlag.getFromFlags(inp.readUnsignedShort());
        nameIndex = inp.readUnsignedShort();
        descriptorIndex = inp.readUnsignedShort();

        attributesCount = inp.readUnsignedShort();
        attributes = AttributeInfo.readAttributes(pool, attributesCount, inp);
    }

}
