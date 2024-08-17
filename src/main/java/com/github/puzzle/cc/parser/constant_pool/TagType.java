package com.github.puzzle.cc.parser.constant_pool;

import java.util.HashMap;
import java.util.Map;

public enum TagType {

    CONSTANT_UTF8(1),
    // missing 2
    CONSTANT_INT(3),
    CONSTANT_FLOAT(4),
    CONSTANT_LONG(5),
    CONSTANT_DOUBLE(6),
    CONSTANT_CLASS(7),
    CONSTANT_STRING(8),
    CONSTANT_FIELD_REF(9),
    CONSTANT_METHOD_REF(10),
    CONSTANT_INTERFACE_METHOD_REF(11),
    CONSTANT_NAME_AND_TYPE(12),
    // missing 13, 14
    CONSTANT_METHOD_HANDLE(15),
    CONSTANT_METHOD_TYPE(16),
    CONSTANT_DYNAMIC(17),
    CONSTANT_INVOKE_DYNAMIC(18),
    CONSTANT_MODULE(19),
    CONSTANT_PACKAGE(20);

    final byte tag;

    TagType(int num) {
        this.tag = (byte) num;
        init(this, (byte) num);
    }


    static final Map<Byte, TagType> byteTagTypeMap = new HashMap<>();

    static void init(TagType tagType, byte num) {
        byteTagTypeMap.put(num, tagType);
    }

    public static TagType fromU1(byte u1) {
        return byteTagTypeMap.getOrDefault(u1, CONSTANT_UTF8);
    }

}
