package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class MethodTypeConstant implements ConstantPoolInfo {

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
