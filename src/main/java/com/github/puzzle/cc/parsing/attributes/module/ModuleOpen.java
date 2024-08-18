package com.github.puzzle.cc.parsing.attributes.module;


import com.github.puzzle.cc.access.AccessFlag;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleOpen {

    int opensIndex;
    AccessFlag[] opensFlags;
    int opensToCount;
    int[] opensToIndex;

    public ModuleOpen(DataInputStream inp) throws IOException {
        opensIndex = inp.readUnsignedShort();
        opensFlags = ModuleOpenAccessFlags.getFromFlags(inp.readUnsignedShort());
        opensToCount = inp.readUnsignedShort();
        opensToIndex = new int[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex[i] = inp.readUnsignedShort();
        }
    }

    public enum ModuleOpenAccessFlags implements AccessFlag {

        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final int flag;

        ModuleOpenAccessFlags(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }

        static Map<Integer, ModuleOpenAccessFlags> intTagTypeMap;

        static void init(ModuleOpenAccessFlags flag, int num) {
            if (intTagTypeMap == null)
                intTagTypeMap = new HashMap<>();
            intTagTypeMap.put(num, flag);
        }

        public static AccessFlag[] getFromFlags(int u2) {
            List<AccessFlag> accessFlags = new ArrayList<>();
            AccessFlag[] values = ModuleOpenAccessFlags.values();
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

}
