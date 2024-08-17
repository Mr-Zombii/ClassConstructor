package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class ModuleMainClassAttribute extends AttributeInfo {

    short mainClassIndex;

    public ModuleMainClassAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        mainClassIndex = reader.consumeU2();
    }
}
