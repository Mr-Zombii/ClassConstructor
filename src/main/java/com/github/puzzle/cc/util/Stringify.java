package com.github.puzzle.cc.util;

import com.github.puzzle.cc.parsing.constants.*;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

public class Stringify {

    public static String atypeToString(int atype) {
        switch (atype) {
            case 4: return "T_BOOLEAN";
            case 5: return "T_CHAR";
            case 6: return "T_FLOAT";
            case 7: return "T_DOUBLE";
            case 8: return "T_BYTE";
            case 9: return "T_SHORT";
            case 10: return "T_INT";
            case 11: return "T_LONG";
            default: return null;
        }
    }

    public static String atypeToDescriptorString(int atype) {
        switch (atype) {
            case 4: return "Z";
            case 5: return "C";
            case 6: return "F";
            case 7: return "D";
            case 8: return "B";
            case 9: return "S";
            case 10: return "I";
            case 11: return "J";
            default: return null;
        }
    }

    public static boolean isNum(GenericConstant constant) {
        switch (constant.getTag()) {
            case CONSTANT_UTF8: return false;
            case CONSTANT_INT: return true;
            case CONSTANT_FLOAT: return true;
            case CONSTANT_LONG: return true;
            case CONSTANT_DOUBLE: return true;
            case CONSTANT_CLASS: return false;
            case CONSTANT_STRING: return false;
            default: return false;
        }
    }

    public static String getFormatString(GenericConstant constant) {
        switch (constant.getTag()) {
            case CONSTANT_INT:
            case CONSTANT_LONG:
                return "%d";
            case CONSTANT_FLOAT:
            case CONSTANT_DOUBLE:
                return "%f";
            default: return "%s";
        }
    }

    public static String getValue(GenericConstant constant, ConstantPool pool) {
        switch (constant.getTag()) {
            case CONSTANT_UTF8: return ((UTF8CONSTANT) constant).asString();
            case CONSTANT_STRING: return ((StringConstant) constant).getString(pool);
            case CONSTANT_CLASS: return ((ClassConstant) constant).getName(pool);
            case CONSTANT_INT: return String.valueOf(((IntegerConstant) constant).asInt());
            case CONSTANT_LONG: return String.valueOf(((LongConstant) constant).asLong());
            case CONSTANT_FLOAT: return String.valueOf(((FloatConstant) constant).asFloat());
            case CONSTANT_DOUBLE: return String.valueOf(((DoubleConstant) constant).asDouble());
            default: return "No Formatting for " + constant.getTag().name();
        }
    }

}
