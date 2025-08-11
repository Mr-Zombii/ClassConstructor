package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class StringConstant extends GenericConstant {

    int stringIndex;

    public StringConstant(int utf8Index) {
        super(ConstantPool.TagType.CONSTANT_STRING, null);
        stringIndex = utf8Index;
    }

    public StringConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.stringIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(stringIndex);
    }

    public String getString(ConstantPool pool) {
        return ((UTF8CONSTANT) pool.get(stringIndex)).asString();
    }

    public int getIndex() {
        return stringIndex;
    }
}
