package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class LocalVariableTypeTableAttribute extends AttributeInfo {

    short localVariableTableLength;
    LocalVariable[] localVariableTypes;

    public LocalVariableTypeTableAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        localVariableTableLength = reader.consumeU2();
        localVariableTypes = new LocalVariable[localVariableTableLength];
        for (int i = 0; i < localVariableTableLength; i++) {
            localVariableTypes[i] = new LocalVariable(reader);
        }
    }

    public static class LocalVariable {

        short startPc;
        short length;
        short nameIndex;
        short descriptorIndex;
        short index;

        public LocalVariable(ClassReader reader) {
            startPc = reader.consumeU2();
            length = reader.consumeU2();
            nameIndex = reader.consumeU2();
            descriptorIndex = reader.consumeU2();
            index = reader.consumeU2();
        }

    }
}
