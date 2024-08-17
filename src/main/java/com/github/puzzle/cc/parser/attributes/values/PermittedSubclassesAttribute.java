package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class PermittedSubclassesAttribute extends AttributeInfo {

    short numberOfClasses;
    short[] classes;

    public PermittedSubclassesAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        numberOfClasses = reader.consumeU2();
        classes = new short[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = reader.consumeU2();
        }

    }
}
