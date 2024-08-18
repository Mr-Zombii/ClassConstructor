package com.github.puzzle.cc.access;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ClassAccessFlag implements AccessFlag {

    ACC_PUBLIC(0x0001),
    ACC_PRIVATE(0x0002),
    ACC_FINAL(0x0010),
    ACC_SUPER(0x0020),
    ACC_INTERFACE(0x0200),
    ACC_ABSTRACT(0x0400),
    ACC_SYNTHETIC(0x1000),
    ACC_ANNOTATION(0x2000),
    ACC_ENUM(0x4000),
    ACC_MODULE(0x8000);

    final int mask;

    ClassAccessFlag(int mask) {
        this.mask = (byte) mask;
        init(this, (byte) mask);
    }


    static Map<Integer, ClassAccessFlag> intTagTypeMap;

    static void init(ClassAccessFlag flag, int mask) {
        if (intTagTypeMap == null)
            intTagTypeMap = new HashMap<>();
        intTagTypeMap.put(mask, flag);
    }

    public static AccessFlag[] getFromFlags(int u2) {
        List<AccessFlag> accessFlags = new ArrayList<>();
        AccessFlag[] values = ClassAccessFlag.values();
        for (int i = values.length - 1; i >= 0; i--) {
            if ((values[i].getMask() & u2) == values[i].getMask()) accessFlags.add(values[i]);
        }
        return accessFlags.toArray(new AccessFlag[0]);
    }

    @Override
    public int getMask() {
        return mask;
    }
}
