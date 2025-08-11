package com.github.puzzle.cc.parsing.bytecode.opcodes.wide;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class WideAddressInstruction implements Instruction {

    int opcode = 0;
    int address = 0;

    @Override
    public void read(AtomicInteger idx, short[] bytes) {
        opcode = Instruction.readByte(idx, bytes);
        address = Instruction.readInt(idx, bytes);
    }

    @Override
    public void write(AtomicInteger idx, DataOutputStream stream) throws IOException {
        Instruction.writeByte(idx, stream, opcode);
        Instruction.writeByte(idx, stream, (address >> 24) & 0xFF);
        Instruction.writeByte(idx, stream, (address >> 16) & 0xFF);
        Instruction.writeByte(idx, stream, (address >> 8) & 0xFF);
        Instruction.writeByte(idx, stream, address & 0xFF);
    }

    public int getAddress() {
        return address;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public String toString() {
        return Opcodes.INT_TO_STR.get(opcode) + " " + address;
    }

    public void set(int opcode, int address) {
        this.opcode = opcode;
        this.address = address;
    }
}
