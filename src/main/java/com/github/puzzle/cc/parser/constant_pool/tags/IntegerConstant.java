package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class IntegerConstant implements ConstantPoolInfo {

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
