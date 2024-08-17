package com.github.puzzle.cc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum ClassAccessFlag {

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

    final short flag;

    ClassAccessFlag(int num) {
        this.flag = (byte) num;
        init(this, (byte) num);
    }


    static final Map<Short, ClassAccessFlag> shortTagTypeMap = new HashMap<>();

    static void init(ClassAccessFlag flag, short num) {
        shortTagTypeMap.put(num, flag);
    }

    public static ClassAccessFlag[] getFlagsFromU2(short u2) {
        List<ClassAccessFlag> accessFlags = new ArrayList<>();
        ClassAccessFlag[] values = ClassAccessFlag.values();
        for (int i = values.length - 1; i >= 0; i--) {
            if ((values[i].flag & u2) != 0) accessFlags.add(values[i]);
        }
        return accessFlags.toArray(new ClassAccessFlag[0]);
    }

    public static ClassAccessFlag fromU2(short u2) {
        return shortTagTypeMap.getOrDefault(u2, ACC_PUBLIC);
    }

}
