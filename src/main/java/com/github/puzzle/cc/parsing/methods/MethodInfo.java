package com.github.puzzle.cc.parsing.methods;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.MethodAccessFlags;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.constants.UTF8CONSTANT;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodInfo {

    AccessFlag[] accessFlags;
    int nameIndex;
    int descriptorIndex;
    Attributes attributes;

    public MethodInfo(ConstantPool pool, DataInputStream inp) throws IOException {
        accessFlags = MethodAccessFlags.getFromFlags(inp.readUnsignedShort());
        nameIndex = inp.readUnsignedShort();
        descriptorIndex = inp.readUnsignedShort();

        attributes = AttributeInfo.readAttributes(pool, inp.readUnsignedShort(), inp);
    }

    public void writeToStream(DataOutputStream outputStream) throws IOException {
        int accFlags = 0;
        for (AccessFlag flag : accessFlags) accFlags |= flag.getMask();
        outputStream.writeShort(accFlags);
        outputStream.writeShort(nameIndex);
        outputStream.writeShort(descriptorIndex);
        attributes.writeToStream(outputStream);
    }

}
