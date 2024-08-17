package com.github.puzzle.cc;

import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.fields.FieldInfo;

public class ClassReader {

    public int ptr = 0;
    public byte[] bytes;

    public int magicNumber;
    public short minor_version;
    public short major_version;

    public short constant_pool_size;
    public CP_INFO[] constant_pool;

    public ClassAccessFlag[] access_flags;

    public short this_class;
    public short super_class;

    public short interfaces_count;
    public short[] interfaces;

    public short field_count;
    public FieldInfo[] fields;

    public ClassReader(byte[] bytes) {
        this.bytes = bytes;

        magicNumber = consumeU4();
        minor_version = consumeU2();
        major_version = consumeU2();

        readConstantPool();
        access_flags = ClassAccessFlag.getFlagsFromU2(consumeU2());

        this_class = consumeU2();
        super_class = consumeU2();

        interfaces_count = consumeU2();
        interfaces = new short[interfaces_count];
        for (int i = 0; i < interfaces_count; i++) {
            interfaces[i] = consumeU2();
        }

        readFields();
    }

    void readFields() {
        field_count = consumeU2();
        fields = new FieldInfo[field_count];
        for (int i = 0; i < field_count; i++) {
            fields[i] = new FieldInfo(this);
        }
    }

    void readConstantPool() {
        constant_pool_size = consumeU2();
        constant_pool = new CP_INFO[constant_pool_size];

        for (int i = 0; i < constant_pool_size; i++) {
            constant_pool[i] = CP_INFO.readConstant(this);
        }
    }

    public int consumeU4() {
        int u4 = (((int)bytes[ptr] << 24) + ((int)bytes[ptr + 1] << 16) + ((int)bytes[ptr + 2] << 8) + (int)bytes[ptr + 3]);
        ptr += 3;
        return u4;
    }

    public int consumeU3() {
        int u3 = (((int)bytes[ptr] << 16) + ((int)bytes[ptr + 1] << 8) + (int)bytes[ptr + 2]);
        ptr += 3;
        return u3;
    }

    public short consumeU2() {
        short u2 = (short) ((short)bytes[ptr] << 16 + (short)bytes[ptr + 1]);
        ptr += 2;
        return u2;
    }

    public byte consumeU1() {
        byte u1 = bytes[ptr];
        ptr++;
        return u1;
    }

}
