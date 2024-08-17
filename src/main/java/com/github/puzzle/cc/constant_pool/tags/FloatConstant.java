package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class FloatConstant implements CP_INFO {

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
