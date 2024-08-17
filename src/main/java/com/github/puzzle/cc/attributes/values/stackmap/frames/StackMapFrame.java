package com.github.puzzle.cc.attributes.values.stackmap.frames;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.util.Pair;

public interface StackMapFrame {

    Pair<Byte, StackMapFrameType> getType();
    static StackMapFrame readFrame(ClassReader reader) {
        Pair<Byte, StackMapFrameType> type = StackMapFrameType.getType(reader.consumeU1());

        return switch (type.b) {
            case SAME_FRAME -> new SameFrame(type, reader);
            case SAME_LOCALS_1_STACK_ITEM_FRAME -> new SameLocals1StackItemFrame(type, reader);
            case SAME_LOCALS_1_STACK_ITEM_FRAME_EXTENDED -> new SameLocals1StackItemFrameExtended(type, reader);
            case CHOP_FRAME -> new ChopFrame(type, reader);
            case SAME_FRAME_EXTENDED -> new SameFrameExtended(type, reader);
            case APPEND_FRAME -> new AppendFrame(type, reader);
            case FULL_FRAME -> new FullFrame(type, reader);
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

        public static Pair<Byte, StackMapFrameType> getType(byte b) {
            for (StackMapFrameType type : StackMapFrameType.values()) {
                if (type.isInRange(b)) return new Pair<>(b, type);
            }
            return new Pair<>((byte) 0, SAME_FRAME);
        }
    }
}
