package com.github.puzzle.cc.parsing.constants;


import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class IntegerConstant extends GenericConstant {

    int intBytes;

    public IntegerConstant(int intValue) {
        super(ConstantPool.TagType.CONSTANT_INT, null);
        intBytes = intValue;
    }

    public IntegerConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.intBytes = inp.readInt();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeInt(intBytes);
    }


    public int asInt() {
        return intBytes;
    }

}
