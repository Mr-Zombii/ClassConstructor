package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class FloatConstant implements ConstantPoolInfo {

    TagType tag;
    int bits;

    public FloatConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.bits = reader.consumeU4();
    }

    public float asFloat() {
        return Float.intBitsToFloat(bits);
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
