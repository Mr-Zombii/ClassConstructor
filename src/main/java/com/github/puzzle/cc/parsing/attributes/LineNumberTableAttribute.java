package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.IOException;

public class LineNumberTableAttribute extends AttributeInfo {

    int lineNumberTableLength;
    LineNumber[] lineNumbers;

    public LineNumberTableAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        lineNumberTableLength = inp.readUnsignedShort();
        lineNumbers = new LineNumber[lineNumberTableLength];
        for (int i = 0; i < lineNumberTableLength; i++) {
            lineNumbers[i] = new LineNumber(inp);
        }
    }

    public static class LineNumber {

        int startPc;
        int lineNum;

        public LineNumber(DataInputStream inp) throws IOException {
            startPc = inp.readUnsignedShort();
            lineNum = inp.readUnsignedShort();
        }

    }
}
