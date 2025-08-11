package com.github.puzzle.cc.writer.attribute;

import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.attributes.CodeAttribute;
import com.github.puzzle.cc.parsing.bytecode.BytecodeContainer;
import com.github.puzzle.cc.parsing.bytecode.Opcodes;
import com.github.puzzle.cc.parsing.bytecode.opcodes.Instruction;
import com.github.puzzle.cc.parsing.constants.UTF8CONSTANT;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.writer.bytecode.BytecodeWriter;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodeAttributeBuilder {
    private final int maxStack;
    private final int maxLocals;
    private int index;
    ConstantPool pool;

    List<CodeAttribute.ExceptionValue> exceptions = new ArrayList<>();
    int currentPc;
    private Attributes attributes = new Attributes();
    private BytecodeContainer container;

    public CodeAttributeBuilder(ConstantPool pool, int maxStack, int maxLocals) {
        this.maxStack = maxStack;
        this.maxLocals = maxLocals;

        this.pool = pool;
        index = pool.findUTF8("Code");
        if (index == -1) index = pool.push(new UTF8CONSTANT("Code"));
        this.container = new BytecodeContainer();
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
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        DataOutputStream stream = new DataOutputStream(arrayOutputStream);
        try {
            this.container.write(stream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        byte[] bytes = arrayOutputStream.toByteArray();
        DataInputStream dataInputStream = new DataInputStream(
                new ByteArrayInputStream(bytes)
        );
        short[] code = new short[bytes.length];
        for (int i = 0; i < bytes.length; i++) {
            try {
                code[i] = (short) dataInputStream.readUnsignedByte();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        return new CodeAttribute(pool, index, maxStack, maxLocals, bytes.length, code, exceptions.toArray(new CodeAttribute.ExceptionValue[0]), attributes);
    }

    public void write(BytecodeWriter writer) {
        this.container = writer.getContainer();
    }
}
