package com.github.puzzle.cc.parsing.attributes.stackmap.verification;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DoubleVariableInfo implements VerificationInfo {

    VerificationType type;

    public DoubleVariableInfo(VerificationType type, DataInputStream inp) throws IOException {
        this.type = type;
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(type.infoOrdinal);
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
