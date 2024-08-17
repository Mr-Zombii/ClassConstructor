package com.github.puzzle.cc.attributes.values.stackmap.verification_types;

import com.github.puzzle.cc.ClassReader;

public class UninitializedVariableInfo implements VerificationInfo {

    VerificationType type;
    short offset;

    public UninitializedVariableInfo(VerificationType type, ClassReader reader) {
        this.type = type;
        this.offset = reader.consumeU2();
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
