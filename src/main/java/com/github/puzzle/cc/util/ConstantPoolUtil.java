package com.github.puzzle.cc.util;

import com.github.puzzle.cc.parsing.constants.*;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.util.Objects;

public class ConstantPoolUtil {

    public static int getUTF8(ConstantPool pool, String value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof UTF8CONSTANT) {
                UTF8CONSTANT vConstant = constant.as();
                if (vConstant.asString().equals(value))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateUTF8(ConstantPool pool, String value) {
        int idx = getUTF8(pool, value);
        if (idx != -1) return idx;
        return pool.push(new UTF8CONSTANT(value));
    }

    public static int getLong(ConstantPool pool, long value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof LongConstant) {
                LongConstant vConstant = constant.as();
                if (Objects.equals(vConstant.asLong(), value))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateLong(ConstantPool pool, long value) {
        int idx = getLong(pool, value);
        if (idx != -1) return idx;
        return pool.push(new LongConstant(value));
    }

    public static int getDouble(ConstantPool pool, double value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof DoubleConstant) {
                DoubleConstant vConstant = constant.as();
                if (Objects.equals(vConstant.asDouble(), value))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateDouble(ConstantPool pool, double value) {
        int idx = getDouble(pool, value);
        if (idx != -1) return idx;
        return pool.push(new DoubleConstant(value));
    }

    public static int getInteger(ConstantPool pool, int value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof IntegerConstant) {
                IntegerConstant vConstant = constant.as();
                if (Objects.equals(vConstant.asInteger(), value))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateInteger(ConstantPool pool, int value) {
        int idx = getInteger(pool, value);
        if (idx != -1) return idx;
        return pool.push(new IntegerConstant(value));
    }

    public static int getFloat(ConstantPool pool, float value) {
        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof FloatConstant) {
                FloatConstant vConstant = constant.as();
                if (Objects.equals(vConstant.asFloat(), value))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateFloat(ConstantPool pool, float value) {
        int idx = getFloat(pool, value);
        if (idx != -1) return idx;
        return pool.push(new FloatConstant(value));
    }

    public static int getString(ConstantPool pool, String value) {
        int idx0 = getUTF8(pool, value);
        if (idx0 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof StringConstant) {
                StringConstant vConstant = constant.as();
                if (Objects.equals(vConstant.getStringIndex(), idx0))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateString(ConstantPool pool, String value) {
        int idx = getString(pool, value);
        if (idx != -1) return idx;
        return pool.push(new StringConstant(getOrCreateUTF8(pool, value)));
    }

    public static int getClass(ConstantPool pool, String value) {
        int idx0 = getUTF8(pool, value);
        if (idx0 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof ClassConstant) {
                ClassConstant vConstant = constant.as();
                if (Objects.equals(vConstant.getNameIdx(), idx0))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateClass(ConstantPool pool, String value) {
        int idx = getClass(pool, value);
        if (idx != -1) return idx;
        return pool.push(new ClassConstant(getOrCreateUTF8(pool, value)));
    }

    public static int getMethodType(ConstantPool pool, String value) {
        int idx0 = getUTF8(pool, value);
        if (idx0 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof MethodTypeConstant) {
                MethodTypeConstant vConstant = constant.as();
                if (Objects.equals(vConstant.getDescriptorIndex(), idx0))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateMethodType(ConstantPool pool, String value) {
        int idx = getMethodType(pool, value);
        if (idx != -1) return idx;
        return pool.push(new MethodTypeConstant(getOrCreateUTF8(pool, value)));
    }

    public static int getPackage(ConstantPool pool, String value) {
        int idx0 = getUTF8(pool, value);
        if (idx0 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof PackageConstant) {
                PackageConstant vConstant = constant.as();
                if (Objects.equals(vConstant.getNameIndex(), idx0))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreatePackage(ConstantPool pool, String value) {
        int idx = getPackage(pool, value);
        if (idx != -1) return idx;
        return pool.push(new PackageConstant(getOrCreateUTF8(pool, value)));
    }

    public static int getModule(ConstantPool pool, String value) {
        int idx0 = getUTF8(pool, value);
        if (idx0 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof ModuleConstant) {
                ModuleConstant vConstant = constant.as();
                if (Objects.equals(vConstant.getNameIndex(), idx0))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateModule(ConstantPool pool, String value) {
        int idx = getModule(pool, value);
        if (idx != -1) return idx;
        return pool.push(new ModuleConstant(getOrCreateUTF8(pool, value)));
    }

    public static int getNameAndType(ConstantPool pool, String name, String type) {
        int idx0 = getUTF8(pool, name);
        if (idx0 == -1) return -1;
        int idx1 = getUTF8(pool, type);
        if (idx1 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof NameAndTypeConstant) {
                NameAndTypeConstant vConstant = constant.as();
                if (!Objects.equals(vConstant.getNameIndex(), idx0)) continue;
                if (Objects.equals(vConstant.getDescriptorIndex(), idx1))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateNameAndType(ConstantPool pool, String name, String type) {
        int idx = getNameAndType(pool, name, type);
        if (idx != -1) return idx;
        return pool.push(new NameAndTypeConstant(
                getOrCreateUTF8(pool, name),
                getOrCreateUTF8(pool, type)
        ));
    }

    public static int getFieldRef(ConstantPool pool, String clazz, String name, String type) {
        int idx0 = getClass(pool, clazz);
        if (idx0 == -1) return -1;
        int idx1 = getNameAndType(pool, name, type);
        if (idx1 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof FieldRefConstant) {
                FieldRefConstant vConstant = constant.as();
                if (!Objects.equals(vConstant.getClassIndex(), idx0)) continue;
                if (Objects.equals(vConstant.getNameAndTypeIndex(), idx1))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateFieldRef(ConstantPool pool, String clazz, String name, String type) {
        int idx = getFieldRef(pool, clazz, name, type);
        if (idx != -1) return idx;

        return pool.push(new FieldRefConstant(
                getOrCreateClass(pool, clazz),
                getOrCreateNameAndType(pool, name, type)
        ));
    }

    public static int getMethodRef(ConstantPool pool, String clazz, String name, String type) {
        int idx0 = getClass(pool, clazz);
        if (idx0 == -1) return -1;
        int idx1 = getNameAndType(pool, name, type);
        if (idx1 == -1) return -1;

        for (int i = 0; i < pool.constants.length; i++) {
            GenericConstant constant = pool.constants[i];
            if (constant instanceof MethodRefConstant) {
                MethodRefConstant vConstant = constant.as();
                if (!Objects.equals(vConstant.getClassIndex(), idx0)) continue;
                if (Objects.equals(vConstant.getNameAndTypeIndex(), idx1))
                    return i + 1;
            }
        }
        return -1;
    }

    public static int getOrCreateMethodRef(ConstantPool pool, String clazz, String name, String type) {
        int idx = getMethodRef(pool, clazz, name, type);
        if (idx != -1) return idx;
        return pool.push(new MethodRefConstant(
                getOrCreateClass(pool, clazz),
                getOrCreateNameAndType(pool, name, type)
        ));
    }

}
