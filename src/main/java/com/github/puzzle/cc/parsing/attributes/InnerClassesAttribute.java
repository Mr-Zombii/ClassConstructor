package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.ClassAccessFlag;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class InnerClassesAttribute extends AttributeInfo {

    int numberOfClasses;
    InnerClass[] classes;

    public InnerClassesAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        numberOfClasses = inp.readUnsignedShort();
        classes = new InnerClass[numberOfClasses];
        for (int i = 0; i < numberOfClasses; i++) {
            classes[i] = new InnerClass(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(classes.length);
        for (InnerClass i : classes) i.writeToStream(outputStream);
    }

    public static class InnerClass {

        int innerClassInfoIndex;
        int outerClassInfoIndex;
        int innerNameIndex;
        AccessFlag[] accessFlags;

        public InnerClass(DataInputStream inp) throws IOException {
            innerClassInfoIndex = inp.readUnsignedShort();
            outerClassInfoIndex = inp.readUnsignedShort();
            innerNameIndex = inp.readUnsignedShort();
            accessFlags = ClassAccessFlag.getFromFlags(inp.readUnsignedShort());
        }

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(innerClassInfoIndex);
            outputStream.writeShort(outerClassInfoIndex);
            outputStream.writeShort(innerNameIndex);

            int accFlags = 0;
            for (AccessFlag flag : accessFlags) accFlags |= flag.getMask();
            outputStream.writeShort(accFlags);
        }

    }
}
