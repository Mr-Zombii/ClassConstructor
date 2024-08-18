package com.github.puzzle.cc.parsing.constants;

import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class DynamicConstant extends GenericConstant {

    int bootstrapMethodAttrIndex;
    int nameAndTypeIndex;

    public DynamicConstant(int nameAndTypeIndex, int bootstrapMethodAttrIndex) {
        super(ConstantPool.TagType.CONSTANT_DYNAMIC, null);
        this.nameAndTypeIndex = nameAndTypeIndex;
        this.bootstrapMethodAttrIndex = bootstrapMethodAttrIndex;
    }

    public DynamicConstant(ConstantPool.TagType type, DataInputStream inp) throws IOException {
        super(type, inp);
        this.bootstrapMethodAttrIndex = inp.readUnsignedShort();
        this.nameAndTypeIndex = inp.readUnsignedShort();
    }

    @Override
    public void writeToStream(DataOutputStream stream) throws IOException {
        super.writeToStream(stream);
        stream.writeShort(bootstrapMethodAttrIndex);
        stream.writeShort(nameAndTypeIndex);
    }

    public int getBootstrapMethodAttrIndex() {
        return bootstrapMethodAttrIndex;
    }

    public NameAndTypeConstant getNameAndType(ConstantPool pool) {
        return (NameAndTypeConstant) pool.constants[nameAndTypeIndex];
    }
}
