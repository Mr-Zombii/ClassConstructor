package com.github.puzzle.cc.attributes.values.stackmap.verification_types;

import com.github.puzzle.cc.ClassReader;

public class FloatVariableInfo implements VerificationInfo {

    VerificationType type;

    public FloatVariableInfo(VerificationType type, ClassReader reader) {
        this.type = type;
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
