package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class ModulePackagesAttribute extends AttributeInfo {

    short packagesCount;
    short[] packagesIndex;

    public ModulePackagesAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        packagesCount = reader.consumeU2();
        packagesIndex = new short[packagesCount];
        for (int i = 0; i < packagesCount; i++) {
            packagesIndex[i] = reader.consumeU2();
        }

    }
}
