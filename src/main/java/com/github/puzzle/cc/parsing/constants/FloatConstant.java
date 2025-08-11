package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FloatConstant extends GenericConstant {

    float floatBytes;

    public FloatConstant(float floatValue) {
        super(ConstantPool.TagType.CONSTANT_FLOAT, null);
        floatBytes = floatValue;
    }

    public FloatConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.floatBytes = inp.readFloat();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeFloat(floatBytes);
    }

    @Override
    public String toString() {
        return "{ type: \"" + getTag() + "\", value: #" + asFloat() + " }";
    }

    public float asFloat() {
        return floatBytes;
    }
}
