package com.github.puzzle.cc.parsing.bytecode.opcodes;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class NoValueInstruction implements Instruction {

    int opcode = 0;

    @Override
    public void read(AtomicInteger idx, short[] bytes) {
        opcode = Instruction.readByte(idx, bytes);
    }

    public void set(int opcode) {
        this.opcode = opcode;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public void write(AtomicInteger idx, DataOutputStream stream) throws IOException {
        Instruction.writeByte(idx, stream, opcode);
    }

    @Override
    public String toString() {
        return Opcodes.INT_TO_STR.get(opcode);
    }

}
