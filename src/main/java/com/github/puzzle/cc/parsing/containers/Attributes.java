package com.github.puzzle.cc.parsing.containers;

import com.github.puzzle.cc.parsing.attributes.AttributeInfo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class Attributes {

    public AttributeInfo[] attributes;

    public Attributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }

    public Attributes(Attributes reop) {
        this.attributes = Arrays.copyOf(reop.attributes, reop.attributes.length);
    }

    public Attributes() {
        this(new AttributeInfo[0]);
    }

    public int push(AttributeInfo attribute) {
        int oldLength = attributes.length;
        if (attributes.length < attributes.length + 1) {
            attributes = Arrays.copyOf(attributes, attributes.length + 1);
            attributes[oldLength] = attribute;
        }
        return oldLength + 1;
    }

    public void writeToStream(DataOutputStream outputStream) throws IOException {
        outputStream.writeShort(attributes.length);
        for (AttributeInfo attribute : attributes) {
            attribute.writeToStream(outputStream);
        }
    }

    public AttributeInfo get(String name) {
        for (AttributeInfo info : attributes) {
            if (info.getType().equals(name)) return info;
        }
        return null;
    }

    public int size() {
        int size = 4 * attributes.length;
        for (AttributeInfo info : attributes) size += info.getLength();
        return size;
    }
}
