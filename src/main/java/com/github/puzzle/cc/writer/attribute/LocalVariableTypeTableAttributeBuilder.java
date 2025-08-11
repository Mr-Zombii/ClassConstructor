package com.github.puzzle.cc.writer.attribute;

import com.github.puzzle.cc.parsing.attributes.LocalVariableTypeTableAttribute;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.util.ConstantPoolUtil;

import java.util.ArrayList;
import java.util.List;

public class LocalVariableTypeTableAttributeBuilder {

    List<LocalVariableTypeTableAttribute.LocalVariableType> list = new ArrayList<>();

    ConstantPool pool;
    public LocalVariableTypeTableAttributeBuilder(ConstantPool pool) {
        this.pool = pool;
    }

    public void addVar(int startPc, int length, String name, String signature, int idx) {
        int nameIdx = ConstantPoolUtil.getOrCreateUTF8(pool, name);
        int signatureIdx = ConstantPoolUtil.getOrCreateUTF8(pool, signature);

        list.add(new LocalVariableTypeTableAttribute.LocalVariableType(
                startPc, length, nameIdx, signatureIdx, idx
        ));
    }

    public LocalVariableTypeTableAttribute end() {
        int nameIdx = ConstantPoolUtil.getOrCreateUTF8(pool, "LocalVariableTypeTable");
        return new LocalVariableTypeTableAttribute(nameIdx, (list.size() * 10) + 2, list);
    }

}
