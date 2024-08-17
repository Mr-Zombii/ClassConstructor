package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class StringConstant implements CP_INFO {

    TagType tag;
    short stringIndex;

    public StringConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.stringIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
