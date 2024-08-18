package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.annotations.TypeAnnotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RuntimeVisibleTypeAnnotationsAttribute extends AttributeInfo {

    TypeAnnotation[] annotations;

    public RuntimeVisibleTypeAnnotationsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        annotations = new TypeAnnotation[inp.readUnsignedShort()];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = new TypeAnnotation(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(annotations.length);
        for (TypeAnnotation annotation : annotations) annotation.writeToStream(outputStream);
    }
    
}
