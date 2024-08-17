package com.github.puzzle.cc.parser.attributes.values.annotations;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.util.Pair;

public class TypePath {

    byte pathLength;
    Pair<Byte, Byte>[] path;

    public TypePath(ClassReader reader) {
        pathLength = reader.consumeU1();
        path = new Pair[pathLength];
        for (int i = 0; i < path.length; i++) {
            path[i] = new Pair<>(reader.consumeU1(), reader.consumeU1());
        }
    }

}
