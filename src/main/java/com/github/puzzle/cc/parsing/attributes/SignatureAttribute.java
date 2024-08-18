package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class SignatureAttribute extends AttributeInfo {

    int signatureIndex;

    public SignatureAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        signatureIndex = inp.readUnsignedShort();
    }
}
