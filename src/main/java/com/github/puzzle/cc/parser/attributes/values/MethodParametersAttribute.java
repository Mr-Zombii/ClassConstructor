package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MethodParametersAttribute extends AttributeInfo {

    short parametersCount;
    MethodParameter[] parameters;

    public MethodParametersAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        parametersCount = reader.consumeU2();
        parameters = new MethodParameter[parametersCount];
        for (int i = 0; i < parametersCount; i++) {
            parameters[i] = new MethodParameter(reader);
        }
    }

    public static class MethodParameter {

        short nameIndex;
        MethodParameterAccessFlag[] accessFlags;

        public MethodParameter(ClassReader reader) {
            nameIndex = reader.consumeU2();
            accessFlags = MethodParameterAccessFlag.getFlagsFromU2(reader.consumeU2());
        }

    }

    public enum MethodParameterAccessFlag {

        ACC_FINAL(0x0010),
        ACC_SYNTHETIC(0x1000),
        ACC_MANDATED(0x8000);

        final short flag;

        MethodParameterAccessFlag(int num) {
            this.flag = (byte) num;
            init(this, (byte) num);
        }


        static Map<Short, MethodParameterAccessFlag> shortTagTypeMap;

        static void init(MethodParameterAccessFlag flag, short num) {
            if (shortTagTypeMap == null)
                shortTagTypeMap = new HashMap<>();
            shortTagTypeMap.put(num, flag);
        }

        public static MethodParameterAccessFlag[] getFlagsFromU2(short u2) {
            List<MethodParameterAccessFlag> accessFlags = new ArrayList<>();
            MethodParameterAccessFlag[] values = MethodParameterAccessFlag.values();
            for (int i = values.length - 1; i >= 0; i--) {
                if ((values[i].flag & u2) != 0) accessFlags.add(values[i]);
            }
            return accessFlags.toArray(new MethodParameterAccessFlag[0]);
        }

        public static MethodParameterAccessFlag fromU2(short u2) {
            return shortTagTypeMap.getOrDefault(u2, ACC_MANDATED);
        }

    }
    
}
