package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class InterfaceMethodRefConstant extends GenericConstant {

    int classIndex;
    int nameAndTypeIndex;

    public InterfaceMethodRefConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.classIndex = inp.readUnsignedShort();
        this.nameAndTypeIndex = inp.readUnsignedShort();
    }

    public ClassConstant getClass(ConstantPool pool) {
        return (ClassConstant) pool.constants[classIndex];
    }

    public NameAndTypeConstant getNameAndType(ConstantPool pool) {
        return (NameAndTypeConstant) pool.constants[nameAndTypeIndex];
    }

}
