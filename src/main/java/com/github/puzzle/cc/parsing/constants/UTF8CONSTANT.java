package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.IOException;

public class UTF8CONSTANT extends GenericConstant {

    int length;
    byte[] bytes;

    public UTF8CONSTANT(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.length = inp.readUnsignedShort();
        this.bytes = new byte[length];

        for (int i = 0; i < length; i++) {
            this.bytes[i] = inp.readByte();
        }
    }

    @Override
    public String toString() {
        return new String(bytes);
    }

    public String asString() {
        return new String(bytes);
    }

}
