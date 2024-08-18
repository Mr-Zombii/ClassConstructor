package com.github.puzzle.cc.parsing.attributes.stackmap.verification;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class UninitializedVariableInfo implements VerificationInfo {

    VerificationType type;
    int offset;

    public UninitializedVariableInfo(VerificationType type, DataInputStream inp) throws IOException {
        this.type = type;
        this.offset = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeByte(type.infoOrdinal);
        outputStream.writeShort(offset);
    }

    @Override
    public VerificationType getType() {
        return type;
    }
}
