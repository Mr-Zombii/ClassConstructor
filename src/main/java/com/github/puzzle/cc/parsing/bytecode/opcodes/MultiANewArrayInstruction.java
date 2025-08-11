package com.github.puzzle.cc.parsing.bytecode.opcodes;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class MultiANewArrayInstruction implements Instruction {

    int opcode = 0;
    int address = 0;
    int dimensions = 0;

    @Override
    public void read(AtomicInteger idx, short[] bytes) {
        opcode = Instruction.readByte(idx, bytes);
        address = Instruction.readShort(idx, bytes);
        dimensions = Instruction.readByte(idx, bytes);
    }

    @Override
    public void write(AtomicInteger idx, DataOutputStream stream) throws IOException {
        Instruction.writeByte(idx, stream, opcode);
        Instruction.writeByte(idx, stream, (address >> 8) & 0xFF);
        Instruction.writeByte(idx, stream, address & 0xFF);
        Instruction.writeByte(idx, stream, dimensions);
    }

    public int getAddress() {
        return address;
    }

    public int getDimensions() {
        return dimensions;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public String toString() {
        return Opcodes.INT_TO_STR.get(opcode) + " " + address;
    }

    public void set(int opcode, short address, short value) {
        this.opcode = opcode;
        this.address = address;
        this.dimensions = value;
    }
}
