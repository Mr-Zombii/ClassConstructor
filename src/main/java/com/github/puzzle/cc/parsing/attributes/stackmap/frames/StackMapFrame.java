package com.github.puzzle.cc.parsing.attributes.stackmap.frames;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public interface StackMapFrame {

    Pair<Integer, StackMapFrameType> getType();
    static StackMapFrame readFrame(DataInputStream inp) throws IOException {
        Pair<Integer, StackMapFrameType> type = StackMapFrameType.getType(inp.readUnsignedByte());

        return switch (type.b) {
            case SAME_FRAME -> new SameFrame(type, inp);
            case SAME_LOCALS_1_STACK_ITEM_FRAME -> new SameLocals1StackItemFrame(type, inp);
            case SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED -> new SameLocals1StackItemFrameExtended(type, inp);
            case CHOP_FRAME -> new ChopFrame(type, inp);
            case SAME_FRAME_EXTENDED -> new SameFrameExtended(type, inp);
            case APPEND_FRAME -> new AppendFrame(type, inp);
            case FULL_FRAME -> new FullFrame(type, inp);
        };
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
                if (type.isInRange((byte) b)) return new Pair<>(b, type);
            }
            return new Pair<>(0, SAME_FRAME);
        }
    }
}
