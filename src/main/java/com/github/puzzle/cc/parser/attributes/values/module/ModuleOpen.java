package com.github.puzzle.cc.parser.attributes.values.module;

import com.github.puzzle.cc.parser.ClassReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleOpen {

    short opensIndex;
    ModuleOpenAccessFlags[] opensFlags;
    short opensToCount;
    short[] opensToIndex;

    public ModuleOpen(ClassReader reader) {
        opensIndex = reader.consumeU2();
        opensFlags = ModuleOpenAccessFlags.getFlagsFromU2(reader.consumeU2());
        opensToCount = reader.consumeU2();
        opensToIndex = new short[opensToCount];
        for (int i = 0; i < opensToCount; i++) {
            opensToIndex[i] = reader.consumeU2();
        }
    }

    public enum ModuleOpenAccessFlags {

        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final short flag;

        ModuleOpenAccessFlags(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }

        static Map<Short, ModuleOpenAccessFlags> shortTagTypeMap;

        static void init(ModuleOpenAccessFlags flag, short num) {
            if (shortTagTypeMap == null)
                shortTagTypeMap = new HashMap<>();
            shortTagTypeMap.put(num, flag);
        }

        public static ModuleOpenAccessFlags[] getFlagsFromU2(short u2) {
            List<ModuleOpenAccessFlags> accessFlags = new ArrayList<>();
            ModuleOpenAccessFlags[] values = ModuleOpenAccessFlags.values();
            for (int i = values.length - 1; i >= 0; i--) {
                if ((values[i].flag & u2) != 0) accessFlags.add(values[i]);
            }
            return accessFlags.toArray(new ModuleOpenAccessFlags[0]);
        }

        public static ModuleOpenAccessFlags fromU2(short u2) {
            return shortTagTypeMap.getOrDefault(u2, ACC_MANDATED);
        }

    }

}
