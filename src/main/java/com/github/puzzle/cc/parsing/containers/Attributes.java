package com.github.puzzle.cc.parsing.containers;

import com.github.puzzle.cc.parsing.attributes.AttributeInfo;

public class Attributes {

    AttributeInfo[] attributes;

    public Attributes(AttributeInfo[] attributes) {
        this.attributes = attributes;
    }

    public AttributeInfo get(String name) {
        for (AttributeInfo info : attributes) {
            if (info.getType().equals(name)) return info;
        }
        return null;
    }

}
