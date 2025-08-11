package com.github.puzzle.cc.parsing.bytecode.opcodes.wide;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class TableSwitchInstruction implements Instruction {

    int opcode = 0;
    int defaultCaseOffset = 0;

    int padding = 0;
    int start = 0;
    int end = 0;
    int[] offsets = new int[0];

    @Override
    public void read(final AtomicInteger idx, short[] bytes) {
        padding = (idx.get() - 1) % 4;
        opcode = Instruction.readByte(idx, bytes);
        Instruction.skipBytes(idx, padding);
        defaultCaseOffset = Instruction.readInt(idx, bytes);
        start = Instruction.readInt(idx, bytes);
        end = Instruction.readInt(idx, bytes);

        int count = (end - start) + 1;
        offsets = new int[count];

        for (int i = 0; i < offsets.length; i++) {
            offsets[i] = Instruction.readInt(idx, bytes);
        }
    }

    @Override
    public void write(AtomicInteger idx, DataOutputStream stream) throws IOException {
        padding = idx.get() % 4;
        Instruction.writeByte(idx, stream, opcode);
        for (int i = 0; i < padding; i++) Instruction.writeByte(idx, stream, 0);
        Instruction.writeByte(idx, stream, (defaultCaseOffset >> 24) & 0xFF);
        Instruction.writeByte(idx, stream, (defaultCaseOffset >> 16) & 0xFF);
        Instruction.writeByte(idx, stream, (defaultCaseOffset >> 8) & 0xFF);
        Instruction.writeByte(idx, stream, defaultCaseOffset & 0xFF);

        Instruction.writeByte(idx, stream, (start >> 24) & 0xFF);
        Instruction.writeByte(idx, stream, (start >> 16) & 0xFF);
        Instruction.writeByte(idx, stream, (start >> 8) & 0xFF);
        Instruction.writeByte(idx, stream, start & 0xFF);

        Instruction.writeByte(idx, stream, (end >> 24) & 0xFF);
        Instruction.writeByte(idx, stream, (end >> 16) & 0xFF);
        Instruction.writeByte(idx, stream, (end >> 8) & 0xFF);
        Instruction.writeByte(idx, stream, end & 0xFF);

        for (int i = 0; i < getCaseCount(); i++) {
            int value = offsets[i];
            Instruction.writeByte(idx, stream, (value >> 24) & 0xFF);
            Instruction.writeByte(idx, stream, (value >> 16) & 0xFF);
            Instruction.writeByte(idx, stream, (value >> 8) & 0xFF);
            Instruction.writeByte(idx, stream, value & 0xFF);
        }
    }

    public int getCaseCount() {
        return (end - start) + 1;
    }

    public int getDefaultCaseOffset() {
        return defaultCaseOffset;
    }

    public int getPadding() {
        return padding;
    }

    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public int[] getOffsets() {
        return offsets;
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public String toString() {
        return Opcodes.INT_TO_STR.get(opcode) + " " + padding + " " + defaultCaseOffset + " " + start + " " + end;
    }

    public void set(int defaultOffs, int end, int start, int[] offsets) {
        this.defaultCaseOffset = defaultOffs;
        this.offsets = offsets;
        this.end = end;
        this.start = start;
    }
}
