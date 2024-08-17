package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class DoubleConstant implements ConstantPoolInfo {

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
