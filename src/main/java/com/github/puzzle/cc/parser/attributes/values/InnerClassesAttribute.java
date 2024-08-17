package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassAccessFlag;
import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class InnerClassesAttribute extends AttributeInfo {

    short numberOfClasses;
    InnerClass[] classes;

    public InnerClassesAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        numberOfClasses = reader.consumeU2();
        classes = new InnerClass[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = new InnerClass(reader);
        }
    }

    public static class InnerClass {

        short innerClassInfoIndex;
        short outerClassInfoIndex;
        short innerNameIndex;
        ClassAccessFlag[] accessFlags;

        public InnerClass(ClassReader reader) {
            innerClassInfoIndex = reader.consumeU2();
            outerClassInfoIndex = reader.consumeU2();
            innerNameIndex = reader.consumeU2();
            accessFlags = ClassAccessFlag.getFlagsFromU2(reader.consumeU2());
        }

    }
}
