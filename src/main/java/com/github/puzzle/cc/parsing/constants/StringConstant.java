package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class StringConstant extends GenericConstant {

    int stringIndex;

    public StringConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.stringIndex = inp.readUnsignedShort();
    }

    public String getString(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.constants[stringIndex]).asString();
    }

}
