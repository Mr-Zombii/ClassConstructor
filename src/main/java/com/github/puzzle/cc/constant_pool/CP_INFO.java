package com.github.puzzle.cc.constant_pool;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.constant_pool.tags.*;

public interface CP_INFO {

    TagType getTag();

    static CP_INFO readConstant(ClassReader reader) {
        TagType type = TagType.fromU1(reader.consumeU1());

        return switch (type) {
            case CONSTANT_UTF8 -> new UTF8CONSTANT(type, reader);
            case CONSTANT_INT -> new IntegerConstant(type, reader);
            case CONSTANT_FLOAT -> new FloatConstant(type, reader);
            case CONSTANT_LONG -> new LongConstant(type, reader);
            case CONSTANT_DOUBLE -> new DoubleConstant(type, reader);
            case CONSTANT_CLASS -> new ClassConstant(type, reader);
            case CONSTANT_STRING -> new StringConstant(type, reader);
            case CONSTANT_FIELD_REF -> new FieldRefConstant(type, reader);
            case CONSTANT_METHOD_REF -> new MethodRefConstant(type, reader);
            case CONSTANT_INTERFACE_METHOD_REF -> new InterfaceMethodRefConstant(type, reader);
            case CONSTANT_NAME_AND_TYPE -> new NameAndTypeConstant(type, reader);
            case CONSTANT_METHOD_HANDLE -> new MethodHandleConstant(type, reader);
            case CONSTANT_METHOD_TYPE -> new MethodTypeConstant(type, reader);
            case CONSTANT_DYNAMIC -> new DynamicConstant(type, reader);
            case CONSTANT_INVOKE_DYNAMIC -> new InvokeDynamicConstant(type, reader);
            case CONSTANT_MODULE -> new ModuleConstant(type, reader);
            case CONSTANT_PACKAGE -> new PackageConstant(type, reader);
        };
    }
}
