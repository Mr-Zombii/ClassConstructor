package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class BootstrapMethodsAttribute extends AttributeInfo {

    short bootstrapMethodsCount;

    public BootstrapMethodsAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);
        bootstrapMethodsCount = reader.consumeU2();
    }

    public static class BootstrapMethod {

        short bootstrapMethodRef;
        short bootstrapArgumentsCount;
        short[] bootstrapArguments;

        public BootstrapMethod(ClassReader reader) {
            bootstrapMethodRef = reader.consumeU2();

            bootstrapArgumentsCount = reader.consumeU2();
            bootstrapArguments = new short[bootstrapArgumentsCount];
            for (int i = 0; i < bootstrapArgumentsCount; i++) {
                bootstrapArguments[i] = reader.consumeU2();
            }
        }

    }

}
