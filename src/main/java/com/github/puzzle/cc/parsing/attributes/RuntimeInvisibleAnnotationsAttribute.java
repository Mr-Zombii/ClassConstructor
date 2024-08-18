package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.annotations.RtAnnotation;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class RuntimeInvisibleAnnotationsAttribute extends AttributeInfo {

    RtAnnotation[] annotations;

    public RuntimeInvisibleAnnotationsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        annotations = new RtAnnotation[inp.readUnsignedShort()];
        for (int i = 0; i < annotations.length; i++) {
            annotations[i] = new RtAnnotation(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(annotations.length);
        for (RtAnnotation annotation : annotations) annotation.writeToStream(outputStream);
    }
}
