package com.github.puzzle.cc.parsing.attributes;


import com.github.puzzle.cc.parsing.constants.GenericConstant;
import com.github.puzzle.cc.parsing.constants.UTF8CONSTANT;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class AttributeInfo {

    int nameIndex;
    int length;

    public AttributeInfo(int nameIndex, int length, DataInputStream inp) {
        this.nameIndex = nameIndex;
        this.length = length;
    }

    public String getName(ConstantPool pool) {
        return ((UTF8CONSTANT)pool.get(nameIndex)).asString();
    }

    String type;

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static AttributeInfo readAttribute(ConstantPool pool, DataInputStream inp) throws IOException {
        int nameIndex = inp.readUnsignedShort();
        int length = inp.readInt();

        GenericConstant constant = pool.get(nameIndex);
        System.out.println(((UTF8CONSTANT) constant).asString());

        AttributeInfo attributeInfo = switch (((UTF8CONSTANT) constant).asString()) {
            case "ConstantValue": yield new ConstantValueAttribute(nameIndex, length, inp);
            case "Code": yield new CodeAttribute(pool, nameIndex, length, inp);
            case "StackMapTable": yield new StackMapAttribute(nameIndex, length, inp);
            case "Exceptions": yield new ExceptionsAttribute(nameIndex, length, inp);
            case "InnerClasses": yield new InnerClassesAttribute(nameIndex, length, inp);
            case "EnclosingMethod": yield new EnclosingMethodAttribute(nameIndex, length, inp);
            case "Synthetic": yield new SyntheticAttribute(nameIndex, length, inp);
            case "Signature": yield new SignatureAttribute(nameIndex, length, inp);
            case "SourceFile": yield new SourceFileAttribute(nameIndex, length, inp);
            case "SourceDebugExtension": yield new SourceDebugExtension(nameIndex, length, inp);
            case "LineNumberTable": yield new LineNumberTableAttribute(nameIndex, length, inp);
            case "LocalVariableTable": yield new LocalVariableTableAttribute(nameIndex, length, inp);
            case "LocalVariableTypeTable": yield new LocalVariableTypeTableAttribute(nameIndex, length, inp);
            case "Deprecated": yield new DeprecatedAttribute(nameIndex, length, inp);
            case "RuntimeVisibleAnnotations": yield new RuntimeVisibleAnnotationsAttribute(nameIndex, length, inp);
            case "RuntimeInvisibleAnnotations": yield new RuntimeInvisibleAnnotationsAttribute(nameIndex, length, inp);
            case "RuntimeInvisibleParameterAnnotations": yield new RuntimeVisibleParameterAnnotationsAttribute(nameIndex, length, inp);
            case "RuntimeVisibleParameterAnnotations": yield new RuntimeInvisibleParameterAnnotationsAttribute(nameIndex, length, inp);
            case "RuntimeVisibleTypeAnnotations": yield new RuntimeVisibleTypeAnnotationsAttribute(nameIndex, length, inp);
            case "RuntimeInvisibleTypeAnnotations": yield new RuntimeInvisibleTypeAnnotationsAttribute(nameIndex, length, inp);
            case "AnnotationDefault": yield new AnnotationDefaultAttribute(nameIndex, length, inp);
            case "BootstrapMethods": yield new BootstrapMethodsAttribute(nameIndex, length, inp);
            case "MethodParameters": yield new MethodParametersAttribute(nameIndex, length, inp);
            case "Module": yield new ModuleAttribute(nameIndex, length, inp);
            case "ModulePackages": yield new ModulePackagesAttribute(nameIndex, length, inp);
            case "ModuleMainClass": yield new ModuleMainClassAttribute(nameIndex, length, inp);
            case "NestHost": yield new NestHostAttribute(nameIndex, length, inp);
            case "NestMembers": yield new NestMembersAttribute(nameIndex, length, inp);
            case "Record": yield new RecordAttribute(pool, nameIndex, length, inp);
            case "PermittedSubclasses": yield new PermittedSubclassesAttribute(nameIndex, length, inp);
            default:
                throw new IllegalStateException("Unexpected value: " + ((UTF8CONSTANT) constant).asString());
        };

        attributeInfo.setType(((UTF8CONSTANT) constant).asString());
        return attributeInfo;
    }

    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(nameIndex);
        outputStream.writeInt(length);
    }

    public static Attributes readAttributes(ConstantPool pool, int length, DataInputStream inp) throws IOException {
        AttributeInfo[] attributes = new AttributeInfo[length];

        for (int i = 0; i < length; i++) {
            System.out.println("\\/ Attrib #" + (i + 1) + " of " + length);
            attributes[i] = readAttribute(pool, inp);
        }

        return new Attributes(attributes);
    }

    public int getLength() {
        return length;
    }
}
