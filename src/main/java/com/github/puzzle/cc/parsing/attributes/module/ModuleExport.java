package com.github.puzzle.cc.parsing.attributes.module;


import com.github.puzzle.cc.access.AccessFlag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModuleExport {

    int exportsIndex;
    AccessFlag[] exportsFlags;
    int[] exportsToIndex;

    public ModuleExport(DataInputStream inp) throws IOException {
        exportsIndex = inp.readUnsignedShort();
        exportsFlags = ModuleExportAccessFlags.getFromFlags(inp.readUnsignedShort());
        exportsToIndex = new int[inp.readUnsignedShort()];
        for (int i = 0; i < exportsToIndex.length; i++) {
            exportsToIndex[i] = inp.readUnsignedShort();
        }
    }

    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(exportsIndex);
        int accFlags = 0;
        for (AccessFlag flag : exportsFlags) accFlags |= flag.getMask();
        outputStream.writeShort(accFlags);
        outputStream.writeShort(exportsToIndex.length);
        for (int i : exportsToIndex) outputStream.writeShort(i);
    }

    public enum ModuleExportAccessFlags implements AccessFlag {

        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final int flag;

        ModuleExportAccessFlags(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }


        static Map<Integer, ModuleExportAccessFlags> intTagTypeMap;

        static void init(ModuleExportAccessFlags flag, int num) {
            if (intTagTypeMap == null)
                intTagTypeMap = new HashMap<>();
            intTagTypeMap.put(num, flag);
        }

        public static AccessFlag[] getFromFlags(int u2) {
            List<AccessFlag> accessFlags = new ArrayList<>();
            AccessFlag[] values = ModuleExportAccessFlags.values();
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
