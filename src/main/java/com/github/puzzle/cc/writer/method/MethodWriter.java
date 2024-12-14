package com.github.puzzle.cc.writer.method;

import com.github.puzzle.cc.access.AccessFlag;
import com.github.puzzle.cc.parsing.attributes.AttributeInfo;
import com.github.puzzle.cc.parsing.attributes.CodeAttribute;
import com.github.puzzle.cc.parsing.constants.UTF8CONSTANT;
import com.github.puzzle.cc.parsing.containers.Attributes;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.parsing.methods.MethodInfo;
import com.github.puzzle.cc.writer.ClassWriter;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MethodWriter {

    private String name;
    private String descriptor;
    private List<AccessFlag> flags = new ArrayList<>();
    private Attributes attributes = new Attributes();

    public MethodWriter(ClassWriter writer, MethodInfo info) {
        descriptorIndex = info.descriptorIndex;

        nameIndex = info.nameIndex;
        name = ((UTF8CONSTANT)writer.constantPool.get(info.nameIndex)).asString();
        descriptor = ((UTF8CONSTANT)writer.constantPool.get(info.descriptorIndex)).asString();
        flags.addAll(List.of(info.accessFlags));
        attributes = info.attributes;
    }

    public MethodWriter(String name, String descriptor) {
        this.name = name;
        this.descriptor = descriptor;
    }

    public MethodWriter(String name, Class<?> type, Class<?>[] argTypes) {
        this.name = name;
        this.descriptor =  "(";
        for (Class<?> argType : argTypes) descriptor += argType.descriptorString();
        descriptor += ")" + type.descriptorString();
    }

    public void addAccessFlag(AccessFlag flag) {
        this.flags.add(flag);
    }

    public void addAccessFlag(AccessFlag... flagz) {
        this.flags.addAll(Arrays.asList(flagz));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        nameIndex = - 1;
        this.name = name;
    }

    public Attributes getAttributes() {
        return attributes;
    }

    public int addAttribute(AttributeInfo info) {
        return attributes.push(info);
    }

    public List<AccessFlag> getFlags() {
        return flags;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public void setDescriptor(String descriptor) {
        descriptorIndex = - 1;
        this.descriptor = descriptor;
    }

    int nameIndex = -1;
    int descriptorIndex = -1;

    public void setup(ClassWriter writer) {
        if (nameIndex == -1) nameIndex = writer.pushConstant(new UTF8CONSTANT(name));
        if (descriptorIndex == -1) descriptorIndex = writer.pushConstant(new UTF8CONSTANT(descriptor));
    }

    public void writeToStream(DataOutputStream outputStream) throws IOException {
        int accFlags = 0;
        for (AccessFlag flag : flags) accFlags |= flag.getMask();

        outputStream.writeShort(accFlags);
        outputStream.writeShort(nameIndex);
        outputStream.writeShort(descriptorIndex);
        attributes.writeToStream(outputStream);
    }

}
