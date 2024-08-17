package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class MethodRefConstant implements CP_INFO {

    TagType tag;
    short classIndex;
    short nameAndTypeIndex;

    public MethodRefConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.classIndex = reader.consumeU2();
        this.nameAndTypeIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
