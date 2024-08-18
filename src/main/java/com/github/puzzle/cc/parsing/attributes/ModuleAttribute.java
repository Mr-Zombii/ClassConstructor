package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.module.ModuleExport;
import com.github.puzzle.cc.parsing.attributes.module.ModuleOpen;
import com.github.puzzle.cc.parsing.attributes.module.ModuleRequire;

import java.io.DataInputStream;
import java.io.IOException;

public class ModuleAttribute extends AttributeInfo {

    int moduleNameIndex;
    int moduleFlags;
    int moduleVersionIndex;

    int requiresCount;
    ModuleRequire[] requires;

    int exportsCount;
    ModuleExport[] exports;

    int opensCount;
    ModuleOpen[] opens;

    int usesCount;
    int[] usesIndexes;

    public ModuleAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        moduleNameIndex = inp.readUnsignedShort();
        moduleFlags = inp.readUnsignedShort();
        moduleVersionIndex = inp.readUnsignedShort();

        requiresCount = inp.readUnsignedShort();
        requires = new ModuleRequire[requiresCount];
        for (int i = 0; i < requiresCount; i++) {
            requires[i] = new ModuleRequire(inp);
        }

        exportsCount = inp.readUnsignedShort();
        exports = new ModuleExport[exportsCount];
        for (int i = 0; i < exportsCount; i++) {
            exports[i] = new ModuleExport(inp);
        }

        opensCount = inp.readUnsignedShort();
        opens = new ModuleOpen[opensCount];
        for (int i = 0; i < opensCount; i++) {
            opens[i] = new ModuleOpen(inp);
        }

        usesCount = inp.readUnsignedShort();
        usesIndexes = new int[usesCount];
        for (int i = 0; i < usesCount; i++) {
            usesIndexes[i] = inp.readUnsignedShort();
        }
    }
}
