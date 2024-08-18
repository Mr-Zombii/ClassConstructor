package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UTF8CONSTANT extends GenericConstant {

    byte[] bytes;

    public UTF8CONSTANT(String strValue) {
        super(ConstantPool.TagType.CONSTANT_UTF8, null);
        bytes = strValue.getBytes();
    }

    public UTF8CONSTANT(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.bytes = new byte[inp.readUnsignedShort()];

        for (int i = 0; i < bytes.length; i++) {
            this.bytes[i] = inp.readByte();
        }
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(bytes.length);
        for (byte i : bytes) {
            stream.writeByte(i);
        }
    }

    public String asString() {
        return new String(bytes);
    }

}
