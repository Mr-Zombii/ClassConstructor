package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class EnclosingMethodAttribute extends AttributeInfo {

    short classIndex;
    short methodIndex;

    public EnclosingMethodAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        classIndex = reader.consumeU2();
        methodIndex = reader.consumeU2();
    }

}
