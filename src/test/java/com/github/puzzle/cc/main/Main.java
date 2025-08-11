package com.github.puzzle.cc.main;

import com.github.puzzle.cc.parsing.ClassReader;
import com.github.puzzle.cc.parsing.fields.FieldInfo;
import com.github.puzzle.cc.parsing.methods.MethodInfo;
import com.github.puzzle.cc.util.StreamUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
//        File file = new File("./tests/Calculator.class");
        File file = new File("./tests/HelloWorld.class");
//        File file = new File("./tests/Code.class");
        try {
            FileInputStream stream = new FileInputStream(file);
            ClassReader reader = new ClassReader(StreamUtil.readAllBytes(stream));

            for (MethodInfo method : reader.methods) {
                String name = reader.constantPool.get(method.nameIndex).toString();
                if (Objects.equals(name, "main")) {
                    System.out.println(method.attributes.get("Code"));
                }
            }
//            for (FieldInfo field : reader.fields) {
//                String descriptor = reader.constantPool.get(field.descriptorIndex).toString();
//                if (Objects.equals(descriptor, "I")) {
//
//                }
//            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
