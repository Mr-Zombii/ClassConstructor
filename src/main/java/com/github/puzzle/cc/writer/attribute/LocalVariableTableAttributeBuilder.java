package com.github.puzzle.cc.writer.attribute;

import com.github.puzzle.cc.parsing.attributes.LocalVariableTableAttribute;
import com.github.puzzle.cc.parsing.attributes.LocalVariableTypeTableAttribute;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.util.ConstantPoolUtil;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTableAttributeBuilder {

    List<LocalVariableTableAttribute.LocalVariableType> list = new ArrayList<>();

    ConstantPool pool;
    public LocalVariableTableAttributeBuilder(ConstantPool pool) {
        this.pool = pool;
    }

    public void addVar(int startPc, int length, String name, String descriptor, int idx) {
        int nameIdx = ConstantPoolUtil.getOrCreateUTF8(pool, name);
        int descriptorIdx = ConstantPoolUtil.getOrCreateUTF8(pool, descriptor);

        list.add(new LocalVariableTableAttribute.LocalVariableType(
                startPc, length, nameIdx, descriptorIdx, idx
        ));
    }

    public LocalVariableTableAttribute end() {
        int nameIdx = ConstantPoolUtil.getOrCreateUTF8(pool, "LocalVariableTable");
        return new LocalVariableTableAttribute(nameIdx, (list.size() * 10) + 2, list);
    }

}
