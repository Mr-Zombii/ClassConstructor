package com.github.puzzle.cc.writer;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.access.ClassAccessFlag;
import com.github.puzzle.cc.parsing.ClassReader;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.attributes.SourceFileAttribute;
import com.github.puzzle.cc.parsing.constants.ClassConstant;
import com.github.puzzle.cc.parsing.constants.GenericConstant;
import com.github.puzzle.cc.parsing.constants.UTF8CONSTANT;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.parsing.fields.FieldInfo;
import com.github.puzzle.cc.parsing.methods.MethodInfo;
import com.github.puzzle.cc.util.Pair;

import java.io.*;
import java.lang.reflect.Field;
import java.util.*;

public class ClassWriter {

    public static int MAGIC_NUMBER = 0xCAFEBABE;

    final Pair<Integer, Integer> classVersion;
    public ConstantPool constantPool = new ConstantPool();
    public Attributes attributes = new Attributes();

    int superClassIndex;
    int thisClassIndex;

    int accessFlags;

    List<Integer> interfaces = new ArrayList<>();
    List<MethodInfo> methodInfos = new ArrayList<>();
    List<FieldInfo> fieldInfos = new ArrayList<>();

    public ClassWriter(ClassReader reader) {
        classVersion = new Pair<>(reader.classVersion.a, reader.classVersion.b);

        constantPool = new ConstantPool(reader.constantPool);
        attributes = new Attributes(reader.attributes);

        accessFlags = reader.rawAccessFlags;

        for (int i : Arrays.copyOf(reader.interfaces_indexes, reader.interfaces_indexes.length)) {
            interfaces.add(i);
        }

        superClassIndex = reader.super_class_index;
        thisClassIndex = reader.this_class_index;

        fieldInfos.addAll(Arrays.asList(Arrays.copyOf(reader.fields, reader.fields.length)));

        methodInfos.addAll(Arrays.asList(Arrays.copyOf(reader.methods, reader.methods.length)));
    }

    public ClassWriter(
            Pair<Integer, Integer> cv,
            int accessFlags,
            String superClass,
            String className
    ) {
        this.classVersion = cv;

        String[] innerClassSplit = className.split("\\$");
        String[] classSplit = innerClassSplit[innerClassSplit.length == 1 ? 0 : innerClassSplit.length - 2].split("/");

        int nameIndex = constantPool.push(new UTF8CONSTANT("SourceFile"));
        int sourceIndex = constantPool.push(new UTF8CONSTANT(classSplit[classSplit.length - 1] + ".java"));

        attributes.push(new SourceFileAttribute(nameIndex, sourceIndex));

        int superClassUTF8Index = constantPool.push(new UTF8CONSTANT(superClass));
        superClassIndex = constantPool.push(new ClassConstant(superClassUTF8Index));

        int classNameUTF8Index = constantPool.push(new UTF8CONSTANT(className));
        thisClassIndex = constantPool.push(new ClassConstant(classNameUTF8Index));

        this.accessFlags = accessFlags;
    }

    public ClassWriter(
            Pair<Integer, Integer> cv,
            int accessFlags,
            Class<?> superClass,
            String className
    ) {
        this(
                cv,
                accessFlags,
                superClass.getName().replaceAll("\\.", "/"),
                className
        );
    }

    public int pushConstant(GenericConstant constant) {
        return constantPool.push(constant);
    }

    public int addAttribute(AttributeInfo attributeInfo) {
        return attributes.push(attributeInfo);
    }

    public void addInterface(String className) {
        int interfaceUTF8Index = constantPool.push(new UTF8CONSTANT(className));
        int interfaceClassIndex = constantPool.push(new ClassConstant(interfaceUTF8Index));
        interfaces.add(interfaceClassIndex);
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream stream = new DataOutputStream(byteStream);

        // Write Magic
        stream.writeInt(MAGIC_NUMBER);

        // Write Version minor, major
        stream.writeShort(classVersion.b);
        stream.writeShort(classVersion.a);

        // Write ConstantPool
        constantPool.writeToStream(stream);

        // Write Access Flags
        stream.writeShort(accessFlags);

        // Write This & Super Classes
        stream.writeShort(thisClassIndex);
        stream.writeShort(superClassIndex);

        // Write Interfaces
        stream.writeShort(interfaces.size());
        for (int interfce : interfaces)
            stream.writeShort(interfce);

        stream.writeShort(fieldInfos.size());
        for (FieldInfo f : fieldInfos)
            f.writeToStream(stream);

        stream.writeShort(methodInfos.size());
        for (MethodInfo f : methodInfos)
            f.writeToStream(stream);

        // write attributes
        attributes.writeToStream(stream);

        return byteStream.toByteArray();
    }

    public static void main(String[] args) {
//        ClassWriter writer = new ClassWriter(
//                new Pair<>(6, 9),
//                ClassAccessFlag.ACC_PUBLIC.getMask(),
//                Object.class,
//                "com/github/puzzle/cc/Test"
//        );
//
//        try {
//            File f = new File("Test.class");
//            FileOutputStream outputStream = new FileOutputStream(f);
//            outputStream.write(writer.toBytes());
//
//            ClassReader reader = new ClassReader(writer.toBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        File file = new File("./tests/WatParser.class");
        try {
            FileInputStream stream = new FileInputStream(file);
            ClassReader reader = new ClassReader(stream.readAllBytes());
//            ClassWriter writer = new ClassWriter(reader);

            File f = new File("Test2.class");
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(reader.toBytes());

            ClassReader reader2 = new ClassReader(reader.toBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }



    }

}
