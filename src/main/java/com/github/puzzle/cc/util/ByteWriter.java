package com.github.puzzle.cc.util;

import java.util.ArrayList;
import java.util.List;

public interface ByteWriter {

    byte[] toBytes();

    static byte[] u4ToBytes(int u4) {
        int b4 = (u4 >> 24) << 24;
        int b3 = ((u4 - b4) >> 16) << 16;
        int b2 = (((u4 - b4) - b3) >> 8) << 8;
        int b1 = ((u4 - b4) - b3) - b2;

        return new byte[] {(byte) (b4 >> 24), (byte) (b3 >> 16), (byte) (b2 >> 8), (byte) b1};
    }

    static byte[] u3ToBytes(int u3) {
        int b3 = (u3 >> 16) << 16;
        int b2 = ((u3 - b3) >> 8) << 8;
        int b1 = (u3 - b3) - b2;

        return new byte[] {(byte) (b3 >> 16), (byte) (b2 >> 8), (byte) b1};
    }

    static byte[] u2ToBytes(short u2) {
        int b2 = (u2 >> 8) << 8;
        int b1 = u2 - b2;

        return new byte[] {(byte) (b2 >> 8), (byte) b1};
    }

    static List<Byte> toByteList(byte[] byteArray) {
        List<Byte> bytes = new ArrayList<>();
        for (byte b : byteArray) {
            bytes.add(b);
        }
        return bytes;
    }

}
