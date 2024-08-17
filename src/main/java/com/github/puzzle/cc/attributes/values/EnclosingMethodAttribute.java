package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class EnclosingMethodAttribute extends AttributeInfo {

    short classIndex;
    short methodIndex;

    public EnclosingMethodAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        classIndex = reader.consumeU2();
        methodIndex = reader.consumeU2();
    }

}
