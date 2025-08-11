package com.github.puzzle.cc.parsing.bytecode;

import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BytecodeReader {

    public static Collection<Instruction> read(short[] bytes) {
        List<Instruction> opcodes = new ArrayList<>();
        try {
            AtomicInteger idx = new AtomicInteger(0);
            while (idx.get() < bytes.length) {
                int opcodeNum = bytes[idx.get()];
                Class<? extends Instruction> opcodeClazz = InstructionParsers.instructionParsers.get(opcodeNum);
                if (opcodeClazz == null) {
                    throw new RuntimeException("Unsupported opcode " + Opcodes.INT_TO_STR.get(opcodeNum) + " | " + Integer.toHexString(opcodeNum));
                }
                Instruction opcode = opcodeClazz.getConstructor().newInstance();
                opcode.read(idx, bytes);
                opcodes.add(opcode);
            }
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                 NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return opcodes;
    }

}
