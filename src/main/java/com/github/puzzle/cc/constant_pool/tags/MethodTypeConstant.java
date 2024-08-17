package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class MethodTypeConstant implements CP_INFO {

    TagType tag;
    short descriptorIndex;

    public MethodTypeConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.descriptorIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
