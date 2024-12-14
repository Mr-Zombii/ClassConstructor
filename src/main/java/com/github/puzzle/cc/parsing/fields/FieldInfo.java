package com.github.puzzle.cc.parsing.fields;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.FieldAccessFlag;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.constants.UTF8CONSTANT;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FieldInfo {

    public AccessFlag[] accessFlags;
    public int nameIndex;
    public int descriptorIndex;
    public Attributes attributes;

    public FieldInfo() {

    }

    public FieldInfo(ConstantPool pool, DataInputStream inp) throws IOException {
        accessFlags = FieldAccessFlag.getFromFlags(inp.readUnsignedShort());
        nameIndex = inp.readUnsignedShort();
        descriptorIndex = inp.readUnsignedShort();

        attributes = AttributeInfo.readAttributes(pool, inp.readUnsignedShort(), inp);
    }

    public String getName(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.get(nameIndex)).asString();
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
