package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class FloatConstant extends GenericConstant {

    float floatTypes;

    public FloatConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.floatTypes = inp.readFloat();
    }

    public float asFloat() {
        return floatTypes;
    }
}
