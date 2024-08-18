package com.github.puzzle.cc.parsing.attributes;

import com.github.puzzle.cc.parsing.attributes.module.ModuleExport;
import com.github.puzzle.cc.parsing.attributes.module.ModuleOpen;
import com.github.puzzle.cc.parsing.attributes.module.ModuleRequire;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class ModuleAttribute extends AttributeInfo {

    int moduleNameIndex;
    int moduleFlags;
    int moduleVersionIndex;

    ModuleRequire[] requires;
    ModuleExport[] exports;
    ModuleOpen[] opens;

    int[] usesIndexes;

    public ModuleAttribute(int nameIndex, int length, DataInputStream inp) throws IOException {
        super(nameIndex, length, inp);

        moduleNameIndex = inp.readUnsignedShort();
        moduleFlags = inp.readUnsignedShort();
        moduleVersionIndex = inp.readUnsignedShort();

        requires = new ModuleRequire[inp.readUnsignedShort()];
        for (int i = 0; i < requires.length; i++) {
            requires[i] = new ModuleRequire(inp);
        }

        exports = new ModuleExport[inp.readUnsignedShort()];
        for (int i = 0; i < exports.length; i++) {
            exports[i] = new ModuleExport(inp);
        }

        opens = new ModuleOpen[inp.readUnsignedShort()];
        for (int i = 0; i < opens.length; i++) {
            opens[i] = new ModuleOpen(inp);
        }

        usesIndexes = new int[inp.readUnsignedShort()];
        for (int i = 0; i < usesIndexes.length; i++) {
            usesIndexes[i] = inp.readUnsignedShort();
        }
    }

    @Override
    public void writeToStream(DataOutputStream outputStream) throws IOException {
        super.writeToStream(outputStream);

        outputStream.writeShort(moduleNameIndex);
        outputStream.writeShort(moduleFlags);
        outputStream.writeShort(moduleVersionIndex);

        outputStream.writeShort(requires.length);
        for (ModuleRequire require : requires) require.writeToStream(outputStream);
        outputStream.writeShort(exports.length);
        for (ModuleExport export : exports) export.writeToStream(outputStream);
        outputStream.writeShort(opens.length);
        for (ModuleOpen open : opens) open.writeToStream(outputStream);

        outputStream.writeShort(usesIndexes.length);
        for (int index : usesIndexes) outputStream.writeShort(index);
    }
}
