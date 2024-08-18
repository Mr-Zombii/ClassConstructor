package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class MethodRefConstant extends GenericConstant {

    int classIndex;
    int nameAndTypeIndex;

    public MethodRefConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.classIndex = inp.readUnsignedShort();
        this.nameAndTypeIndex = inp.readUnsignedShort();
    }

    public String getClass(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.constants[classIndex]).asString();
    }

    public NameAndTypeConstant getNameAndType(ConstantPool pool) {
        return (NameAndTypeConstant) pool.constants[nameAndTypeIndex];
    }
}
