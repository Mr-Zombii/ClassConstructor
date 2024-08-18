package com.github.puzzle.cc.main;

import com.github.puzzle.cc.parsing.ClassReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        File file = new File("./tests/WatParser.class");
        try {
            FileInputStream stream = new FileInputStream(file);
            ClassReader reader = new ClassReader(stream.readAllBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
