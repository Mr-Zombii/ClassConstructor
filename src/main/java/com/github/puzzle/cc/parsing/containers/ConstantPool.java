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
        if (constant instanceof UTF8CONSTANT) {
            int index = findUTF8((UTF8CONSTANT) constant);
            if (index != -1) return index;
        }
        int index = constants.length;
        if (constants.length < constants.length + 1) {
            constants = Arrays.copyOf(constants, index + 1);
            constants[index] = constant;
        }
        return index + 1;
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
            constants[i] = constant;
        }
    }

    public static GenericConstant readConstant(DataInputStream inp) throws IOException {
        TagType type = TagType.fromU1(inp.readByte());

        switch (type) {
            case CONSTANT_UTF8:
                return new UTF8CONSTANT(type, inp);
            case CONSTANT_INT:
                return new IntegerConstant(type, inp);
            case CONSTANT_FLOAT:
                return new FloatConstant(type, inp);
            case CONSTANT_LONG:
                return new LongConstant(type, inp);
            case CONSTANT_DOUBLE:
                return new DoubleConstant(type, inp);
            case CONSTANT_CLASS:
                return new ClassConstant(type, inp);
            case CONSTANT_STRING:
                return new StringConstant(type, inp);
            case CONSTANT_FIELD_REF:
                return new FieldRefConstant(type, inp);
            case CONSTANT_METHOD_REF:
                return new MethodRefConstant(type, inp);
            case CONSTANT_INTERFACE_METHOD_REF:
                return new InterfaceMethodRefConstant(type, inp);
            case CONSTANT_NAME_AND_TYPE:
                return new NameAndTypeConstant(type, inp);
            case CONSTANT_METHOD_HANDLE:
                return new MethodHandleConstant(type, inp);
            case CONSTANT_METHOD_TYPE:
                return new MethodTypeConstant(type, inp);
            case CONSTANT_DYNAMIC:
                return new DynamicConstant(type, inp);
            case CONSTANT_INVOKE_DYNAMIC:
                return new InvokeDynamicConstant(type, inp);
            case CONSTANT_MODULE:
                return new ModuleConstant(type, inp);
            case CONSTANT_PACKAGE:
                return new PackageConstant(type, inp);
            default:
                throw new IllegalArgumentException();
        }
    }

    public GenericConstant get(int i) {
        return constants[i - 1];
    }

    public int findUTF8(UTF8CONSTANT constant) {
        return findUTF8(constant.asString());
    }

    public int findUTF8(String contents) {
        for (int i = 0; i < constants.length; i++) {
            GenericConstant constant = constants[i];
            if (constant instanceof UTF8CONSTANT && ((UTF8CONSTANT) constant).asString().equals(contents))
                return i + 1;
        }
        return -1;
    }

    public int findUTF8OrCreate(String contents) {
        int idx = findUTF8(contents);
        if (idx != -1) return idx;
        return push(new UTF8CONSTANT(contents));
    }

    public String stringifyConstantSafe(GenericConstant constant) {
        StringBuilder builder = new StringBuilder();
        switch (constant.getTag()) {
            case CONSTANT_UTF8:
                builder.append(" { value: \"" + ((UTF8CONSTANT) constant).asString() + "\" }");
                break;
            case CONSTANT_STRING:
                builder.append(" { idx: \"" + ((StringConstant) constant).getIndex() + "\" }");
                break;
            case CONSTANT_CLASS:
                builder.append(" { idx: \"" + ((ClassConstant) constant).getNameIdx() + "\" }");
                break;
            case CONSTANT_NAME_AND_TYPE:
                builder.append(" { nameIdx: \"" + ((NameAndTypeConstant) constant).getNameIndex() + "\", descriptorIdx: \"" + ((NameAndTypeConstant) constant).getDescriptorIndex() + "\" }");
                break;
            case CONSTANT_FIELD_REF:
                builder.append(" { clazzIdx: " + ((FieldRefConstant) constant).getClassIndex() + ", nameAndTypeIdx: " + ((FieldRefConstant) constant).getNameAndTypeIndex() + " }");
                break;
            case CONSTANT_METHOD_REF:
                builder.append(" { clazzIdx: " + ((MethodRefConstant) constant).getClassIndex() + ", nameAndTypeIdx: " + ((MethodRefConstant) constant).getNameAndTypeIndex() + " }");
                break;
        }
        return builder.toString();
    }


    public String stringifyConstant(GenericConstant constant) {
        StringBuilder builder = new StringBuilder();
        switch (constant.getTag()) {
            case CONSTANT_UTF8:
                builder.append(" { value: \"" + ((UTF8CONSTANT) constant).asString() + "\" }");
                break;
            case CONSTANT_STRING:
                builder.append(" { value: \"" + ((StringConstant) constant).getString(this) + "\" }");
                break;
            case CONSTANT_CLASS:
                builder.append(" { value: \"" + ((ClassConstant) constant).getName(this) + "\" }");
                break;
            case CONSTANT_NAME_AND_TYPE:
                builder.append(" { name: \"" + ((NameAndTypeConstant) constant).getName(this) + "\", descriptor: \"" + ((NameAndTypeConstant) constant).getDescriptor(this) + "\" }");
                break;
            case CONSTANT_FIELD_REF:
                builder.append(" { clazz: " + stringifyConstant(((FieldRefConstant) constant).getClass(this)) + ", nameAndType: " + stringifyConstant(((FieldRefConstant) constant).getNameAndType(this)) + " }");
                break;
            case CONSTANT_METHOD_REF:
                builder.append(" { clazz: " + stringifyConstant(((MethodRefConstant) constant).getClass(this)) + ", nameAndType: " + stringifyConstant(((MethodRefConstant) constant).getNameAndType(this)) + " }");
                break;
        }
        return builder.toString();
    }

    @Override
    public String toString() {
        return asStringDeep();
    }

    public String asStringDeep() {
        StringBuilder builder = new StringBuilder("[\n");
        for (int i = 0; i < constants.length; i++) {
            GenericConstant constant = constants[i];
            builder.append("\t").append(i).append(": ");

            builder.append(constant.getTag().name());
            builder.append(stringifyConstant(constant));
            if (i != constants.length - 1) builder.append(",");
            builder.append("\n");
        }
        return builder.append("]").toString();
    }

    public String asStringSimple() {
        StringBuilder builder = new StringBuilder("[\n");
        for (int i = 0; i < constants.length; i++) {
            GenericConstant constant = constants[i];
            builder.append("\t").append(i).append(": ");
            builder.append(constant.getTag().name());
            if (i != constants.length - 1) builder.append(",");
            builder.append("\n");
        }
        return builder.append("]").toString();
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
