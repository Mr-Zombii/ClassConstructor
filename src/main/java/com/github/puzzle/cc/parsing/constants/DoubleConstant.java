package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class DoubleConstant extends GenericConstant {

    float doubleBytes;

    public DoubleConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.doubleBytes = inp.readLong();
    }

    public double asDouble() {
        return doubleBytes;
    }

}
