package com.github.puzzle.cc.parser.attributes.values.stackmap.verification_types;

import com.github.puzzle.cc.parser.ClassReader;

public class TopVariableInfo implements VerificationInfo {

    VerificationType type;

    public TopVariableInfo(VerificationType type, ClassReader reader) {
        this.type = type;
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
