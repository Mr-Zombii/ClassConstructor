package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class MethodHandleConstant implements ConstantPoolInfo {

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
