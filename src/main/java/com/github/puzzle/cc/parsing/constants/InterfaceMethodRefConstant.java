package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InterfaceMethodRefConstant extends GenericConstant {

    int classIndex;
    int nameAndTypeIndex;

    public InterfaceMethodRefConstant(int classIndex, int nameAndTypeIndex) {
        super(ConstantPool.TagType.CONSTANT_INTERFACE_METHOD_REF, null);
        this.classIndex = classIndex;
        this.nameAndTypeIndex = nameAndTypeIndex;
    }

    public InterfaceMethodRefConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
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
        return (ClassConstant) pool.constants[classIndex];
    }

    public NameAndTypeConstant getNameAndType(ConstantPool pool) {
        return (NameAndTypeConstant) pool.constants[nameAndTypeIndex];
    }

}
