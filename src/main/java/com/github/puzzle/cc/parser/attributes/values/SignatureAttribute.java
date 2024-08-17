package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;

public class SignatureAttribute extends AttributeInfo {

    short signatureIndex;

    public SignatureAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        signatureIndex = reader.consumeU2();
    }
}
