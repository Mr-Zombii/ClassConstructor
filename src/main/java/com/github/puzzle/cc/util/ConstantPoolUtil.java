package com.github.puzzle.cc.util;

import com.github.puzzle.cc.parsing.constants.*;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

public class ConstantPoolUtil {

    public static int findUTF8(ConstantPool pool, String value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                constant instanceof UTF8CONSTANT
                && ((UTF8CONSTANT) constant).asString().equals(value)
            )
                return i;
        }
        return -1;
    }

    public static int findUTF8OrCreate(ConstantPool pool, String value) {
        int idx = findUTF8(pool, value);
        if (idx != -1) return idx;
        return pool.push(new UTF8CONSTANT(value));
    }

    public static int findDouble(ConstantPool pool, double value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof DoubleConstant
                            && ((DoubleConstant) constant).asDouble() == value
            )
                return i;
        }
        return -1;
    }

    public static int findDoubleOrCreate(ConstantPool pool, double value) {
        int idx = findDouble(pool, value);
        if (idx != -1) return idx;
        return pool.push(new DoubleConstant(value));
    }

    public static int findLong(ConstantPool pool, long value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof LongConstant
                            && ((LongConstant) constant).asLong() == value
            )
                return i;
        }
        return -1;
    }

    public static int findLongOrCreate(ConstantPool pool, long value) {
        int idx = findLong(pool, value);
        if (idx != -1) return idx;
        return pool.push(new LongConstant(value));
    }

    public static int findFloat(ConstantPool pool, float value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof FloatConstant
                            && ((FloatConstant) constant).asFloat() == value
            )
                return i;
        }
        return -1;
    }

    public static int findFloatOrCreate(ConstantPool pool, float value) {
        int idx = findFloat(pool, value);
        if (idx != -1) return idx;
        return pool.push(new FloatConstant(value));
    }

    public static int findInteger(ConstantPool pool, int value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof IntegerConstant
                            && ((IntegerConstant) constant).asInt() == value
            )
                return i;
        }
        return -1;
    }

    public static int findIntegerOrCreate(ConstantPool pool, int value) {
        int idx = findInteger(pool, value);
        if (idx != -1) return idx;
        return pool.push(new IntegerConstant(value));
    }

    public static int findString(ConstantPool pool, String value) {
        int utf8Idx = findUTF8(pool, value);
        if (utf8Idx == -1) return -1;
        return findString(pool, utf8Idx);
    }

    public static int findStringOrCreate(ConstantPool pool, String value) {
        int utf8Idx = findUTF8OrCreate(pool, value);
        int idx = findString(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new StringConstant(utf8Idx));
    }

    public static int findString(ConstantPool pool, int utf8Idx) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof StringConstant
                            && ((StringConstant) constant).getIndex() == utf8Idx
            )
                return i;
        }
        return -1;
    }

    public static int findStringOrCreate(ConstantPool pool, int utf8Idx) {
        int idx = findString(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new StringConstant(utf8Idx));
    }

    public static int findClass(ConstantPool pool, int utf8Idx) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof ClassConstant
                            && ((ClassConstant) constant).getNameIdx() == utf8Idx
            )
                return i;
        }
        return -1;
    }

    public static int findClassOrCreate(ConstantPool pool, int utf8Idx) {
        int idx = findClass(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new ClassConstant(utf8Idx));
    }

    public static int findClass(ConstantPool pool, String value) {
        int utf8Idx = findUTF8(pool, value);
        if (utf8Idx == -1) return -1;
        return findClass(pool, utf8Idx);
    }

    public static int findClassOrCreate(ConstantPool pool, String value) {
        int utf8Idx = findUTF8OrCreate(pool, value);
        int idx = findClass(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new ClassConstant(utf8Idx));
    }

    public static int findMethodType(ConstantPool pool, int utf8Idx) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof MethodTypeConstant
                            && ((MethodTypeConstant) constant).getDescriptorIndex() == utf8Idx
            )
                return i;
        }
        return -1;
    }

    public static int findMethodTypeOrCreate(ConstantPool pool, int utf8Idx) {
        int idx = findMethodType(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new ClassConstant(utf8Idx));
    }

    public static int findMethodType(ConstantPool pool, String value) {
        int utf8Idx = findUTF8(pool, value);
        if (utf8Idx == -1) return -1;
        return findMethodType(pool, utf8Idx);
    }

    public static int findMethodTypeOrCreate(ConstantPool pool, String value) {
        int utf8Idx = findUTF8OrCreate(pool, value);
        int idx = findMethodType(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new MethodTypeConstant(utf8Idx));
    }

    public static int findModule(ConstantPool pool, int utf8Idx) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof ModuleConstant
                            && ((ModuleConstant) constant).getNameIndex() == utf8Idx
            )
                return i;
        }
        return -1;
    }

    public static int findModuleOrCreate(ConstantPool pool, int utf8Idx) {
        int idx = findModule(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new ClassConstant(utf8Idx));
    }

    public static int findModule(ConstantPool pool, String value) {
        int utf8Idx = findUTF8(pool, value);
        if (utf8Idx == -1) return -1;
        return findModule(pool, utf8Idx);
    }

    public static int findModuleOrCreate(ConstantPool pool, String value) {
        int utf8Idx = findUTF8OrCreate(pool, value);
        int idx = findModule(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new ModuleConstant(utf8Idx));
    }

    public static int findPackage(ConstantPool pool, int utf8Idx) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof PackageConstant
                            && ((PackageConstant) constant).getIndex() == utf8Idx
            )
                return i;
        }
        return -1;
    }

    public static int findPackageOrCreate(ConstantPool pool, int utf8Idx) {
        int idx = findPackage(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new ClassConstant(utf8Idx));
    }

    public static int findPackage(ConstantPool pool, String value) {
        int utf8Idx = findUTF8(pool, value);
        if (utf8Idx == -1) return -1;
        return findPackage(pool, utf8Idx);
    }

    public static int findPackageOrCreate(ConstantPool pool, String value) {
        int utf8Idx = findUTF8OrCreate(pool, value);
        int idx = findPackage(pool, utf8Idx);
        if (idx != -1) return idx;
        return pool.push(new PackageConstant(utf8Idx));
    }

    public static int findNameAndTypeConstant(
            ConstantPool pool,
            int nameUTF8Idx, int descriptorUTF8Idx
    ) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof NameAndTypeConstant
                            && ((NameAndTypeConstant) constant).getNameIndex() == nameUTF8Idx
                            && ((NameAndTypeConstant) constant).getDescriptorIndex() == descriptorUTF8Idx
            )
                return i;
        }
        return -1;
    }

    public static int findNameAndTypeConstant(
            ConstantPool pool,
            String name, int descriptorUTF8Idx
    ) {
        int nameUTF8 = findUTF8(pool, name);
        if (nameUTF8 == -1) return -1;

        return findNameAndTypeConstant(pool, nameUTF8, descriptorUTF8Idx);
    }

    public static int findNameAndTypeConstant(
            ConstantPool pool,
            int nameUTF8, String descriptor
    ) {
        int descUTF8 = findUTF8(pool, descriptor);
        if (descUTF8 == -1) return -1;

        return findNameAndTypeConstant(pool, nameUTF8, descUTF8);
    }

    public static int findNameAndTypeConstant(
            ConstantPool pool,
            String name, String descriptor
    ) {
        int nameUTF8 = findUTF8(pool, name);
        if (nameUTF8 == -1) return -1;
        int descUTF8 = findUTF8(pool, descriptor);
        if (descUTF8 == -1) return -1;

        return findNameAndTypeConstant(pool, nameUTF8, descUTF8);
    }

    public static int findNameAndTypeConstantOrCreate(
            ConstantPool pool,
            int nameUTF8Idx, int descriptorUTF8Idx
    ) {
        int idx = findNameAndTypeConstant(pool, nameUTF8Idx, descriptorUTF8Idx);
        if (idx != -1) return idx;
        return pool.push(new NameAndTypeConstant(nameUTF8Idx, descriptorUTF8Idx));
    }

    public static int findNameAndTypeConstantOrCreate(
            ConstantPool pool,
            String name, int descUTF8
    ) {
        int nameUTF8 = findUTF8OrCreate(pool, name);

        return findNameAndTypeConstantOrCreate(pool, nameUTF8, descUTF8);
    }

    public static int findNameAndTypeConstantOrCreate(
            ConstantPool pool,
            int nameUTF8, String descriptor
    ) {
        int descUTF8 = findUTF8OrCreate(pool, descriptor);

        return findNameAndTypeConstantOrCreate(pool, nameUTF8, descUTF8);
    }

    public static int findNameAndTypeConstantOrCreate(
            ConstantPool pool,
            String name, String descriptor
    ) {
        int nameUTF8 = findUTF8OrCreate(pool, name);
        int descUTF8 = findUTF8OrCreate(pool, descriptor);

        return findNameAndTypeConstantOrCreate(pool, nameUTF8, descUTF8);
    }

    public static int findFieldRefConstant(
            ConstantPool pool,
            int classIdx, int nameAndTypeIdx
    ) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof FieldRefConstant
                            && ((FieldRefConstant) constant).getClassIndex() == classIdx
                            && ((FieldRefConstant) constant).getNameAndTypeIndex() == nameAndTypeIdx
            )
                return i;
        }
        return -1;
    }

    public static int findFieldRefConstantOrCreate(
            ConstantPool pool,
            int classIdx, int nameAndTypeIdx
    ) {
        int idx = findFieldRefConstant(pool, classIdx, nameAndTypeIdx);
        if (idx == -1) return pool.push(new FieldRefConstant(classIdx, nameAndTypeIdx));
        return idx;
    }

    public static int findFieldRefConstant(
            ConstantPool pool,
            String clazz, String name, String descriptor
    ) {
        int classIdx = findClass(pool, clazz);
        if (classIdx == -1) return -1;
        int nameAndTypeIdx = findNameAndTypeConstant(pool, name, descriptor);
        if (nameAndTypeIdx == -1) return -1;
        return findFieldRefConstant(pool, classIdx, nameAndTypeIdx);
    }

    public static int findFieldRefConstantOrCreate(
            ConstantPool pool,
            String clazz, String name, String descriptor
    ) {
        int classIdx = findClassOrCreate(pool, clazz);
        int nameAndTypeIdx = findNameAndTypeConstantOrCreate(pool, name, descriptor);
        if (nameAndTypeIdx == -1) return -1;
        return findFieldRefConstantOrCreate(pool, classIdx, nameAndTypeIdx);
    }

    public static int findMethodRefConstant(
            ConstantPool pool,
            int classIdx, int nameAndTypeIdx
    ) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (
                    constant instanceof MethodRefConstant
                            && ((MethodRefConstant) constant).getClassIndex() == classIdx
                            && ((MethodRefConstant) constant).getNameAndTypeIndex() == nameAndTypeIdx
            )
                return i;
        }
        return -1;
    }

    public static int findMethodRefConstantOrCreate(
            ConstantPool pool,
            int classIdx, int nameAndTypeIdx
    ) {
        int idx = findMethodRefConstant(pool, classIdx, nameAndTypeIdx);
        if (idx == -1) return pool.push(new MethodRefConstant(classIdx, nameAndTypeIdx));
        return idx;
    }

    public static int findMethodRefConstant(
            ConstantPool pool,
            String clazz, String name, String descriptor
    ) {
        int classIdx = findClass(pool, clazz);
        if (classIdx == -1) return -1;
        int nameAndTypeIdx = findNameAndTypeConstant(pool, name, descriptor);
        if (nameAndTypeIdx == -1) return -1;
        return findMethodRefConstant(pool, classIdx, nameAndTypeIdx);
    }

    public static int findMethodRefConstantOrCreate(
            ConstantPool pool,
            String clazz, String name, String descriptor
    ) {
        int classIdx = findClassOrCreate(pool, clazz);
        int nameAndTypeIdx = findNameAndTypeConstantOrCreate(pool, name, descriptor);
        if (nameAndTypeIdx == -1) return -1;
        return findMethodRefConstantOrCreate(pool, classIdx, nameAndTypeIdx);
    }

}
