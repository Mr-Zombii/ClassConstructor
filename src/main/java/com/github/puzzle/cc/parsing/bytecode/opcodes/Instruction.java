package com.github.puzzle.cc.parsing.bytecode.opcodes;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public interface Instruction {

    void read(final AtomicInteger idx, short[] bytes);

    static int readByte(AtomicInteger idx, short[] bytes) {
        return bytes[idx.getAndIncrement()];
    }
    static int readShort(AtomicInteger idx, short[] bytes) {
        return readByte(idx, bytes) << 8
                | readByte(idx, bytes);
    }
    static int readInt(AtomicInteger idx, short[] bytes) {
        return readShort(idx, bytes) << 16
                | readShort(idx, bytes);
    }
    static void skipBytes(AtomicInteger idx, int skip) {
        idx.set(idx.get() + skip);
    }

    int getOpcode();

    default String getOpcodeStr() {
        return Opcodes.INT_TO_STR.get(getOpcode());
    }

    void write(AtomicInteger idx, DataOutputStream stream) throws IOException;

    static void writeByte(AtomicInteger idx, DataOutputStream stream, int i) throws IOException {
        idx.set(idx.get() + 1);
        stream.writeByte(i);
    }

    static void writeShort(AtomicInteger idx, DataOutputStream stream, int i) throws IOException {
        idx.set(idx.get() + 2);
        stream.writeShort(i);
    }

    static void writeInt(AtomicInteger idx, DataOutputStream stream, int i) throws IOException {
        idx.set(idx.get() + 4);
        stream.writeInt(i);
    }
}
