package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface StackMapFrame {

    void writeToStream(DataOutputStream outputStream) throws IOException;

    Pair<Integer, StackMapFrameType> getType();
    static StackMapFrame readFrame(DataInputStream inp) throws IOException {
        Pair<Integer, StackMapFrameType> type = StackMapFrameType.getType(inp.readUnsignedByte());

        switch (type.b) {
            case SAME_FRAME:
                return new SameFrame(type, inp);
            case SAME_LOCALS_1_STACK_ITEM_FRAME:
                return new SameLocals1StackItemFrame(type, inp);
            case SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED:
                return new SameLocals1StackItemFrameExtended(type, inp);
            case CHOP_FRAME:
                return new ChopFrame(type, inp);
            case SAME_FRAME_EXTENDED:
                return new SameFrameExtended(type, inp);
            case APPEND_FRAME:
                return new AppendFrame(type, inp);
            case FULL_FRAME:
                return new FullFrame(type, inp);
            default:
                throw new IllegalArgumentException();
        }
    }

    enum StackMapFrameType {
        SAME_FRAME(0, 63),
        SAME_LOCALS_1_STACK_ITEM_FRAME(64, 126),
        SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED(247),
        CHOP_FRAME(248, 250),
        SAME_FRAME_EXTENDED(251),
        APPEND_FRAME(252, 254),
        FULL_FRAME(255);

        final byte min;
        final byte max;

        StackMapFrameType(int value) {
            this(value, value);
        }

        StackMapFrameType(int min, int max) {
            this.min = (byte) min;
            this.max = (byte) max;
        }

        boolean isInRange(byte value) {
            return min <= value && max >= value;
        }

        public static Pair<Integer, StackMapFrameType> getType(int b) {
            for (StackMapFrameType type : StackMapFrameType.values()) {
                if (type.isInRange((byte) b)) {
                    return new Pair<>(b, type);
                }
            }
            throw new RuntimeException("Invalid Frame " + b);
        }
    }
}
