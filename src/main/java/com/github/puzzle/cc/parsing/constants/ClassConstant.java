package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ClassConstant extends GenericConstant {

    int nameIndex;

    public ClassConstant(int nameUtf8Index) {
        super(ConstantPool.TagType.CONSTANT_CLASS, null);
        nameIndex = nameUtf8Index;
    }

    public ClassConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.nameIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(nameIndex);
    }

    public String getName(ConstantPool pool) {
        return ((UTF8CONSTANT)pool.get(nameIndex)).asString();
    }

    @Override
    public String toString() {
        return "{ type: \"" + getTag() + "\", nameIdx: #" + nameIndex + " }";
    }

    public int getNameIdx() {
        return nameIndex;
    }
}
