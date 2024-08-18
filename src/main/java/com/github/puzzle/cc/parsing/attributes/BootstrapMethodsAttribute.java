package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
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
        int bootstrapArgumentsCount;
        int[] bootstrapArguments;

        public BootstrapMethod(DataInputStream inp) throws IOException {
            bootstrapMethodRef = inp.readUnsignedShort();

            bootstrapArgumentsCount = inp.readUnsignedShort();
            bootstrapArguments = new int[bootstrapArgumentsCount];
            for (int i = 0; i < bootstrapArgumentsCount; i++) {
                bootstrapArguments[i] = inp.readUnsignedShort();
            }
        }

    }

}
