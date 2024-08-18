package com.github.puzzle.cc.parsing.containers;

import com.github.puzzle.cc.parsing.constants.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConstantPool {

    public GenericConstant[] constants;

    public ConstantPool() {
        constants = new GenericConstant[0];
    }

    public ConstantPool(ConstantPool old) {
        constants = Arrays.copyOf(old.constants, old.constants.length);
    }

    public int push(GenericConstant constant) {
        int oldLength = constants.length;
        if (constants.length < constants.length + 1) {
            constants = Arrays.copyOf(constants, constants.length + 1);
            constants[oldLength] = constant;
        }
        return oldLength + 1;
    }

    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(constants.length + 1);
        for (GenericConstant constant : constants) {
            constant.writeToStream(outputStream);
        }
    }

    public ConstantPool(DataInputStream inp) throws IOException {
        constants = new GenericConstant[inp.readUnsignedShort() - 1];
        for (int i = 0; i < constants.length; i++) {
            GenericConstant constant = ConstantPool.readConstant(inp);
            System.out.println("CL " + (i + 1) + " " + constant.getTag().name());
            constants[i] = constant;
        }
    }

    public static GenericConstant readConstant(DataInputStream inp) throws IOException {
        TagType type = TagType.fromU1(inp.readByte());

        return switch (type) {
            case CONSTANT_UTF8 -> new UTF8CONSTANT(type, inp);
            case CONSTANT_INT -> new IntegerConstant(type, inp);
            case CONSTANT_FLOAT -> new FloatConstant(type, inp);
            case CONSTANT_LONG -> new LongConstant(type, inp);
            case CONSTANT_DOUBLE -> new DoubleConstant(type, inp);
            case CONSTANT_CLASS -> new ClassConstant(type, inp);
            case CONSTANT_STRING -> new StringConstant(type, inp);
            case CONSTANT_FIELD_REF -> new FieldRefConstant(type, inp);
            case CONSTANT_METHOD_REF -> new MethodRefConstant(type, inp);
            case CONSTANT_INTERFACE_METHOD_REF -> new InterfaceMethodRefConstant(type, inp);
            case CONSTANT_NAME_AND_TYPE -> new NameAndTypeConstant(type, inp);
            case CONSTANT_METHOD_HANDLE -> new MethodHandleConstant(type, inp);
            case CONSTANT_METHOD_TYPE -> new MethodTypeConstant(type, inp);
            case CONSTANT_DYNAMIC -> new DynamicConstant(type, inp);
            case CONSTANT_INVOKE_DYNAMIC -> new InvokeDynamicConstant(type, inp);
            case CONSTANT_MODULE -> new ModuleConstant(type, inp);
            case CONSTANT_PACKAGE -> new PackageConstant(type, inp);
        };
    }

    public enum TagType {

        CONSTANT_UTF8(1),
        // missing 2
        CONSTANT_INT(3),
        CONSTANT_FLOAT(4),
        CONSTANT_LONG(5),
        CONSTANT_DOUBLE(6),
        CONSTANT_CLASS(7),
        CONSTANT_STRING(8),
        CONSTANT_FIELD_REF(9),
        CONSTANT_METHOD_REF(10),
        CONSTANT_INTERFACE_METHOD_REF(11),
        CONSTANT_NAME_AND_TYPE(12),
        // missing 13, 14
        CONSTANT_METHOD_HANDLE(15),
        CONSTANT_METHOD_TYPE(16),
        CONSTANT_DYNAMIC(17),
        CONSTANT_INVOKE_DYNAMIC(18),
        CONSTANT_MODULE(19),
        CONSTANT_PACKAGE(20);

        public final byte tag;

        TagType(int num) {
            this.tag = (byte) num;
            init(this, (byte) num);
        }

        static Map<Byte, TagType> byteTagTypeMap;

        static void init(TagType tagType, byte num) {
            if (byteTagTypeMap == null) byteTagTypeMap = new HashMap<>();
            byteTagTypeMap.put(num, tagType);
        }

        public static TagType fromU1(byte u1) {
            return byteTagTypeMap.getOrDefault(u1, CONSTANT_UTF8);
        }

    }


}
