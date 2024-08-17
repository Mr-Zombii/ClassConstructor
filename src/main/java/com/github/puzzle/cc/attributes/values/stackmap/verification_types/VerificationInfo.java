package com.github.puzzle.cc.attributes.values.stackmap.verification_types;

import com.github.puzzle.cc.ClassReader;

public interface VerificationInfo {

    VerificationType getType();

    static VerificationInfo readType(ClassReader reader) {
        VerificationType type = VerificationType.getType(reader.consumeU1());

        return switch (type) {
            case TOP_VARIABLE_INFO -> new TopVariableInfo(type, reader);
            case INTEGER_VARIABLE_INFO -> new IntegerVariableInfo(type, reader);
            case FLOAT_VARIABLE_INFO -> new FloatVariableInfo(type, reader);
            case LONG_VARIABLE_INFO -> new LongVariableInfo(type, reader);
            case DOUBLE_VARIABLE_INFO -> new DoubleVariableInfo(type, reader);
            case NULL_VARIABLE_INFO -> new NullVariableInfo(type, reader);
            case UNINITIALIZED_THIS_VARIABLE_INFO -> new UninitializedThisVariableInfo(type, reader);
            case OBJECT_VARIABLE_INFO -> new ObjectVariableInfo(type, reader);
            case UNINITIALIZED_VARIABLE_INFO -> new UninitializedVariableInfo(type, reader);
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
