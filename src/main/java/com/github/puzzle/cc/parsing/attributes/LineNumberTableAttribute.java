package com.github.puzzle.cc.parsing.attributes;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class LineNumberTableAttribute extends AttributeInfo {

    LineNumber[] lineNumbers;

    public LineNumberTableAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        lineNumbers = new LineNumber[inp.readUnsignedShort()];
        for (int i = 0; i < lineNumbers.length; i++) {
            lineNumbers[i] = new LineNumber(inp);
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(lineNumbers.length);
        for (LineNumber lineNumber : lineNumbers) {
            lineNumber.writeToStream(outputStream);
        }
    }

    public static class LineNumber {

        int startPc;
        int lineNum;

        public LineNumber(DataInputStream inp) throws IOException {
            startPc = inp.readUnsignedShort();
            lineNum = inp.readUnsignedShort();
        }

        public void writeToStream(DataOutputStream outputStream) throws IOException {
            outputStream.writeShort(startPc);
            outputStream.writeShort(lineNum);
        }
    }
}
