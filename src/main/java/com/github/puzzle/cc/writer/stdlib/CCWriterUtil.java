package com.github.puzzle.cc.writer.stdlib;

import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.util.ConstantPoolUtil;
import com.github.puzzle.cc.writer.ClassWriter;
import com.github.puzzle.cc.writer.bytecode.BytecodeWriter;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class CCWriterUtil {

    static ClassWriter writer;
    static BytecodeWriter bytecodeWriter;

    public static void use(ClassWriter w, BytecodeWriter b) {
        writer = w;
        bytecodeWriter = b;
    }

    public static void callVirtual(String clazz, String methodName, String methodDescriptor) {
        int idx = ConstantPoolUtil.getOrCreateMethodRef(
                writer.constantPool,
                clazz, methodName, methodDescriptor
        );
        bytecodeWriter.putInstructionA(Opcodes.INVOKEVIRTUAL, (short) idx);
    }

    public static void callVirtual(Class<?> clazz, String methodName, Class<?>... methodParameters) {
        Method method;
        try {
            method = clazz.getMethod(methodName, methodParameters);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("method \"" + methodName + "\" on class " + clazz.getTypeName() + " must exist!");
        }
        if (!Modifier.isPublic(method.getModifiers()))
            throw new IllegalArgumentException("method \"" + methodName + "\" on class " + clazz.getTypeName() + " must be public!");
        if (Modifier.isStatic(method.getModifiers()))
            throw new IllegalArgumentException("method \"" + methodName + "\" on class " + clazz.getTypeName() + " must not be static!");

        StringBuilder builder = new StringBuilder("(");
        for (Class<?> methodParameter : methodParameters) {
            builder.append(methodParameter.descriptorString());
        }
        builder.append(")").append(method.getReturnType().descriptorString());

        callVirtual(
                clazz.getName().replaceAll("\\.", "/"),
                methodName,
                builder.toString()
        );
    }

    public static void callStatic(String clazz, String methodName, String methodDescriptor) {
        int idx = ConstantPoolUtil.getOrCreateMethodRef(
                writer.constantPool,
                clazz, methodName, methodDescriptor
        );
        bytecodeWriter.putInstructionA(Opcodes.INVOKESTATIC, (short) idx);
    }

    public static void callStatic(Class<?> clazz, String methodName, Class<?>... methodParameters) {
        Method method;
        try {
            method = clazz.getMethod(methodName, methodParameters);
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("method \"" + methodName + "\" on class " + clazz.getTypeName() + " must exist!");
        }
        if (!Modifier.isPublic(method.getModifiers()))
            throw new IllegalArgumentException("method \"" + methodName + "\" on class " + clazz.getTypeName() + " must be public!");
        if (!Modifier.isStatic(method.getModifiers()))
            throw new IllegalArgumentException("method \"" + methodName + "\" on class " + clazz.getTypeName() + " must be static!");

        StringBuilder builder = new StringBuilder("(");
        for (Class<?> methodParameter : methodParameters) {
            builder.append(methodParameter.descriptorString());
        }
        builder.append(")").append(method.getReturnType().descriptorString());

        callStatic(
                clazz.getName().replaceAll("\\.", "/"),
                methodName,
                builder.toString()
        );
    }

    public static void getStatic(String clazz, String fieldName, String fieldDescriptor) {
        int idx = ConstantPoolUtil.getOrCreateFieldRef(
                writer.constantPool,
                clazz, fieldName, fieldDescriptor
        );
        bytecodeWriter.putInstructionA(Opcodes.GETSTATIC, (short) idx);
    }

    public static void getStatic(Class<?> clazz, String fieldName) {
        Field field;
        try {
            field = clazz.getField(fieldName);
        } catch (NoSuchFieldException e) {
            throw new IllegalArgumentException("field \"" + fieldName + "\" on class " + clazz.getTypeName() + " must exist!");
        }
        if (!Modifier.isPublic(field.getModifiers())) throw new IllegalArgumentException("field \"" + fieldName + "\" on class " + clazz.getTypeName() + " must be public!");
        if (!Modifier.isStatic(field.getModifiers()))
            throw new IllegalArgumentException("field \"" + fieldName + "\" on class " + clazz.getTypeName() + " must be static!");

        getStatic(
                clazz.getName().replaceAll("\\.", "/"),
                fieldName,
                field.getType().descriptorString()
        );
    }

    public static void test(ClassWriter writer, BytecodeWriter bytecodeWriter) {
        NativeTypes.use(writer, bytecodeWriter);
        SystemLib.use(writer, bytecodeWriter);
        NativeArrays.use(writer, bytecodeWriter);

        bytecodeWriter.putInstruction(Opcodes.ALOAD_0);
        SystemLib.println();
        NativeTypes.value("Hello World2");
        bytecodeWriter.putInstruction(Opcodes.ASTORE_0);
        bytecodeWriter.putInstruction(Opcodes.ALOAD_0);
        SystemLib.println();
//        NativeTypes.value("HHHH");
//        NativeTypes.value("HHHH");
        NativeTypes.value(1236);
        bytecodeWriter.putInstruction(Opcodes.ASTORE_1);
        bytecodeWriter.putInstruction(Opcodes.ALOAD_1);
        SystemLib.println();

//        NativeTypes.value(2);
//        NativeTypes.value(2);
//        NativeArrays.newMultiDimensionArray(String.class, 2);
//        bytecodeWriter.putInstructionV(Opcodes.ASTORE, (short) 1);
//        bytecodeWriter.putInstructionV(Opcodes.ALOAD, (short) 1);

    }

}
