package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LongConstant extends GenericConstant {

    long longBytes;

    public LongConstant(long longValue) {
        super(ConstantPool.TagType.CONSTANT_LONG, null);
        longBytes = longValue;
    }

    public LongConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.longBytes = inp.readLong();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeLong(longBytes);
    }

    @Override
    public String toString() {
        return "{ type: \"" + getTag() + "\", value: #" + asLong() + " }";
    }

    public long asLong() {
        return longBytes;
    }

}
