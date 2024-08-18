package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodTypeConstant extends GenericConstant {

    int descriptorIndex;

    public MethodTypeConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.descriptorIndex = inp.readUnsignedShort();
    }

    public String getDescriptor(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.constants[descriptorIndex]).asString();
    }

}
