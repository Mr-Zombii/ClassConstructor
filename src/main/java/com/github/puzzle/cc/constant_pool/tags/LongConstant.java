package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class LongConstant implements CP_INFO {

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
