package com.github.puzzle.cc.parsing.methods;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.MethodAccessFlags;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodInfo {

    AccessFlag[] accessFlags;
    int nameIndex;
    int descriptorIndex;
    int attributesCount;
    Attributes attributes;

    public MethodInfo(ConstantPool pool, DataInputStream inp) throws IOException {
        accessFlags = MethodAccessFlags.getFromFlags(inp.readUnsignedShort());
        nameIndex = inp.readUnsignedShort();
        descriptorIndex = inp.readUnsignedShort();

        attributesCount = inp.readUnsignedShort();
        attributes = AttributeInfo.readAttributes(pool, attributesCount, inp);
    }

}
