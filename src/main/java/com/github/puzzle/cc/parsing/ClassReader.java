package com.github.puzzle.cc.parsing;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.ClassAccessFlag;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.constants.ClassConstant;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.parsing.fields.FieldInfo;
import com.github.puzzle.cc.parsing.methods.MethodInfo;
import com.github.puzzle.cc.util.Pair;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ClassReader {

    // the regular java magic number
    public static int MAGIC_NUMBER = 0xCAFEBABE;
    DataInputStream inp;

    public Pair<Integer, Integer> classVersion;
    public ConstantPool constantPool;

    public AccessFlag[] accessFlags;

    public ClassConstant this_class;
    public ClassConstant super_class;

    public ClassConstant[] interfaces;
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

        classVersion = new Pair<>(inp.readUnsignedShort(), inp.readUnsignedShort());
        constantPool = new ConstantPool(inp);

        accessFlags = ClassAccessFlag.getFromFlags(inp.readUnsignedShort());
        try {
            this_class = (ClassConstant) constantPool.constants[inp.readUnsignedShort() - 1];
            super_class = (ClassConstant) constantPool.constants[inp.readUnsignedShort() - 1];
        } catch (Exception e) {
            throw new RuntimeException("Constant Pool Is Broken, ", e);
        }

        interfaces = new ClassConstant[inp.readUnsignedShort()];
        for (int i = 0; i < interfaces.length; i++) {
            interfaces[i] = (ClassConstant) constantPool.constants[inp.readUnsignedShort() - 1];
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
        System.out.println(attributes.get("SourceFile").getName(constantPool));
    }

}
