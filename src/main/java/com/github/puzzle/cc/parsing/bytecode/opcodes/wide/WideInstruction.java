package com.github.puzzle.cc.parsing.bytecode.opcodes.wide;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class WideInstruction implements Instruction {

    int opcode = 0;
    int wrappedOpcode = 0;
    int address0 = 0;
    int address1 = 0;

    public int getWrappedOpcode() {
        return wrappedOpcode;
    }

    public int getAddress0() {
        return address0;
    }

    public int getAddress1() {
        return address1;
    }

    @Override
    public void read(AtomicInteger idx, short[] bytes) {
        opcode = Instruction.readByte(idx, bytes);
        wrappedOpcode = Instruction.readByte(idx, bytes);
        address0 = Instruction.readShort(idx, bytes);
        if (opcode == Opcodes.IINC)
            address1 = Instruction.readShort(idx, bytes);
    }

    @Override
    public void write(AtomicInteger idx, DataOutputStream stream) throws IOException {
        Instruction.writeByte(idx, stream, opcode);
        Instruction.writeByte(idx, stream, wrappedOpcode);
        Instruction.writeByte(idx, stream, (address0 >> 8) & 0xFF);
        Instruction.writeByte(idx, stream, address0 & 0xFF);
        if (wrappedOpcode == Opcodes.IINC) {
            Instruction.writeByte(idx, stream, (address1 >> 8) & 0xFF);
            Instruction.writeByte(idx, stream, address1 & 0xFF);
        }
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public String toString() {
        if (wrappedOpcode == Opcodes.IINC) {
            return Opcodes.INT_TO_STR.get(opcode) + "+" + Opcodes.INT_TO_STR.get(wrappedOpcode) + " " + address0 + " | " + address1;
        } else {
            return Opcodes.INT_TO_STR.get(opcode) + "+" + Opcodes.INT_TO_STR.get(wrappedOpcode) + " " + address0;
        }
    }

    public void set(int opcode, short address0, short address1) {
        this.opcode = Opcodes.WIDE;
        this.wrappedOpcode = opcode;
        this.address0 = address0;
        this.address1 = address1;
    }
}
