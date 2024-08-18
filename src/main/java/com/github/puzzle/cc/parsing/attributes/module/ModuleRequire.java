package com.github.puzzle.cc.parsing.attributes.module;

import com.github.puzzle.cc.access.AccessFlag;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleRequire {

    int requiresIndex;
    AccessFlag[] requiresFlags;
    int requiresVersionIndex;

    public ModuleRequire(DataInputStream inp) throws IOException {
        requiresIndex = inp.readUnsignedShort();
        requiresFlags = ModuleRequireAccessFlags.getFromFlags(inp.readUnsignedShort());
        requiresVersionIndex = inp.readUnsignedShort();
    }

    public enum ModuleRequireAccessFlags implements AccessFlag {

        ACC_TRANSITIVE(0x0020),
        ACC_STATIC_PHASE(0x0040),
        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final int flag;

        ModuleRequireAccessFlags(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }


        static Map<Integer, ModuleRequireAccessFlags> intTagTypeMap;

        static void init(ModuleRequireAccessFlags flag, int num) {
            if (intTagTypeMap == null)
                intTagTypeMap = new HashMap<>();
            intTagTypeMap.put(num, flag);
        }

        public static AccessFlag[] getFromFlags(int u2) {
            List<AccessFlag> accessFlags = new ArrayList<>();
            AccessFlag[] values = ModuleRequireAccessFlags.values();
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
