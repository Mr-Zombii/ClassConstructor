package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RecordAttribute extends AttributeInfo {

    RecordComponentInfo[] components;

    public RecordAttribute(ConstantPool pool, int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        components = new RecordComponentInfo[inp.readUnsignedShort()];
        for (int i = 0; i < components.length; i++) {
            components[i] = new RecordComponentInfo(pool, inp);
        }

    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(components.length);
        for (RecordComponentInfo recrd : components) recrd.writeToStream(outputStream);
    }

    public static class RecordComponentInfo {

        int nameIndex;
        int descriptorIndex;
        Attributes attributes;

        public RecordComponentInfo(ConstantPool pool, DataInputStream inp) throws IOException {
            nameIndex = inp.readUnsignedShort();
            descriptorIndex = inp.readUnsignedShort();

            attributes = AttributeInfo.readAttributes(pool, inp.readUnsignedShort(), inp);
        }

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(nameIndex);
            outputStream.writeShort(descriptorIndex);
            attributes.writeToStream(outputStream);
        }

    }
}
