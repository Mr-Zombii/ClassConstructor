package com.github.puzzle.cc.parsing.attributes;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import com.github.puzzle.cc.parsing.attributes.annotations.RtAnnotation;
import com.github.puzzle.cc.parsing.attributes.annotations.TypeAnnotation;
import com.github.puzzle.cc.util.Pair;

public class RuntimeInvisibleParameterAnnotationsAttribute extends AttributeInfo {

    Pair<Integer, RtAnnotation[]>[] annotationPairs;

    public RuntimeInvisibleParameterAnnotationsAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        annotationPairs = new Pair[inp.readUnsignedByte()];

        for (int i = 0; i < annotationPairs.length; i++) {
            int annotationCount = inp.readUnsignedShort();
            RtAnnotation[] values = new RtAnnotation[annotationCount];
            for (int j = 0; j < annotationCount; j++) {
                values[j] = new RtAnnotation(inp);
            }
            annotationPairs[i] = new Pair<>(annotationCount, values);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeByte(annotationPairs.length);
        for (Pair<Integer, RtAnnotation[]> annotationPair : annotationPairs) {
            outputStream.writeShort(annotationPair.a);
            for (RtAnnotation annotation : annotationPair.b) {
                annotation.writeToStream(outputStream);
            }
        }
    }
    
}
