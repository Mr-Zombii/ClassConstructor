package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class SourceDebugExtension extends AttributeInfo {

    byte[] debugExtension;

    public SourceDebugExtension(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        debugExtension = new byte[length];
        for (int i = 0; i < length; i++) {
            debugExtension[i] = reader.consumeU1();
        }
    }
}
