package com.github.puzzle.cc.parsing.attributes.annotations.values;

import com.github.puzzle.cc.parsing.attributes.annotations.RtAnnotation;
import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ElementValue {

    char tag;
    Value value;

    public ElementValue(DataInputStream inp) throws IOException {
        tag = (char) inp.readByte();
        value = new Value(tag, inp);
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeByte(tag);
        value.writeToStream(stream);
    }

    public static class Value {

        int valueIndex;
        Pair<Integer, Integer> enumConstValue;

        int classInfoIndex;

        RtAnnotation annotationValue;
        Pair<Integer, ElementValue[]> arrayValue;

        public Value(char tag, DataInputStream inp) throws IOException {
            switch (tag) {
                case 'B':
                case 'C':
                case 'D':
                case 'F':
                case 'I':
                case 'J':
                case 'K':
                case 'S':
                case 'Z':
                case 's':
                    classInfoIndex = -1999999999;
                    valueIndex = inp.readUnsignedShort();
                    break;
                case 'e':
                    classInfoIndex = -1999999999;
                    valueIndex = -1999999999;
                    enumConstValue = new Pair<>(inp.readUnsignedShort(), inp.readUnsignedShort());
                    break;
                case 'q':
                    valueIndex = -1999999999;
                    classInfoIndex = inp.readUnsignedShort();
                    break;
                case '@':
                    valueIndex = -1999999999;
                    classInfoIndex = -1999999999;
                    annotationValue = new RtAnnotation(inp);
                    break;
                case '[':
                    valueIndex = -1999999999;
                    classInfoIndex = -1999999999;
                    int valuesCount = inp.readUnsignedShort();
                    ElementValue[] values = new ElementValue[valuesCount];
                    for (int i = 0; i < valuesCount; i++) {
                        values[i] = new ElementValue(inp);
                    }
                    arrayValue = new Pair<>(valuesCount, values);
                    break;
            }
        }

        public void writeToStream(DataOutputStream stream) throws IOException {
            if (valueIndex != -1999999999) stream.writeShort(valueIndex);
            if (annotationValue != null) annotationValue.writeToStream(stream);
            if (classInfoIndex != -1999999999) stream.writeShort(classInfoIndex);
            if (enumConstValue != null) {
                stream.writeShort(enumConstValue.a);
                stream.writeShort(enumConstValue.b);
            }
            if (arrayValue != null) {
                stream.writeShort(arrayValue.a);
                for (ElementValue v : arrayValue.b) {
                    v.writeToStream(stream);
                }
            }
        }

    }

}
