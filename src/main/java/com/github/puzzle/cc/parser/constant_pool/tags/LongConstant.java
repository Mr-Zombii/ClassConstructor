package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class LongConstant implements ConstantPoolInfo {

    TagType tag;
    int highBytes;
    int lowBytes;

    public LongConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.highBytes = reader.consumeU4();
        this.lowBytes = reader.consumeU4();
    }

    public long asLong() {
        return ((long) highBytes << 32) + lowBytes;
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
