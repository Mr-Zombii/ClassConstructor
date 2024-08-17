package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class DynamicConstant implements CP_INFO {

    TagType tag;
    short bootstrapMethodAttrIndex;
    short nameAndTypeIndex;

    public DynamicConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.bootstrapMethodAttrIndex = reader.consumeU2();
        this.nameAndTypeIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
