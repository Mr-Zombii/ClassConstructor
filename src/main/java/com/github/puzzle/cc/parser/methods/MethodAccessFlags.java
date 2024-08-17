package com.github.puzzle.cc.parser.methods;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum MethodAccessFlags {

    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_SYNCHRONIZED(0x0020),
    ACC_BRIDGE(0x0040),
    ACC_VARARGS(0x0080),
    ACC_NATIVE(0x0100),
    ACC_ABSTRACT(0x0400),
    ACC_STRICT(0x0800),
    ACC_SYNTHETIC(0x1000);

    final short flag;

    MethodAccessFlags(int num) {
        this.flag = (byte) num;
        init(this, (byte) num);
    }


    static Map<Short, MethodAccessFlags> shortTagTypeMap;

    static void init(MethodAccessFlags flag, short num) {
        if (shortTagTypeMap == null)
            shortTagTypeMap = new HashMap<>();
        shortTagTypeMap.put(num, flag);
    }

    public static MethodAccessFlags[] getFlagsFromU2(short u2) {
        List<MethodAccessFlags> accessFlags = new ArrayList<>();
        MethodAccessFlags[] values = MethodAccessFlags.values();
        for (int i = values.length - 1; i >= 0; i--) {
            if ((values[i].flag & u2) != 0) accessFlags.add(values[i]);
        }
        return accessFlags.toArray(new MethodAccessFlags[0]);
    }

    public static MethodAccessFlags fromU2(short u2) {
        return shortTagTypeMap.getOrDefault(u2, ACC_PUBLIC);
    }

}
