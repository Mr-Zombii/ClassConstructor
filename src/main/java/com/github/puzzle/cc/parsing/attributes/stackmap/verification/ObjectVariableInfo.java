package com.github.puzzle.cc.parsing.attributes.stackmap.verification;

import java.io.DataInputStream;
import java.io.IOException;

public class ObjectVariableInfo implements VerificationInfo {

    VerificationType type;
    int cpoolIndex;

    public ObjectVariableInfo(VerificationType type, DataInputStream inp) throws IOException {
        this.type = type;
        this.cpoolIndex = inp.readUnsignedShort();
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
