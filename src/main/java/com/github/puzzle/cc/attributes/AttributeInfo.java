package com.github.puzzle.cc.attributes;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.values.*;
import com.github.puzzle.cc.constant_pool.CP_INFO;
import com.github.puzzle.cc.constant_pool.tags.UTF8CONSTANT;

public class AttributeInfo {

    short nameIndex;
    int length;

    public AttributeInfo(short nameIndex, int length, ClassReader reader) {
        this.nameIndex = nameIndex;
        this.length = length;
    }

    public static AttributeInfo readAttribute(ClassReader reader) {
        short nameIndex = reader.consumeU2();
        int length = reader.consumeU4();

        CP_INFO constant = reader.constant_pool[nameIndex];

        if (!(constant instanceof UTF8CONSTANT)) return null;
        return switch (((UTF8CONSTANT) constant).asString()) {
            case "ConstantValue": yield new ConstantAttribute(nameIndex, length, reader);
            case "Code": yield new CodeAttribute(nameIndex, length, reader);
            case "StackMapTable": yield new StackMapAttribute(nameIndex, length, reader);
            case "Exceptions": yield new ExceptionsAttribute(nameIndex, length, reader);
            case "InnerClasses": yield new InnerClassesAttribute(nameIndex, length, reader);
            case "EnclosingMethod": yield new EnclosingMethodAttribute(nameIndex, length, reader);
            case "Synthetic": yield new SyntheticAttribute(nameIndex, length, reader);
            case "Signature": yield new SignatureAttribute(nameIndex, length, reader);
            case "SourceFile": yield new SourceFileAttirbute(nameIndex, length, reader);
            case "SourceDebugExtension": yield new SourceDebugExtension(nameIndex, length, reader);
            case "LineNumberTable": yield new LineNumberTableAttribute(nameIndex, length, reader);
            case "LocalVariableTable": yield new LocalVariableTableAttribute(nameIndex, length, reader);
            case "LocalVariableTypeTable": yield new LocalVariableTypeTableAttribute(nameIndex, length, reader);
            case "Deprecated": yield new DeprecatedAttribute(nameIndex, length, reader);
            case "RuntimeVisibleAnnotations": yield new RuntimeVisibleAnnotationsAttribute(nameIndex, length, reader);
            case "RuntimeInvisibleAnnotations": yield new RuntimeInvisibleAnnotationsAttribute(nameIndex, length, reader);
            case "RuntimeInvisibleParameterAnnotations": yield new RuntimeVisibleParameterAnnotationsAttribute(nameIndex, length, reader);
            case "RuntimeVisibleParameterAnnotations": yield new RuntimeInvisibleParameterAnnotationsAttribute(nameIndex, length, reader);
            /* TODO: FINISH "RuntimeVisibleTypeAnnotations" THEN CONTINUE ON REST OF IT */
            case "RuntimeVisibleTypeAnnotations": yield new RuntimeVisibleTypeAnnotationsAttribute(nameIndex, length, reader);
            default:
                throw new IllegalStateException("Unexpected value: " + ((UTF8CONSTANT) constant).asString());
        };
    }

    public static AttributeInfo[] readAttributes(short length, ClassReader reader) {
        AttributeInfo[] attributes = new AttributeInfo[length];

        for (int i = 0; i < length; i++) {
            attributes[i] = readAttribute(reader);
        }

        return attributes;
    }

}
