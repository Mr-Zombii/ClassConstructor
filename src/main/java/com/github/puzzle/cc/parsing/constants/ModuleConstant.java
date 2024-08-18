package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class ModuleConstant extends GenericConstant {

    int nameIndex;

    public ModuleConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.nameIndex = inp.readUnsignedShort();
    }

    public String getName(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.constants[nameIndex]).asString();
    }
}
