package com.github.puzzle.cc.parser.attributes.values;

import com.github.puzzle.cc.parser.ClassReader;
import com.github.puzzle.cc.parser.attributes.AttributeInfo;
import com.github.puzzle.cc.parser.attributes.values.module.ModuleExport;
import com.github.puzzle.cc.parser.attributes.values.module.ModuleOpen;
import com.github.puzzle.cc.parser.attributes.values.module.ModuleRequire;

public class ModuleAttribute extends AttributeInfo {

    short moduleNameIndex;
    short moduleFlags;
    short moduleVersionIndex;

    short requiresCount;
    ModuleRequire[] requires;

    short exportsCount;
    ModuleExport[] exports;

    short opensCount;
    ModuleOpen[] opens;

    short usesCount;
    short[] usesIndexes;

    public ModuleAttribute(short nameIndex, int length, ClassReader reader) {
        super(nameIndex, length, reader);

        moduleNameIndex = reader.consumeU2();
        moduleFlags = reader.consumeU2();
        moduleVersionIndex = reader.consumeU2();

        requiresCount = reader.consumeU2();
        requires = new ModuleRequire[requiresCount];
        for (int i = 0; i < requiresCount; i++) {
            requires[i] = new ModuleRequire(reader);
        }

        exportsCount = reader.consumeU2();
        exports = new ModuleExport[exportsCount];
        for (int i = 0; i < exportsCount; i++) {
            exports[i] = new ModuleExport(reader);
        }

        opensCount = reader.consumeU2();
        opens = new ModuleOpen[opensCount];
        for (int i = 0; i < opensCount; i++) {
            opens[i] = new ModuleOpen(reader);
        }

        usesCount = reader.consumeU2();
        usesIndexes = new short[usesCount];
        for (int i = 0; i < usesCount; i++) {
            usesIndexes[i] = reader.consumeU2();
        }
    }
}
