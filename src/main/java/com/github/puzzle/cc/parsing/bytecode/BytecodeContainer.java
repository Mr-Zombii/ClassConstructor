package com.github.puzzle.cc.parsing.bytecode;

import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class BytecodeContainer {

    public List<Instruction> instructions = new ArrayList<>();

    public BytecodeContainer() {}

    public BytecodeContainer(List<Instruction> instructions) {
        this.instructions = instructions;
    }

    public static BytecodeContainer make(short[] code) {
        BytecodeContainer container = new BytecodeContainer();
        container.instructions.addAll(BytecodeReader.read(code));
        return container;
    }

    public static BytecodeContainer make(List<Instruction> opcodes) {
        BytecodeContainer container = new BytecodeContainer();
        container.instructions.addAll(opcodes);
        return container;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[\n");

        for (int i = 0; i < instructions.size(); i++) {
            builder.append("\t");
            builder.append(instructions.get(i).toString());
            if (i != instructions.size() - 1) builder.append(",");
            builder.append("\n");
        }

        return builder.append("]").toString();
    }

    public String asString(ConstantPool pool) {
        StringBuilder builder = new StringBuilder("[\n");

        for (int i = 0; i < instructions.size(); i++) {
            builder.append("\t");
            builder.append(
                    InstructionParsers.instructionStringers.get(
                            instructions.get(i).getOpcode()
                    ).apply(instructions.get(i), pool)
            );
            if (i != instructions.size() - 1) builder.append(",");
            builder.append("\n");
        }

        return builder.append("]").toString();
    }

    public List<Instruction> getInstructions() {
        return instructions;
    }

    public int write(DataOutputStream stream) throws IOException {
        AtomicInteger idx = new AtomicInteger(0);
        for (Instruction instruction : instructions) {
            instruction.write(idx, stream);
        }
        return idx.get();
    }
}
