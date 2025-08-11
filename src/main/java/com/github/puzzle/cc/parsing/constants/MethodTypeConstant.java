package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodTypeConstant extends GenericConstant {

    int descriptorIndex;

    public MethodTypeConstant(int descriptorIndex) {
        super(ConstantPool.TagType.CONSTANT_METHOD_HANDLE, null);
        this.descriptorIndex = descriptorIndex;
    }

    public MethodTypeConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.descriptorIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(descriptorIndex);
    }

    public String getDescriptor(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.get(descriptorIndex)).asString();
    }

    public int getDescriptorIndex() {
        return descriptorIndex;
    }
}
