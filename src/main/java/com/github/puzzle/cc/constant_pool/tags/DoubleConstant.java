package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class DoubleConstant implements CP_INFO {

    TagType tag;
    int highBytes;
    int lowBytes;

    public DoubleConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.highBytes = reader.consumeU4();
        this.lowBytes = reader.consumeU4();
    }

    public double asDouble() {
        long bits = ((long) highBytes << 32) + lowBytes;
        return Double.longBitsToDouble(bits);
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
