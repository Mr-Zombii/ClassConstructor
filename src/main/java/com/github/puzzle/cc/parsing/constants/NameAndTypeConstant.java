package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class NameAndTypeConstant extends GenericConstant {

    int nameIndex;
    int descriptorIndex;

    public NameAndTypeConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.nameIndex = inp.readUnsignedShort();
        this.descriptorIndex = inp.readUnsignedShort();
    }

    public String getName(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.constants[nameIndex]).asString();
    }

    public String getDescriptor(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.constants[descriptorIndex]).asString();
    }

}
