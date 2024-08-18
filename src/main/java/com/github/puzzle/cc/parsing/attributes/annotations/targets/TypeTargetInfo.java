package com.github.puzzle.cc.parsing.attributes.annotations.targets;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public interface TypeTargetInfo {

    void writeToStream(DataOutputStream outputStream) throws IOException;

    TypeTargetInfoType getType();

    static TypeTargetInfo readTargetInfo(DataInputStream inp) throws IOException {
        Pair<Byte, TypeTargetInfoType> type = TypeTargetInfoType.getType(inp.readByte());

        return switch (type.b) {
            case TYPE_PARAMETER_TARGET -> new TypeParameterTarget(type, inp);
            case SUPER_TYPE_TARGET -> new SuperTypeTarget(type, inp);
            case TYPE_PARAMETER_BOUND_TARGET -> new TypeParameterBoundTarget(type, inp);
            case EMPTY_TARGET -> new EmptyTarget(type, inp);
            case FORMAL_PARAMETER_TARGET -> new FormatParameterTarget(type, inp);
            case THROWS_TARGET -> new ThrowsTarget(type, inp);
            case LOCAL_VAR_TARGET -> new LocalVarTarget(type, inp);
            case CATCH_TARGET -> new CatchTarget(type, inp);
            case OFFSET_TARGET -> new OffsetTarget(type, inp);
            case TYPE_ARGUMENT_TARGET -> new TypeArgumentTarget(type, inp);
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