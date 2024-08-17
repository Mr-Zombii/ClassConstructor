package com.github.puzzle.cc.constant_pool.tags;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.TagType;

public class UTF8CONSTANT implements CP_INFO {

    TagType tag;
    short length;
    byte[] bytes;

    public UTF8CONSTANT(TagType type, ClassReader reader) {
        this.tag = type;
        this.length = reader.consumeU2();
        this.bytes = new byte[length];

        for (int i = 0; i < length; i++) {
            this.bytes[i] = reader.consumeU1();
        }
    }

    public String asString() {
        return new String(bytes);
    }

    @Override
    public TagType getTag() {
        return tag;
    }
}
