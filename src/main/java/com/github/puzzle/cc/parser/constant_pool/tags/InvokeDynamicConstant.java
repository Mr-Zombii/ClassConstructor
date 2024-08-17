package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class InvokeDynamicConstant implements ConstantPoolInfo {

    TagType tag;
    short bootstrapMethodAttrIndex;
    short nameAndTypeIndex;

    public InvokeDynamicConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.bootstrapMethodAttrIndex = reader.consumeU2();
        this.nameAndTypeIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
