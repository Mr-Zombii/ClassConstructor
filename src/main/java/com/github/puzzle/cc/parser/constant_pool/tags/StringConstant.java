package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class StringConstant implements ConstantPoolInfo {

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
