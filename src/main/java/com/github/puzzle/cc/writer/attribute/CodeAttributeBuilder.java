package com.github.puzzle.cc.writer.attribute;

import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.attributes.CodeAttribute;
import com.github.puzzle.cc.parsing.constants.UTF8CONSTANT;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.util.ArrayList;
import java.util.List;

public class CodeAttributeBuilder {
    private final int maxStack;
    private final int maxLocals;
    private int index;
    ConstantPool pool;

    List<CodeAttribute.ExceptionValue> exceptions = new ArrayList<>();
    int currentPc;
    private Attributes attributes = new Attributes();

    public CodeAttributeBuilder(ConstantPool pool, int maxStack, int maxLocals) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;

        index = pool.findUTF8("Code");
        if (index == -1) index = pool.push(new UTF8CONSTANT("Code"));
    }

    public void startTryCatch() {
        CodeAttribute.ExceptionValue value = new CodeAttribute.ExceptionValue();
        value.setStartPc(currentPc);
        exceptions.add(value);
    }

    public void setTryCatchHandler(int catchType) {
        CodeAttribute.ExceptionValue value = exceptions.get(exceptions.size() - 1);
        value.setCatchType(catchType);
        value.setHandlerPc(currentPc);
    }

    public void endTryCatch() {
        CodeAttribute.ExceptionValue value = exceptions.get(exceptions.size() - 1);
        value.setEndPc(currentPc);
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public CodeAttribute end() {
        return new CodeAttribute(index, maxStack, maxLocals, new byte[]{1, 1, 1, 1}, exceptions.toArray(new CodeAttribute.ExceptionValue[0]), attributes);
    }
}
