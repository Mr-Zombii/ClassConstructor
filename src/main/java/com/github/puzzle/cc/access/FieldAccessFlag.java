package com.github.puzzle.cc.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum FieldAccessFlag implements AccessFlag {

    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_PROTECTED(0x0004),
    ACC_STATIC(0x0008),
    ACC_FINAL(0x0010),
    ACC_VOLATILE(0x0040),
    ACC_TRANSIENT(0x0080),
    ACC_SYNTHETIC(0x1000),
    ACC_ENUM(0x4000);

    final int flag;

    FieldAccessFlag(int num) {
        this.flag = (byte) num;
        init(this, (byte) num);
    }


    static Map<Integer, FieldAccessFlag> intTagTypeMap;

    static void init(FieldAccessFlag flag, int num) {
        if (intTagTypeMap == null)
            intTagTypeMap = new HashMap<>();
        intTagTypeMap.put(num, flag);
    }

    public static AccessFlag[] getFromFlags(int u2) {
        List<AccessFlag> accessFlags = new ArrayList<>();
        AccessFlag[] values = FieldAccessFlag.values();
        for (int i = values.length - 1; i >= 0; i--) {
            if ((values[i].getMask() & u2) == values[i].getMask()) accessFlags.add(values[i]);
        }
        return accessFlags.toArray(new AccessFlag[0]);
    }

    @Override
    public int getMask() {
        return flag;
    }
}
