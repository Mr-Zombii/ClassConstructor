package com.github.puzzle.cc.parsing.bytecode.opcodes.wide;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class LookupSwitchInstruction implements Instruction {

    int opcode = 0;
    int defaultCaseOffset = 0;

    int padding = 0;
    int pairCount = 0;
    int[] keys = new int[0];
    int[] values = new int[0];

    public int getPadding() {
        return padding;
    }

    public int getCaseCount() {
        return pairCount;
    }

    public int getDefaultCaseOffset() {
        return defaultCaseOffset;
    }

    public int[] getKeys() {
        return keys;
    }

    public int[] getValues() {
        return values;
    }

    @Override
    public void read(final AtomicInteger idx, short[] bytes) {
        padding = (idx.get() - 1) % 4;
        opcode = Instruction.readByte(idx, bytes);
        Instruction.skipBytes(idx, padding);
        defaultCaseOffset = Instruction.readInt(idx, bytes);
        pairCount = Instruction.readInt(idx, bytes);

        keys = new int[pairCount];
        values = new int[pairCount];

        for (int i = 0; i < pairCount; i++) {
            keys[i] = Instruction.readInt(idx, bytes);
            values[i] = Instruction.readInt(idx, bytes);
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

        pairCount = keys.length;

        Instruction.writeByte(idx, stream, (pairCount >> 24) & 0xFF);
        Instruction.writeByte(idx, stream, (pairCount >> 16) & 0xFF);
        Instruction.writeByte(idx, stream, (pairCount >> 8) & 0xFF);
        Instruction.writeByte(idx, stream, pairCount & 0xFF);

        for (int i = 0; i < pairCount; i++) {
            int value = keys[i];
            Instruction.writeByte(idx, stream, (value >> 24) & 0xFF);
            Instruction.writeByte(idx, stream, (value >> 16) & 0xFF);
            Instruction.writeByte(idx, stream, (value >> 8) & 0xFF);
            Instruction.writeByte(idx, stream, value & 0xFF);

            value = values[i];
            Instruction.writeByte(idx, stream, (value >> 24) & 0xFF);
            Instruction.writeByte(idx, stream, (value >> 16) & 0xFF);
            Instruction.writeByte(idx, stream, (value >> 8) & 0xFF);
            Instruction.writeByte(idx, stream, value & 0xFF);
        }
    }

    @Override
    public int getOpcode() {
        return opcode;
    }

    @Override
    public String toString() {
        return Opcodes.INT_TO_STR.get(opcode) + " " + padding + " " + defaultCaseOffset + " " + pairCount;
    }

    public void set(int defaultOffs, int[] keys, int[] values) {
        this.defaultCaseOffset = defaultOffs;
        this.keys = keys;
        this.values = values;
    }
}
