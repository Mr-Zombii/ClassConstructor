package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class LocalVariableTableAttribute extends AttributeInfo {

    short localVariableTypeTableLength;
    LocalVariableType[] localVariables;

    public LocalVariableTableAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        localVariableTypeTableLength = reader.consumeU2();
        localVariables = new LocalVariableType[localVariableTypeTableLength];
        for (int i = 0; i < localVariableTypeTableLength; i++) {
            localVariables[i] = new LocalVariableType(reader);
        }
    }

    public static class LocalVariableType {

        short startPc;
        short length;
        short nameIndex;
        short signatureIndex;
        short index;

        public LocalVariableType(ClassReader reader) {
            startPc = reader.consumeU2();
            length = reader.consumeU2();
            nameIndex = reader.consumeU2();
            signatureIndex = reader.consumeU2();
            index = reader.consumeU2();
        }

    }
}
