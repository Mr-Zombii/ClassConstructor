package com.github.puzzle.cc.attributes.values;

import com.github.puzzle.cc.ClassReader;
import com.github.puzzle.cc.attributes.AttributeInfo;

public class LineNumberTableAttribute extends AttributeInfo {

    short lineNumberTableLength;
    LineNumber[] lineNumbers;

    public LineNumberTableAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        lineNumberTableLength = reader.consumeU2();
        lineNumbers = new LineNumber[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumbers[i] = new LineNumber(reader);
        }
    }

    public static class LineNumber {

        short startPc;
        short lineNum;

        public LineNumber(ClassReader reader) {
            startPc = reader.consumeU2();
            lineNum = reader.consumeU2();
        }

    }
}
