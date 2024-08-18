package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class BootstrapMethodsAttribute extends AttributeInfo {

    BootstrapMethod[] bootstrapMethods;

    public BootstrapMethodsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        int bootstrapMethodsCount = inp.readUnsignedShort();
        bootstrapMethods = new BootstrapMethod[bootstrapMethodsCount];
        for (int i = 0; i < bootstrapMethodsCount; i++) {
            bootstrapMethods[i] = new BootstrapMethod(inp);
        }
    }

    public static class BootstrapMethod {

        int bootstrapMethodRef;
        int[] bootstrapArguments;

        public BootstrapMethod(DataInputStream inp) throws IOException {
            bootstrapMethodRef = inp.readUnsignedShort();

            bootstrapArguments = new int[inp.readUnsignedShort()];
            for (int i = 0; i < bootstrapArguments.length; i++) {
                bootstrapArguments[i] = inp.readUnsignedShort();
            }
        }

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(bootstrapMethodRef);
            outputStream.writeShort(bootstrapArguments.length);
            for (int i : bootstrapArguments) {
                outputStream.writeShort(i);
            }
        }

    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(bootstrapMethods.length);
        for (BootstrapMethod m : bootstrapMethods) {
            m.writeToStream(outputStream);
        }
    }
}
