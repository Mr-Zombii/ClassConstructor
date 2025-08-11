package com.github.puzzle.cc.writer.stdlib;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.util.ConstantPoolUtil;
import com.github.puzzle.cc.util.NativeType;
import com.github.puzzle.cc.writer.ClassWriter;
import com.github.puzzle.cc.writer.bytecode.BytecodeWriter;

public class NativeTypes {

    static ClassWriter writer;
    static BytecodeWriter bytecodeWriter;

    public static void use(ClassWriter w, BytecodeWriter b) {
        writer = w;
        bytecodeWriter = b;
    }

    public static void value(int i) {
        int valueIdx = ConstantPoolUtil.getOrCreateInteger(writer.constantPool, i);
        bytecodeWriter.putInstructionV(Opcodes.LDC, (short) (valueIdx));
        CCWriterUtil.callStatic(Integer.class, "valueOf", int.class);
    }

    public static void value(float i) {
        int valueIdx = ConstantPoolUtil.getOrCreateFloat(writer.constantPool, i);
        bytecodeWriter.putInstructionV(Opcodes.LDC, (short) valueIdx);
        CCWriterUtil.callStatic(Float.class, "valueOf", float.class);
    }

    public static void value(double i) {
        int valueIdx = ConstantPoolUtil.getOrCreateDouble(writer.constantPool, i);
        bytecodeWriter.putInstructionV(Opcodes.LDC, (short) valueIdx);
        CCWriterUtil.callStatic(Double.class, "valueOf", double.class);
    }

    public static void value(long i) {
        int valueIdx = ConstantPoolUtil.getOrCreateLong(writer.constantPool, i);
        bytecodeWriter.putInstructionV(Opcodes.LDC, (short) valueIdx);
        CCWriterUtil.callStatic(Long.class, "valueOf", long.class);
    }

    public static void value(String i) {
        int valueIdx = ConstantPoolUtil.getOrCreateString(writer.constantPool, i);
        bytecodeWriter.putInstructionV(Opcodes.LDC, (short) valueIdx);
    }

    public static void dup() {
        bytecodeWriter.putInstruction(Opcodes.DUP);
    }

    public static void nop() {
        bytecodeWriter.putInstruction(Opcodes.NOP);
    }

    public static void swap() {
        bytecodeWriter.putInstruction(Opcodes.SWAP);
    }
}
