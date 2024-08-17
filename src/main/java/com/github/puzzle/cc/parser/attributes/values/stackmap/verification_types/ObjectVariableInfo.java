package com.github.puzzle.cc.parser.attributes.values.stackmap.verification_types;

import com.github.puzzle.cc.parser.ClassReader;

public class ObjectVariableInfo implements VerificationInfo {

    VerificationType type;
    short cpoolIndex;

    public ObjectVariableInfo(VerificationType type, ClassReader reader) {
        this.type = type;
        this.cpoolIndex = reader.consumeU2();
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
