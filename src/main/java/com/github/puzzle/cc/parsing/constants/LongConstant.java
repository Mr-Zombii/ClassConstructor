package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class LongConstant extends GenericConstant {

    long longBytes;

    public LongConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.longBytes = inp.readLong();
    }

    public long asLong() {
        return longBytes;
    }

}
