package com.github.puzzle.cc.writer.stdlib;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.util.ConstantPoolUtil;
import com.github.puzzle.cc.util.NativeType;
import com.github.puzzle.cc.writer.ClassWriter;
import com.github.puzzle.cc.writer.bytecode.BytecodeWriter;

public class NativeArrays {

    static ClassWriter writer;
    static BytecodeWriter bytecodeWriter;

    public static void use(ClassWriter w, BytecodeWriter b) {
        writer = w;
        bytecodeWriter = b;
    }

    public static void newArray(Class<?> type) {
        if (NativeType.isNativeType(type))
            newNativeArray(NativeType.asNativeType(type));
        else
            newObjectArray(type.getName().replaceAll("\\.", "/"));
    }

    public static void newMultiDimensionArray(Class<?> type, int dimensions) {
        newMultiDimensionArray(type.getName().replaceAll("\\.", "/"), dimensions);
    }

    public static void newMultiDimensionArray(String type, int dimensions) {
        int valueIdx = ConstantPoolUtil.getOrCreateClass(new ConstantPool(), type);
        bytecodeWriter.putInstructionAV(Opcodes.MULTIANEWARRAY, (short) valueIdx, (short) dimensions);
    }

    public static void newObjectArray(String type) {
        int valueIdx = ConstantPoolUtil.getOrCreateClass(new ConstantPool(), type);
        bytecodeWriter.putInstructionA(Opcodes.ANEWARRAY, (short) valueIdx);
    }

    public static void newNativeArray(NativeType type) {
        bytecodeWriter.putInstructionV(Opcodes.NEWARRAY, (short) type.typeIdx);
    }

    /**
        -push ArrayRefType
        -push ArrayIndex
    */
    public static void loadFromArray() {
        bytecodeWriter.putInstruction(Opcodes.AALOAD);
    }

}
