package com.github.puzzle.cc.parsing.constants;


import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class IntegerConstant extends GenericConstant {

    int intBytes;

    public IntegerConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.intBytes = inp.readInt();
    }

    public int asInt() {
        return intBytes;
    }

}
