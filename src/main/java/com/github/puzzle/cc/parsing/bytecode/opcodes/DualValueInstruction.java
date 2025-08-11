package com.github.puzzle.cc.parsing.bytecode.opcodes;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class DualValueInstruction implements Instruction {

    int opcode = 0;
    int value0 = 0;
    int value1 = 0;

    @Override
    public void read(AtomicInteger idx, short[] bytes) {
        opcode = Instruction.readByte(idx, bytes);
        value0 = Instruction.readByte(idx, bytes);
        value1 = Instruction.readByte(idx, bytes);
    }

    @Override
    public void write(AtomicInteger idx, DataOutputStream stream) throws IOException {
        Instruction.writeByte(idx, stream, opcode);
        Instruction.writeByte(idx, stream, value0);
        Instruction.writeByte(idx, stream, value1);
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    public int getValue0() {
        return value0;
    }

    public int getValue1() {
        return value1;
    }

    @Override
    public String toString() {
        return Opcodes.INT_TO_STR.get(opcode) + " " + value0 + " " + value1;
    }

    public void set(int opcode, short value0, short value1) {
        this.opcode = opcode;
        this.value0 = value0;
        this.value1 = value1;
    }
}
