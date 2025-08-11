package com.github.puzzle.cc.writer.stdlib;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.writer.ClassWriter;
import com.github.puzzle.cc.writer.bytecode.BytecodeWriter;

import java.io.PrintStream;

public class SystemLib {

    static ClassWriter writer;
    static BytecodeWriter bytecodeWriter;

    public static void use(ClassWriter w, BytecodeWriter b) {
        writer = w;
        bytecodeWriter = b;
    }

    public static void println() {
        CCWriterUtil.use(writer, bytecodeWriter);
        CCWriterUtil.getStatic(System.class, "out");
        bytecodeWriter.putInstruction(Opcodes.SWAP);
        CCWriterUtil.callVirtual(PrintStream.class, "println", Object.class);
    }

}
