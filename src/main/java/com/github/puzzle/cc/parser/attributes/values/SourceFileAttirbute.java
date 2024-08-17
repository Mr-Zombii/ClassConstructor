package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class SourceFileAttirbute extends AttributeInfo {

    short sourcefileAttribute;

    public SourceFileAttirbute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        sourcefileAttribute = reader.consumeU2();
    }
}
