package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class MethodHandleConstant implements CP_INFO {

    TagType tag;
    byte referenceKind;
    short referenceIndex;

    public MethodHandleConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.referenceKind = reader.consumeU1();
        this.referenceIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
