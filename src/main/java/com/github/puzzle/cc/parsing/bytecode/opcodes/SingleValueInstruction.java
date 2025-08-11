package com.github.puzzle.cc.parsing.bytecode.opcodes;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class SingleValueInstruction implements Instruction {

    int opcode = 0;
    int value = 0;

    @Override
    public void read(AtomicInteger idx, short[] bytes) {
        opcode = Instruction.readByte(idx, bytes);
        value = Instruction.readByte(idx, bytes);
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public void write(AtomicInteger idx, DataOutputStream stream) throws IOException {
        Instruction.writeByte(idx, stream, opcode);
        Instruction.writeByte(idx, stream, value);
    }

    @Override
    public String toString() {
        return Opcodes.INT_TO_STR.get(opcode) + " " + value;
    }

    public int getValue() {
        return value;
    }

    public void set(int opcode, short value) {
        this.opcode = opcode;
        this.value = value;
    }
}
