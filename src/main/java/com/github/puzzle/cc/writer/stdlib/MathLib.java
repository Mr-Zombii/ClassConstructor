package com.github.puzzle.cc.writer.stdlib;

import com.github.puzzle.cc.writer.ClassWriter;
import com.github.puzzle.cc.writer.bytecode.BytecodeWriter;

public class MathLib {

    static ClassWriter writer;
    static BytecodeWriter bytecodeWriter;

    public static void use(ClassWriter w, BytecodeWriter b) {
        writer = w;
        bytecodeWriter = b;
    }

    /// {@link Math#E}
    public static void E() {
        CCWriterUtil.use(writer, bytecodeWriter);
        CCWriterUtil.getStatic(Math.class, "sin");
    }

    /// {@link Math#PI}
    public static void PI() {
        CCWriterUtil.use(writer, bytecodeWriter);
        CCWriterUtil.getStatic(Math.class, "PI");
    }

    /// {@link Math#sin(double)}
    public static void sin() {
        CCWriterUtil.use(writer, bytecodeWriter);
        CCWriterUtil.callStatic(Math.class, "sin", double.class);
    }

}
