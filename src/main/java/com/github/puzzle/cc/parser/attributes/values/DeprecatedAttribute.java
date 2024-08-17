package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class DeprecatedAttribute extends AttributeInfo {

    public DeprecatedAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
    }

}
