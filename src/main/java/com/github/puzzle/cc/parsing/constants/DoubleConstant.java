package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DoubleConstant extends GenericConstant {

    double doubleBytes;

    public DoubleConstant(double doubleValue) {
        super(ConstantPool.TagType.CONSTANT_DOUBLE, null);
        doubleBytes = doubleValue;
    }

    public DoubleConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.doubleBytes = inp.readDouble();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeDouble(doubleBytes);
    }

    public double asDouble() {
        return doubleBytes;
    }

}
