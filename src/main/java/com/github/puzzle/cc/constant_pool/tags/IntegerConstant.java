package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class IntegerConstant implements CP_INFO {

    TagType tag;
    int bytes;

    public IntegerConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.bytes = reader.consumeU4();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
