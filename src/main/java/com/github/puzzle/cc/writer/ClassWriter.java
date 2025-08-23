package com.github.puzzle.cc.writer;

import com.github.puzzle.cc.access.ClassAccessFlag;
import com.github.puzzle.cc.access.MethodAccessFlags;
import com.github.puzzle.cc.parsing.ClassReader;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.attributes.CodeAttribute;
import com.github.puzzle.cc.parsing.attributes.SourceFileAttribute;
import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.constants.*;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.parsing.fields.FieldInfo;
import com.github.puzzle.cc.parsing.methods.MethodInfo;
import com.github.puzzle.cc.util.ConstantPoolUtil;
import com.github.puzzle.cc.util.Pair;
import com.github.puzzle.cc.writer.attribute.CodeAttributeBuilder;
import com.github.puzzle.cc.writer.attribute.LocalVariableTableAttributeBuilder;
import com.github.puzzle.cc.writer.attribute.LocalVariableTypeTableAttributeBuilder;
import com.github.puzzle.cc.writer.bytecode.BytecodeWriter;
import com.github.puzzle.cc.writer.field.FieldWriter;
import com.github.puzzle.cc.writer.method.MethodWriter;
import com.github.puzzle.cc.writer.stdlib.CCWriterUtil;

import java.io.*;
import java.util.*;

public class ClassWriter {

    public static int MAGIC_NUMBER = 0xCAFEBABE;

    final Pair<Integer, Integer> classVersion;
    public ConstantPool constantPool = new ConstantPool();
    public Attributes attributes = new Attributes();

    int superClassIndex;
    int thisClassIndex;

    int accessFlags;

    private List<Integer> interfaces = new ArrayList<>();

    List<FieldWriter> fields = new ArrayList<>();
    List<MethodWriter> methods = new ArrayList<>();

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

        for (FieldInfo f : reader.fields) fields.add(new FieldWriter(this, f));
        for (MethodInfo m : reader.methods) methods.add(new MethodWriter(this, m));
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

    public void addInterface(Class<?> className) {
        if (!className.isInterface()) throw new RuntimeException("Class must be an interface");
        addInterface(className.getName());
    }

    public void addInterface(String className) {
        int interfaceUTF8Index = constantPool.push(new UTF8CONSTANT(className));
        int interfaceClassIndex = constantPool.push(new ClassConstant(interfaceUTF8Index));
        interfaces.add(interfaceClassIndex);
    }

    public FieldWriter addField(String name, Class<?> type) {
        FieldWriter fieldWriter = new FieldWriter(name, type);
        fields.add(fieldWriter);
        return fieldWriter;
    }

    public FieldWriter addField(String name, String descriptor) {
        FieldWriter fieldWriter = new FieldWriter(name, descriptor);
        fields.add(fieldWriter);
        return fieldWriter;
    }

    public MethodWriter addMethod(String name, Class<?> returnType, Class<?>... argTypes) {
        MethodWriter methodWriter = new MethodWriter(name, returnType, argTypes);
        methods.add(methodWriter);
        return methodWriter;
    }

    public byte[] toBytes() throws IOException {
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream();
        DataOutputStream stream = new DataOutputStream(byteStream);

        // Write Magic
        stream.writeInt(MAGIC_NUMBER);

        // Write Version minor, major
        stream.writeShort(classVersion.b);
        stream.writeShort(classVersion.a);

        for (FieldWriter writer : fields) writer.setup(this);
        for (MethodWriter writer : methods) writer.setup(this);

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

        stream.writeShort(fields.size());
        for (FieldWriter fieldWriter : fields)
            fieldWriter.writeToStream(stream);

        stream.writeShort(methods.size());
        for (MethodWriter f : methods)
            f.writeToStream(stream);

        // write attributes
        attributes.writeToStream(stream);

        return byteStream.toByteArray();
    }

    public static void println(
            ClassWriter writer,
            BytecodeWriter bytecodeWriter,
            String value
    ) {
        int outCtx = ConstantPoolUtil.getOrCreateFieldRef(
                writer.constantPool,
                "java/lang/System", "out", "Ljava/io/PrintStream;"
        );

        int printCts = ConstantPoolUtil.getOrCreateMethodRef(
                writer.constantPool,
                "java/io/PrintStream", "println", "(Ljava/lang/String;)V"
        );

        int str = ConstantPoolUtil.getOrCreateString(writer.constantPool, value);

        bytecodeWriter.putInstructionA(Opcodes.GETSTATIC, (short) (outCtx));
        bytecodeWriter.putInstructionV(Opcodes.LDC, (short) (str));
        bytecodeWriter.putInstructionARR(Opcodes.INVOKEVIRTUAL, (short) (printCts));
    }

    public static void main(String[] args) {
        ClassWriter writer = new ClassWriter(
                new Pair<>(45,0),
                ClassAccessFlag.ACC_PUBLIC.getMask(),
                Object.class,
                "Test"
        );

        MethodWriter methodWriter = writer.addMethod("main", void.class, String[].class);
        methodWriter.addAccessFlag(MethodAccessFlags.ACC_PUBLIC, MethodAccessFlags.ACC_STATIC);
        CodeAttributeBuilder builder = new CodeAttributeBuilder(writer.constantPool, 10, 10);
        LocalVariableTypeTableAttributeBuilder builder1 = new LocalVariableTypeTableAttributeBuilder(writer.constantPool);
        LocalVariableTableAttributeBuilder builder2 = new LocalVariableTableAttributeBuilder(writer.constantPool);

        BytecodeWriter bytecodeWriter = new BytecodeWriter(new ArrayList<>());
        CCWriterUtil.test(writer, bytecodeWriter);
        builder.write(bytecodeWriter);
        bytecodeWriter.putInstruction(Opcodes.RETURN);

        builder2.addVar(0, 10, "cliArgs", "[Ljava/lang/String;", 0);
        builder2.addVar(10, 22, "cliArgs", "[Ljava/lang/String;", 1);
        builder.getAttributes().push(builder2.end());
        CodeAttribute attribute = builder.end();


        methodWriter.addAttribute(attribute);
        System.out.println(writer.constantPool);

//        FieldWriter fieldWriter = writer.addField("text", String.class);
//        int valueNameIndex = writer.pushConstant(new UTF8CONSTANT("ConstantValue"));
//        int value = writer.pushConstant(new StringConstant(writer.pushConstant(new UTF8CONSTANT("Hola"))));

//        fieldWriter.getAttributes().push(new ConstantValueAttribute(valueNameIndex, value));
//        fieldWriter.addAccessFlag(FieldAccessFlag.ACC_PUBLIC, FieldAccessFlag.ACC_FINAL, FieldAccessFlag.ACC_STATIC);

        try {
            File f = new File("Test.class");
            FileOutputStream outputStream = new FileOutputStream(f);
            outputStream.write(writer.toBytes());

            ClassReader reader = new ClassReader(writer.toBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        File file = new File("./tests/WatParser.class");
//        try {
//            FileInputStream stream = new FileInputStream(file);
//            ClassReader reader = new ClassReader(stream.readAllBytes());
//            ClassWriter writer = new ClassWriter(reader);

//            File f = new File("Test2.class");
//            FileOutputStream outputStream = new FileOutputStream(f);
//            outputStream.write(reader.toBytes());

//            ClassReader reader2 = new ClassReader(reader.toBytes());
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }



    }

}
