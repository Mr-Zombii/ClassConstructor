package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class NameAndTypeConstant implements CP_INFO {

    TagType tag;
    short nameIndex;
    short descriptorIndex;

    public NameAndTypeConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.nameIndex = reader.consumeU2();
        this.descriptorIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
