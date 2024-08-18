package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class InvokeDynamicConstant extends GenericConstant {

    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    public InvokeDynamicConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.bootstrapMethodAttrIndex = inp.readUnsignedShort();
        this.nameAndTypeIndex = inp.readUnsignedShort();
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public NameAndTypeConstant getNameAndType(ConstantPool pool) {
        return (NameAndTypeConstant) pool.constants[nameAndTypeIndex];
    }

}
