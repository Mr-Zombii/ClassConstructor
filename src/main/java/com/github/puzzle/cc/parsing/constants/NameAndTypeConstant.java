package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class NameAndTypeConstant extends GenericConstant {

    int nameIndex;
    int descriptorIndex;

    public NameAndTypeConstant(int nameUtf8Index, int descriptorUtf8Index) {
        super(ConstantPool.TagType.CONSTANT_NAME_AND_TYPE, null);
        nameIndex = nameUtf8Index;
        descriptorIndex = descriptorUtf8Index;
    }

    public NameAndTypeConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.nameIndex = inp.readUnsignedShort();
        this.descriptorIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(nameIndex);
        stream.writeShort(descriptorIndex);
    }

    public String getName(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.get(nameIndex)).asString();
    }

    public String getDescriptor(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.get(descriptorIndex)).asString();
    }

    public int getNameIndex() {
        return nameIndex;
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
