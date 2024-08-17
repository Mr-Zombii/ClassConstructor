package com.github.puzzle.cc.parser.attributes.values.module;

import com.github.puzzle.cc.parser.ClassReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleExport {

    short exportsIndex;
    ModuleExportAccessFlags[] exportsFlags;
    short exportsToCount;
    short[] exportsToIndex;

    public ModuleExport(ClassReader reader) {
        exportsIndex = reader.consumeU2();
        exportsFlags = ModuleExportAccessFlags.getFlagsFromU2(reader.consumeU2());
        exportsToCount = reader.consumeU2();
        exportsToIndex = new short[exportsToCount];
        for (int i = 0; i < exportsToCount; i++) {
            exportsToIndex[i] = reader.consumeU2();
        }
    }

    public enum ModuleExportAccessFlags {

        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final short flag;

        ModuleExportAccessFlags(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }


        static Map<Short, ModuleExportAccessFlags> shortTagTypeMap;

        static void init(ModuleExportAccessFlags flag, short num) {
            if (shortTagTypeMap == null)
                shortTagTypeMap = new HashMap<>();
            shortTagTypeMap.put(num, flag);
        }

        public static ModuleExportAccessFlags[] getFlagsFromU2(short u2) {
            List<ModuleExportAccessFlags> accessFlags = new ArrayList<>();
            ModuleExportAccessFlags[] values = ModuleExportAccessFlags.values();
            for (int i = values.length - 1; i >= 0; i--) {
                if ((values[i].flag & u2) != 0) accessFlags.add(values[i]);
            }
            return accessFlags.toArray(new ModuleExportAccessFlags[0]);
        }

        public static ModuleExportAccessFlags fromU2(short u2) {
            return shortTagTypeMap.getOrDefault(u2, ACC_MANDATED);
        }

    }

}
