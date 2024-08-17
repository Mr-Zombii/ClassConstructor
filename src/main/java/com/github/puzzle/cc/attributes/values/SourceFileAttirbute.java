package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class SourceFileAttirbute extends AttributeInfo {

    short sourcefileAttribute;

    public SourceFileAttirbute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        sourcefileAttribute = reader.consumeU2();
    }
}
