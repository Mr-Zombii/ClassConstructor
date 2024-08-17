package com.github.puzzle.cc.parser.constant_pool.tags;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.constant_pool.ConstantPoolInfo;
import com.github.puzzle.cc.parser.constant_pool.TagType;

public class PackageConstant implements ConstantPoolInfo {

    TagType tag;
    short nameIndex;

    public PackageConstant(TagType type, ClassReader reader) {
        this.tag = type;
        this.nameIndex = reader.consumeU2();
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
