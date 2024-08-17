package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class SignatureAttribute extends AttributeInfo {

    short signatureIndex;

    public SignatureAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        signatureIndex = reader.consumeU2();
    }
}
