package com.github.puzzle.cc.parser.attributes.values.annotations.type_targets;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public interface TypeTargetInfo {

    TypeTargetInfoType getType();

    static TypeTargetInfo readTargetInfo(ClassReader reader) {
        Pair<Byte, TypeTargetInfoType> type = TypeTargetInfoType.getType(reader.consumeU1());

        return switch (type.b) {
            case TYPE_PARAMETER_TARGET -> new TypeParameterTarget(type, reader);
            case SUPER_TYPE_TARGET -> new SuperTypeTarget(type, reader);
            case TYPE_PARAMETER_BOUND_TARGET -> new TypeParameterBoundTarget(type, reader);
            case EMPTY_TARGET -> new EmptyTarget(type, reader);
            case FORMAL_PARAMETER_TARGET -> new FormatParameterTarget(type, reader);
            case THROWS_TARGET -> new ThrowsTarget(type, reader);
            case LOCAL_VAR_TARGET -> new LocalVarTarget(type, reader);
            case CATCH_TARGET -> new CatchTarget(type, reader);
            case OFFSET_TARGET -> new OffsetTarget(type, reader);
            case TYPE_ARGUMENT_TARGET -> new TypeArgumentTarget(type, reader);
        };
    }

    enum TypeTargetInfoType {

        TYPE_PARAMETER_TARGET((byte) 0x00, (byte) 0x01),
        SUPER_TYPE_TARGET((byte) 0x10),
        TYPE_PARAMETER_BOUND_TARGET((byte) 0x11, (byte) 0x12),
        EMPTY_TARGET((byte) 0x13, (byte) 0x14, (byte) 0x15),
        FORMAL_PARAMETER_TARGET((byte) 0x16),
        THROWS_TARGET((byte) 0x17),
        LOCAL_VAR_TARGET((byte) 0x40, (byte) 0x41),
        CATCH_TARGET((byte) 0x42),
        OFFSET_TARGET((byte) 0x43, (byte) 0x44, (byte) 0x45, (byte) 0x46),
        TYPE_ARGUMENT_TARGET((byte) 0x47, (byte) 0x48, (byte) 0x49, (byte) 0x4A, (byte) 0x4B);

        final byte[] bytes;

        TypeTargetInfoType(byte... values) {
            bytes = values;
        }

        boolean isOfType(byte b) {
            for (byte t : bytes) {
                if (t == b) return true;
            }
            return false;
        }

        public static Pair<Byte, TypeTargetInfoType> getType(byte b) {
            for (TypeTargetInfoType type : values()) {
                if (type.isOfType(b)) return new Pair<>(b, type);
            }
            return new Pair<>((byte) 0, EMPTY_TARGET);
        }

    }



}