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

        AttributeInfo attributeInfo;
        switch (((UTF8CONSTANT) constant).asString()) {
            case "ConstantValue":
                attributeInfo = new ConstantValueAttribute(nameIndex, length, inp);
                break;
            case "Code":
                attributeInfo = new CodeAttribute(pool, nameIndex, length, inp);
                break;
            case "StackMapTable":
                attributeInfo = new StackMapAttribute(nameIndex, length, inp);
                break;
            case "Exceptions":
                attributeInfo = new ExceptionsAttribute(nameIndex, length, inp);
                break;
            case "InnerClasses":
                attributeInfo = new InnerClassesAttribute(nameIndex, length, inp);
                break;
            case "EnclosingMethod":
                attributeInfo = new EnclosingMethodAttribute(nameIndex, length, inp);
                break;
            case "Synthetic":
                attributeInfo = new SyntheticAttribute(nameIndex, length, inp);
                break;
            case "Signature":
                attributeInfo = new SignatureAttribute(nameIndex, length, inp);
                break;
            case "SourceFile":
                attributeInfo = new SourceFileAttribute(nameIndex, length, inp);
                break;
            case "SourceDebugExtension":
                attributeInfo = new SourceDebugExtension(nameIndex, length, inp);
                break;
            case "LineNumberTable":
                attributeInfo = new LineNumberTableAttribute(nameIndex, length, inp);
                break;
            case "LocalVariableTable":
                attributeInfo = new LocalVariableTableAttribute(nameIndex, length, inp);
                break;
            case "LocalVariableTypeTable":
                attributeInfo = new LocalVariableTypeTableAttribute(nameIndex, length, inp);
                break;
            case "Deprecated":
                attributeInfo = new DeprecatedAttribute(nameIndex, length, inp);
                break;
            case "RuntimeVisibleAnnotations":
                attributeInfo = new RuntimeVisibleAnnotationsAttribute(nameIndex, length, inp);
                break;
            case "RuntimeInvisibleAnnotations":
                attributeInfo = new RuntimeInvisibleAnnotationsAttribute(nameIndex, length, inp);
                break;
            case "RuntimeInvisibleParameterAnnotations":
                attributeInfo = new RuntimeVisibleParameterAnnotationsAttribute(nameIndex, length, inp);
                break;
            case "RuntimeVisibleParameterAnnotations":
                attributeInfo = new RuntimeInvisibleParameterAnnotationsAttribute(nameIndex, length, inp);
                break;
            case "RuntimeVisibleTypeAnnotations":
                attributeInfo = new RuntimeVisibleTypeAnnotationsAttribute(nameIndex, length, inp);
                break;
            case "RuntimeInvisibleTypeAnnotations":
                attributeInfo = new RuntimeInvisibleTypeAnnotationsAttribute(nameIndex, length, inp);
                break;
            case "AnnotationDefault":
                attributeInfo = new AnnotationDefaultAttribute(nameIndex, length, inp);
                break;
            case "BootstrapMethods":
                attributeInfo = new BootstrapMethodsAttribute(nameIndex, length, inp);
                break;
            case "MethodParameters":
                attributeInfo = new MethodParametersAttribute(nameIndex, length, inp);
                break;
            case "Module":
                attributeInfo = new ModuleAttribute(nameIndex, length, inp);
                break;
            case "ModulePackages":
                attributeInfo = new ModulePackagesAttribute(nameIndex, length, inp);
                break;
            case "ModuleMainClass":
                attributeInfo = new ModuleMainClassAttribute(nameIndex, length, inp);
                break;
            case "NestHost":
                attributeInfo = new NestHostAttribute(nameIndex, length, inp);
                break;
            case "NestMembers":
                attributeInfo = new NestMembersAttribute(nameIndex, length, inp);
                break;
            case "Record":
                attributeInfo = new RecordAttribute(pool, nameIndex, length, inp);
                break;
            case "PermittedSubclasses":
                attributeInfo = new PermittedSubclassesAttribute(nameIndex, length, inp);
                break;
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
            attributes[i] = readAttribute(pool, inp);
        }

        return new Attributes(attributes);
    }

    public int getLength() {
        return length;
    }
}
