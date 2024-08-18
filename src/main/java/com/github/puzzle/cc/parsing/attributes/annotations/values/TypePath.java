package com.github.puzzle.cc.parsing.attributes.annotations.values;

import com.github.puzzle.cc.util.Pair;

import java.io.DataInputStream;
import java.io.IOException;

public class TypePath {

    byte pathLength;
    Pair<Byte, Byte>[] path;

    public TypePath(DataInputStream inp) throws IOException {
        pathLength = inp.readByte();
        path = new Pair[pathLength];
        for (int i = 0; i < path.length; i++) {
            path[i] = new Pair<>(inp.readByte(), inp.readByte());
        }
    }

}
