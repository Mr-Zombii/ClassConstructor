package com.github.puzzle.cc.parser.fields;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FieldAccessFlag {

    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_VOLATILE(0x0040),
    ACC_TRANSIENT(0x0080),
    ACC_SYNTHETIC(0x1000),
    ACC_ENUM(0x4000);

    final short flag;

    FieldAccessFlag(int num) {
        this.flag = (byte) num;
        init(this, (byte) num);
    }


    static Map<Short, FieldAccessFlag> shortTagTypeMap;

    static void init(FieldAccessFlag flag, short num) {
        if (shortTagTypeMap == null)
            shortTagTypeMap = new HashMap<>();
        shortTagTypeMap.put(num, flag);
    }

    public static FieldAccessFlag[] getFlagsFromU2(short u2) {
        List<FieldAccessFlag> accessFlags = new ArrayList<>();
        FieldAccessFlag[] values = FieldAccessFlag.values();
        for (int i = values.length - 1; i >= 0; i--) {
            if ((values[i].flag & u2) != 0) accessFlags.add(values[i]);
        }
        return accessFlags.toArray(new FieldAccessFlag[0]);
    }

    public static FieldAccessFlag fromU2(short u2) {
        return shortTagTypeMap.getOrDefault(u2, ACC_PUBLIC);
    }

}
