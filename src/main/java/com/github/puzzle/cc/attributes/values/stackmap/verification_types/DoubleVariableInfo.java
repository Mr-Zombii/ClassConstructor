package com.github.puzzle.cc.attributes.values.stackmap.verification_types;

import com.github.puzzle.cc.ClassReader;

public class DoubleVariableInfo implements VerificationInfo {

    VerificationType type;

    public DoubleVariableInfo(VerificationType type, ClassReader reader) {
        this.type = type;
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
