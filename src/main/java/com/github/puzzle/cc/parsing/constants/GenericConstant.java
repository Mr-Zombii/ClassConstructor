package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class GenericConstant {

    ConstantPool.TagType type;

    public GenericConstant(ConstantPool.TagType type, DataInputStream inp) {
        this.type = type;
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeByte(type.tag);
    }

    public ConstantPool.TagType getTag() {
        return type;
    }

}
