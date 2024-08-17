package com.github.puzzle.cc.parser.attributes.values.module;

import com.github.puzzle.cc.parser.ClassReader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleRequire {

    short requiresIndex;
    ModuleRequireAccessFlags[] requiresFlags;
    short requiresVersionIndex;

    public ModuleRequire(ClassReader reader) {
        requiresIndex = reader.consumeU2();
        requiresFlags = ModuleRequireAccessFlags.getFlagsFromU2(reader.consumeU2());
        requiresVersionIndex = reader.consumeU2();
    }

    public enum ModuleRequireAccessFlags {

        ACC_TRANSITIVE(0x0020),
        ACC_STATIC_PHASE(0x0040),
        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final short flag;

        ModuleRequireAccessFlags(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }


        static Map<Short, ModuleRequireAccessFlags> shortTagTypeMap;

        static void init(ModuleRequireAccessFlags flag, short num) {
            if (shortTagTypeMap == null)
                shortTagTypeMap = new HashMap<>();
            shortTagTypeMap.put(num, flag);
        }

        public static ModuleRequireAccessFlags[] getFlagsFromU2(short u2) {
            List<ModuleRequireAccessFlags> accessFlags = new ArrayList<>();
            ModuleRequireAccessFlags[] values = ModuleRequireAccessFlags.values();
            for (int i = values.length - 1; i >= 0; i--) {
                if ((values[i].flag & u2) != 0) accessFlags.add(values[i]);
            }
            return accessFlags.toArray(new ModuleRequireAccessFlags[0]);
        }

        public static ModuleRequireAccessFlags fromU2(short u2) {
            return shortTagTypeMap.getOrDefault(u2, ACC_MANDATED);
        }

    }
    
}
