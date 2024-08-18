package com.github.puzzle.cc.parsing.attributes.annotations.values;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class TypePath {

    Pair<Byte, Byte>[] path;

    public TypePath(DataInputStream inp) throws IOException {
        path = new Pair[inp.readByte()];
        for (int i = 0; i < path.length; i++) {
            path[i] = new Pair<>(inp.readByte(), inp.readByte());
        }
    }

    public void writeToStream(DataOutputStream stream) throws IOException {
        stream.writeByte(path.length);
        for (Pair<Byte, Byte> bytePair : path) {
            stream.writeByte(bytePair.a);
            stream.writeByte(bytePair.b);
        }
    }

}
