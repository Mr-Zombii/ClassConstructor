package com.github.puzzle.cc.parsing;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.ClassAccessFlag;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.constants.ClassConstant;
import com.github.puzzle.cc.parsing.constants.GenericConstant;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.parsing.fields.FieldInfo;
import com.github.puzzle.cc.parsing.methods.MethodInfo;
import com.github.puzzle.cc.util.Pair;
import com.github.puzzle.cc.writer.ClassWriter;

import java.io.*;

public class ClassReader {

    // the regular java magic number
    public static int MAGIC_NUMBER = 0xCAFEBABE;
    DataInputStream inp;

    public Pair<Integer, Integer> classVersion;
    public ConstantPool constantPool;

    public AccessFlag[] accessFlags;
    public int rawAccessFlags;

    public ClassConstant this_class;
    public int this_class_index;
    public ClassConstant super_class;
    public int super_class_index;

    public ClassConstant[] interfaces;
    public int[] interfaces_indexes;
    public FieldInfo[] fields;
    public MethodInfo[] methods;
    public Attributes attributes;

    public ClassReader(byte[] bytes) throws IOException {
        this(new ByteArrayInputStream(bytes));
    }

    public ClassReader(InputStream stream) throws IOException {
        this.inp = new DataInputStream(stream);

        int magic = inp.readInt();
        if (magic != MAGIC_NUMBER) throw new IOException("Invalid Java Class: Broken Class Magic");

        int minor_ver = inp.readUnsignedShort();
        int major_ver = inp.readUnsignedShort();
        classVersion = new Pair<>(major_ver, minor_ver) {
            @Override
            public String toString() {
                return a + "." + b;
            }
        };
        constantPool = new ConstantPool(inp);

        rawAccessFlags = inp.readUnsignedShort();
        accessFlags = ClassAccessFlag.getFromFlags(rawAccessFlags);
        try {
            this_class_index = inp.readUnsignedShort();
            super_class_index = inp.readUnsignedShort();

            this_class = (ClassConstant) constantPool.constants[this_class_index - 1];
            super_class = (ClassConstant) constantPool.constants[super_class_index - 1];
        } catch (Exception e) {
            throw new RuntimeException("Constant Pool Is Broken, ", e);
        }

        interfaces = new ClassConstant[inp.readUnsignedShort()];
        interfaces_indexes = new int[interfaces.length];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces_indexes[i] = inp.readUnsignedShort();
            interfaces[i] = (ClassConstant) constantPool.constants[interfaces_indexes[i] - 1];
        }

        int field_count = inp.readUnsignedShort();
        fields = new FieldInfo[field_count];
        for (int i = 0; i < field_count; i++) {
            fields[i] = new FieldInfo(constantPool, inp);
        }

        int method_count = inp.readUnsignedShort();
        methods = new MethodInfo[method_count];
        for (int i = 0; i < method_count; i++) {
            methods[i] = new MethodInfo(constantPool, inp);
        }

        int attributes_count = inp.readUnsignedShort();
        attributes = AttributeInfo.readAttributes(constantPool, attributes_count, inp);
    }

    public byte[] toBytes() throws IOException {
        ClassWriter writer = new ClassWriter(this);
        return writer.toBytes();
    }

}
