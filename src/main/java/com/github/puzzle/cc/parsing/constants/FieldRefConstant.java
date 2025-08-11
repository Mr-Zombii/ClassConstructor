package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class FieldRefConstant extends GenericConstant {

    int classIndex;
    int nameAndTypeIndex;

    public FieldRefConstant(int classIndex, int nameAndTypeIndex) {
        super(ConstantPool.TagType.CONSTANT_FIELD_REF, null);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public FieldRefConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.classIndex = inp.readUnsignedShort();
        this.nameAndTypeIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(classIndex);
        stream.writeShort(nameAndTypeIndex);
    }

    public ClassConstant getClass(ConstantPool pool) {
        return ((ClassConstant) pool.get(classIndex));
    }

    public NameAndTypeConstant getNameAndType(ConstantPool pool) {
        return ((NameAndTypeConstant) pool.get(nameAndTypeIndex));
    }

    public int getClassIndex() {
        return classIndex;
    }

    public int getNameAndTypeIndex() {
        return nameAndTypeIndex;
    }
}
