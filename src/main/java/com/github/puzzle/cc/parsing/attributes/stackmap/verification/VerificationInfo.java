package com.github.puzzle.cc.parsing.attributes.stackmap.verification;

import java.io.DataInputStream;
import java.io.IOException;

public interface VerificationInfo {

    VerificationType getType();

    static VerificationInfo readType(DataInputStream inp) throws IOException {
        VerificationType type = VerificationType.getType(inp.readByte());

        return switch (type) {
            case TOP_VARIABLE_INFO -> new TopVariableInfo(type, inp);
            case INTEGER_VARIABLE_INFO -> new IntegerVariableInfo(type, inp);
            case FLOAT_VARIABLE_INFO -> new FloatVariableInfo(type, inp);
            case LONG_VARIABLE_INFO -> new LongVariableInfo(type, inp);
            case DOUBLE_VARIABLE_INFO -> new DoubleVariableInfo(type, inp);
            case NULL_VARIABLE_INFO -> new NullVariableInfo(type, inp);
            case UNINITIALIZED_THIS_VARIABLE_INFO -> new UninitializedThisVariableInfo(type, inp);
            case OBJECT_VARIABLE_INFO -> new ObjectVariableInfo(type, inp);
            case UNINITIALIZED_VARIABLE_INFO -> new UninitializedVariableInfo(type, inp);
        };
    }

    enum VerificationType {
        TOP_VARIABLE_INFO(0),
        INTEGER_VARIABLE_INFO(1),
        FLOAT_VARIABLE_INFO(2),
        LONG_VARIABLE_INFO(3),
        DOUBLE_VARIABLE_INFO(4),
        NULL_VARIABLE_INFO(5),
        UNINITIALIZED_THIS_VARIABLE_INFO(6),
        OBJECT_VARIABLE_INFO(7),
        UNINITIALIZED_VARIABLE_INFO(8);

        final byte infoOrdinal;

        VerificationType(int value) {
            this.infoOrdinal = (byte) value;
        }

        public static VerificationType getType(byte b) {
            for (VerificationType type : VerificationType.values()) {
                if (type.infoOrdinal == b) return type;
            }
            return TOP_VARIABLE_INFO;
        }
    }

}
