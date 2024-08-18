package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class GenericConstant {

    ConstantPool.TagType type;

    public GenericConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        this.type = type;
    }

    public ConstantPool.TagType getTag() {
        return type;
    }

}
