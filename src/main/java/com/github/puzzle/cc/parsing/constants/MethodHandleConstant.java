package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class MethodHandleConstant extends GenericConstant {

    byte referenceKind;
    int referenceIndex;

    public MethodHandleConstant(ReferenceKind referenceKind, int referenceIndex) {
        super(ConstantPool.TagType.CONSTANT_METHOD_HANDLE, null);
        this.referenceKind = (byte) (referenceKind.ordinal() + 1);
        this.referenceIndex = referenceIndex;
    }

    public MethodHandleConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.referenceKind = inp.readByte();
        this.referenceIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(referenceKind);
        stream.writeShort(referenceIndex);
    }

    public ReferenceKind getReferenceKind(ConstantPool pool) {
        return ReferenceKind.values()[referenceKind];
    }

    public GenericConstant getReference(ConstantPool pool) {
        return pool.constants[referenceIndex];
    }

    public enum ReferenceKind {
        REF_GET_FIELD,
        REF_GET_STATIC,
        REF_PUT_FIELD,
        REF_PUT_STATIC,
        REF_INVOKE_VIRTUAL,
        REF_NEW_INVOKE_SPECIAL,
        REF_INVOKE_STATIC,
        REF_INVOKE_SPECIAL,
        REF_INVOKE_INTERFACE
    }
}
