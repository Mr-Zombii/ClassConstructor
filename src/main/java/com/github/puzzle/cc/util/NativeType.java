package com.github.puzzle.cc.util;

public enum NativeType {

    T_BOOLEAN(4, "Z", "java/lang/Boolean"),
    T_CHAR(5, "C", "java/lang/Character"),
    T_FLOAT(6, "F", "java/lang/Float"),
    T_DOUBLE(7, "D", "java/lang/Double"),
    T_BYTE(8, "B", "java/lang/Byte"),
    T_SHORT(9, "S", "java/lang/Short"),
    T_INT(10, "I", "java/lang/Integer"),
    T_LONG(11, "J", "java/lang/Long");

    public final int typeIdx;
    public final String nativeDescriptorChar;
    public final String objectClass;

    NativeType(int typeIdx, String nativeDescriptorChar, String objectClass) {
        this.nativeDescriptorChar = nativeDescriptorChar;
        this.objectClass = objectClass;
        this.typeIdx = typeIdx;
    }

    public static boolean isNativeType(Class<?> type) {
        return type.equals(boolean.class)
                || type.equals(char.class)
                || type.equals(float.class)
                || type.equals(double.class)
                || type.equals(byte.class)
                || type.equals(short.class)
                || type.equals(int.class)
                || type.equals(long.class);
    }

    public static NativeType asNativeType(Class<?> type) {
        if (!isNativeType(type)) return null;
        if (type.equals(boolean.class)) return NativeType.T_BOOLEAN;
        if (type.equals(char.class)) return NativeType.T_CHAR;
        if (type.equals(float.class)) return NativeType.T_FLOAT;
        if (type.equals(double.class)) return NativeType.T_DOUBLE;
        if (type.equals(byte.class)) return NativeType.T_BYTE;
        if (type.equals(short.class)) return NativeType.T_SHORT;
        if (type.equals(int.class)) return NativeType.T_INT;
        return NativeType.T_LONG;
    }
}
