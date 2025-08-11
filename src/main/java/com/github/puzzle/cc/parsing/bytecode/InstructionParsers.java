package com.github.puzzle.cc.parsing.bytecode;

import com.github.puzzle.cc.parsing.bytecode.opcodes.*;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.LookupSwitchInstruction;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.TableSwitchInstruction;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.WideAddressInstruction;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.WideInstruction;
import com.github.puzzle.cc.parsing.constants.*;
import com.github.puzzle.cc.parsing.containers.ConstantPool;
import com.github.puzzle.cc.util.Stringify;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;

public class InstructionParsers {

    public static final Map<Integer, Class<? extends Instruction>> instructionParsers = new HashMap<>();
    public static final Map<Integer, BiFunction<Instruction, ConstantPool, String>> instructionStringers = new HashMap<>();

    public static void register(int opcode, Class<? extends Instruction> clazz) {
        if (clazz.getTypeName().equals(SingleValueInstruction.class.getName())) {
            instructionStringers.put(opcode, (i, c) -> "{ opcode: \"" + i.getOpcodeStr() + "\", value: #" + ((SingleValueInstruction)i).getValue() + " }");
        } else {
            instructionStringers.put(opcode, (i, c) -> "{ opcode: \"" + i.getOpcodeStr() + "\" }");
        }
        instructionParsers.put(opcode, clazz);
    }

    public static <T extends Instruction> void register(int opcode, Class<T> clazz, BiFunction<T, ConstantPool, String> strFunc) {
        instructionParsers.put(opcode, clazz);
        instructionStringers.put(opcode, (BiFunction<Instruction, ConstantPool, String>) strFunc);
    }

    static {
        register(Opcodes.AALOAD, NoValueInstruction.class);
        register(Opcodes.AASTORE, NoValueInstruction.class);
        register(Opcodes.ACONST_NULL, NoValueInstruction.class);
        register(Opcodes.ALOAD, SingleValueInstruction.class);
        register(Opcodes.ALOAD_0, NoValueInstruction.class);
        register(Opcodes.ALOAD_1, NoValueInstruction.class);
        register(Opcodes.ALOAD_2, NoValueInstruction.class);
        register(Opcodes.ALOAD_3, NoValueInstruction.class);
        register(Opcodes.ANEWARRAY, AddressInstruction.class, (i, c) -> {
            ClassConstant classConstant = (ClassConstant) c.get(i.getAddress());
            String clazz = classConstant.getName(c);

            return String.format(
                    "{ opcode: \"%s\", type: \"%s\" }",
                    i.getOpcodeStr(), clazz
            );
        });
        register(Opcodes.ARETURN, NoValueInstruction.class);
        register(Opcodes.ARRAYLENGTH, NoValueInstruction.class);
        register(Opcodes.ASTORE, SingleValueInstruction.class);
        register(Opcodes.ASTORE_0, NoValueInstruction.class);
        register(Opcodes.ASTORE_1, NoValueInstruction.class);
        register(Opcodes.ASTORE_2, NoValueInstruction.class);
        register(Opcodes.ASTORE_3, NoValueInstruction.class);
        register(Opcodes.ATHROW, NoValueInstruction.class);
        register(Opcodes.BALOAD, NoValueInstruction.class);
        register(Opcodes.BASTORE, NoValueInstruction.class);
        register(Opcodes.BIPUSH, SingleValueInstruction.class);
        register(Opcodes.CALOAD, NoValueInstruction.class);
        register(Opcodes.CASTORE, NoValueInstruction.class);
        register(Opcodes.CHECKCAST, AddressInstruction.class);
        register(Opcodes.D2F, NoValueInstruction.class);
        register(Opcodes.D2I, NoValueInstruction.class);
        register(Opcodes.D2L, NoValueInstruction.class);
        register(Opcodes.DADD, NoValueInstruction.class);
        register(Opcodes.DALOAD, NoValueInstruction.class);
        register(Opcodes.DASTORE, NoValueInstruction.class);
        register(Opcodes.DCMPG, NoValueInstruction.class);
        register(Opcodes.DCMPL, NoValueInstruction.class);
        register(Opcodes.DCONST_0, NoValueInstruction.class);
        register(Opcodes.DCONST_1, NoValueInstruction.class);
        register(Opcodes.DDIV, NoValueInstruction.class);
        register(Opcodes.DLOAD, SingleValueInstruction.class);
        register(Opcodes.DLOAD_0, NoValueInstruction.class);
        register(Opcodes.DLOAD_1, NoValueInstruction.class);
        register(Opcodes.DLOAD_2, NoValueInstruction.class);
        register(Opcodes.DLOAD_3, NoValueInstruction.class);
        register(Opcodes.DMUL, NoValueInstruction.class);
        register(Opcodes.DNEG, NoValueInstruction.class);
        register(Opcodes.DREM, NoValueInstruction.class);
        register(Opcodes.DRETURN, NoValueInstruction.class);
        register(Opcodes.DSTORE, SingleValueInstruction.class);
        register(Opcodes.DSTORE_0, NoValueInstruction.class);
        register(Opcodes.DSTORE_1, NoValueInstruction.class);
        register(Opcodes.DSTORE_2, NoValueInstruction.class);
        register(Opcodes.DSTORE_3, NoValueInstruction.class);
        register(Opcodes.DSUB, NoValueInstruction.class);
        register(Opcodes.DUP, NoValueInstruction.class);
        register(Opcodes.DUP_X1, NoValueInstruction.class);
        register(Opcodes.DUP_X2, NoValueInstruction.class);
        register(Opcodes.DUP2, NoValueInstruction.class);
        register(Opcodes.DUP2_X1, NoValueInstruction.class);
        register(Opcodes.DUP2_X2, NoValueInstruction.class);
        register(Opcodes.F2D, NoValueInstruction.class);
        register(Opcodes.F2I, NoValueInstruction.class);
        register(Opcodes.F2L, NoValueInstruction.class);
        register(Opcodes.FADD, NoValueInstruction.class);
        register(Opcodes.FALOAD, NoValueInstruction.class);
        register(Opcodes.FASTORE, NoValueInstruction.class);
        register(Opcodes.FCMPG, NoValueInstruction.class);
        register(Opcodes.FCMPL, NoValueInstruction.class);
        register(Opcodes.FCONST_0, NoValueInstruction.class);
        register(Opcodes.FCONST_1, NoValueInstruction.class);
        register(Opcodes.FCONST_2, NoValueInstruction.class);
        register(Opcodes.FDIV, NoValueInstruction.class);
        register(Opcodes.FLOAD, SingleValueInstruction.class);
        register(Opcodes.FLOAD_0, NoValueInstruction.class);
        register(Opcodes.FLOAD_1, NoValueInstruction.class);
        register(Opcodes.FLOAD_2, NoValueInstruction.class);
        register(Opcodes.FLOAD_3, NoValueInstruction.class);
        register(Opcodes.FMUL, NoValueInstruction.class);
        register(Opcodes.FNEG, NoValueInstruction.class);
        register(Opcodes.FREM, NoValueInstruction.class);
        register(Opcodes.FRETURN, NoValueInstruction.class);
        register(Opcodes.FSTORE, SingleValueInstruction.class);
        register(Opcodes.FSTORE_0, NoValueInstruction.class);
        register(Opcodes.FSTORE_1, NoValueInstruction.class);
        register(Opcodes.FSTORE_2, NoValueInstruction.class);
        register(Opcodes.FSTORE_3, NoValueInstruction.class);
        register(Opcodes.FSUB, NoValueInstruction.class);
        register(Opcodes.GETFIELD, AddressInstruction.class, (i, c) -> {
            FieldRefConstant fieldRefConstant = (FieldRefConstant) c.get(i.getAddress());
            NameAndTypeConstant nameAndTypeConstant = fieldRefConstant.getNameAndType(c);
            String clazz = fieldRefConstant.getClass(c).getName(c);
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", class: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), clazz, name, desc
            );
        });
        register(Opcodes.GETSTATIC, AddressInstruction.class, (i, c) -> {
            FieldRefConstant fieldRefConstant = (FieldRefConstant) c.get(i.getAddress());
            NameAndTypeConstant nameAndTypeConstant = fieldRefConstant.getNameAndType(c);
            String clazz = fieldRefConstant.getClass(c).getName(c);
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", class: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), clazz, name, desc
            );
        });
        register(Opcodes.GOTO, AddressInstruction.class);
        register(Opcodes.GOTO_W, WideAddressInstruction.class);
        register(Opcodes.I2B, NoValueInstruction.class);
        register(Opcodes.I2C, NoValueInstruction.class);
        register(Opcodes.I2D, NoValueInstruction.class);
        register(Opcodes.I2F, NoValueInstruction.class);
        register(Opcodes.I2L, NoValueInstruction.class);
        register(Opcodes.I2S, NoValueInstruction.class);
        register(Opcodes.IADD, NoValueInstruction.class);
        register(Opcodes.IALOAD, NoValueInstruction.class);
        register(Opcodes.IAND, NoValueInstruction.class);
        register(Opcodes.IASTORE, NoValueInstruction.class);
        register(Opcodes.ICONST_M1, NoValueInstruction.class);
        register(Opcodes.ICONST_0, NoValueInstruction.class);
        register(Opcodes.ICONST_1, NoValueInstruction.class);
        register(Opcodes.ICONST_2, NoValueInstruction.class);
        register(Opcodes.ICONST_3, NoValueInstruction.class);
        register(Opcodes.ICONST_4, NoValueInstruction.class);
        register(Opcodes.ICONST_5, NoValueInstruction.class);
        register(Opcodes.IDIV, NoValueInstruction.class);
        register(Opcodes.IF_ACMPEQ, AddressInstruction.class);
        register(Opcodes.IF_ACMPNE, AddressInstruction.class);
        register(Opcodes.IF_ICMPEQ, AddressInstruction.class);
        register(Opcodes.IF_ICMPNE, AddressInstruction.class);
        register(Opcodes.IF_ICMPLT, AddressInstruction.class);
        register(Opcodes.IF_ICMPGE, AddressInstruction.class);
        register(Opcodes.IF_ICMPGT, AddressInstruction.class);
        register(Opcodes.IF_ICMPLE, AddressInstruction.class);
        register(Opcodes.IFEQ, AddressInstruction.class);
        register(Opcodes.IFNE, AddressInstruction.class);
        register(Opcodes.IFLT, AddressInstruction.class);
        register(Opcodes.IFGE, AddressInstruction.class);
        register(Opcodes.IFGT, AddressInstruction.class);
        register(Opcodes.IFLE, AddressInstruction.class);
        register(Opcodes.IFNONNULL, AddressInstruction.class);
        register(Opcodes.IFNULL, AddressInstruction.class);
        register(Opcodes.IINC, DualValueInstruction.class);
        register(Opcodes.ILOAD, SingleValueInstruction.class);
        register(Opcodes.ILOAD_0, NoValueInstruction.class);
        register(Opcodes.ILOAD_1, NoValueInstruction.class);
        register(Opcodes.ILOAD_2, NoValueInstruction.class);
        register(Opcodes.ILOAD_3, NoValueInstruction.class);
        register(Opcodes.IMUL, NoValueInstruction.class);
        register(Opcodes.INEG, NoValueInstruction.class);
        register(Opcodes.INSTANCE_OF, AddressInstruction.class);
        register(Opcodes.INVOKEDYNAMIC, InvokeDynamicInstruction.class, (i, c) -> {
            GenericConstant constant = c.get(i.getAddress());
            NameAndTypeConstant nameAndTypeConstant = null;

            switch (constant.getTag()) {
                case CONSTANT_INVOKE_DYNAMIC:
                    InvokeDynamicConstant invokeDynamicConstant = (InvokeDynamicConstant) constant;
                    nameAndTypeConstant = invokeDynamicConstant.getNameAndType(c);
                    break;
                case CONSTANT_DYNAMIC:
                    DynamicConstant dynamicConstant = (DynamicConstant) constant;
                    nameAndTypeConstant = dynamicConstant.getNameAndType(c);
                    break;
            }
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), name, desc
            );
        });
        register(Opcodes.INVOKEINTERFACE, InvokeInterfaceInstruction.class, (i, c) -> {
            InterfaceMethodRefConstant interfaceMethodRefConstant = (InterfaceMethodRefConstant) c.get(i.getAddress());
            String clazz = interfaceMethodRefConstant.getClass(c).getName(c);
            NameAndTypeConstant nameAndTypeConstant = interfaceMethodRefConstant.getNameAndType(c);
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", clazz: \"%s\", name: \"%s\", descriptor: \"%s\", argCount: #%d }",
                    i.getOpcodeStr(), clazz, name, desc, i.getCount()
            );
        });
        register(Opcodes.INVOKESPECIAL, AddressInstruction.class, (i, c) -> {
            GenericConstant constant = c.get(i.getAddress());
            NameAndTypeConstant nameAndTypeConstant = null;
            String clazz = null;

            switch (constant.getTag()) {
                case CONSTANT_METHOD_REF:
                    MethodRefConstant refConstant = (MethodRefConstant) constant;
                    clazz = refConstant.getClass(c).getName(c);
                    nameAndTypeConstant = refConstant.getNameAndType(c);
                    break;
                case CONSTANT_INTERFACE_METHOD_REF:
                    InterfaceMethodRefConstant interfaceRefConstant = (InterfaceMethodRefConstant) constant;
                    clazz = interfaceRefConstant.getClass(c).getName(c);
                    nameAndTypeConstant = interfaceRefConstant.getNameAndType(c);
                    break;
            }
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", clazz: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), clazz, name, desc
            );
        });
        register(Opcodes.INVOKESTATIC, AddressInstruction.class, (i, c) -> {
            GenericConstant constant = c.get(i.getAddress());
            NameAndTypeConstant nameAndTypeConstant = null;
            String clazz = null;

            switch (constant.getTag()) {
                case CONSTANT_METHOD_REF:
                    MethodRefConstant refConstant = (MethodRefConstant) constant;
                    clazz = refConstant.getClass(c).getName(c);
                    nameAndTypeConstant = refConstant.getNameAndType(c);
                    break;
                case CONSTANT_INTERFACE_METHOD_REF:
                    InterfaceMethodRefConstant interfaceRefConstant = (InterfaceMethodRefConstant) constant;
                    clazz = interfaceRefConstant.getClass(c).getName(c);
                    nameAndTypeConstant = interfaceRefConstant.getNameAndType(c);
                    break;
            }
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", clazz: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), clazz, name, desc
            );
        });
        register(Opcodes.INVOKEVIRTUAL, AddressInstruction.class, (i, c) -> {
            MethodRefConstant refConstant = (MethodRefConstant) c.get(i.getAddress());
            String clazz = refConstant.getClass(c).getName(c);

            NameAndTypeConstant nameAndTypeConstant = refConstant.getNameAndType(c);
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", clazz: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), clazz, name, desc
            );
        });
        register(Opcodes.IOR, NoValueInstruction.class);
        register(Opcodes.IREM, NoValueInstruction.class);
        register(Opcodes.IRETURN, NoValueInstruction.class);
        register(Opcodes.ISHL, NoValueInstruction.class);
        register(Opcodes.ISHR, NoValueInstruction.class);
        register(Opcodes.ISTORE, SingleValueInstruction.class);
        register(Opcodes.ISTORE_0, NoValueInstruction.class);
        register(Opcodes.ISTORE_1, NoValueInstruction.class);
        register(Opcodes.ISTORE_2, NoValueInstruction.class);
        register(Opcodes.ISTORE_3, NoValueInstruction.class);
        register(Opcodes.ISUB, NoValueInstruction.class);
        register(Opcodes.IUSHR, NoValueInstruction.class);
        register(Opcodes.IXOR, NoValueInstruction.class);
        register(Opcodes.JSR, AddressInstruction.class);
        register(Opcodes.JSR_W, WideAddressInstruction.class);
        register(Opcodes.L2D, NoValueInstruction.class);
        register(Opcodes.L2F, NoValueInstruction.class);
        register(Opcodes.L2I, NoValueInstruction.class);
        register(Opcodes.LADD, NoValueInstruction.class);
        register(Opcodes.LALOAD, NoValueInstruction.class);
        register(Opcodes.LAND, NoValueInstruction.class);
        register(Opcodes.LASTORE, NoValueInstruction.class);
        register(Opcodes.LCMP, NoValueInstruction.class);
        register(Opcodes.LCONST_0, NoValueInstruction.class);
        register(Opcodes.LCONST_1, NoValueInstruction.class);
        register(Opcodes.LDC, SingleValueInstruction.class, (i, c) -> {
            GenericConstant refConstant = c.get(i.getValue());
            String value = Stringify.getValue(refConstant, c);
            String formatter = Stringify.getFormatString(refConstant);
            boolean isNum = Stringify.isNum(refConstant);

            return String.format(
                    "{ opcode: \"%s\", value: " + (isNum ? "#" + formatter : "\"" + formatter + "\"") + " }",
                    i.getOpcodeStr(), value
            );
        });
        register(Opcodes.LDC_W, AddressInstruction.class, (i, c) -> {
            GenericConstant refConstant = c.get(i.getAddress());
            String value = Stringify.getValue(refConstant, c);
            String formatter = Stringify.getFormatString(refConstant);
            boolean isNum = Stringify.isNum(refConstant);

            return String.format(
                    "{ opcode: \"%s\", value: " + (isNum ? "#" + formatter : "\"" + formatter + "\"") + " }",
                    i.getOpcodeStr(), value
            );
        });
        register(Opcodes.LDC2_W, AddressInstruction.class, (i, c) -> {
            GenericConstant refConstant = c.get(i.getAddress());
            String value = Stringify.getValue(refConstant, c);
            String formatter = Stringify.getFormatString(refConstant);
            boolean isNum = Stringify.isNum(refConstant);

            return String.format(
                    "{ opcode: \"%s\", value: " + (isNum ? "#" + formatter : "\"" + formatter + "\"") + " }",
                    i.getOpcodeStr(), value
            );
        });
        register(Opcodes.LDIV, NoValueInstruction.class);
        register(Opcodes.LLOAD, SingleValueInstruction.class);
        register(Opcodes.LLOAD_0, NoValueInstruction.class);
        register(Opcodes.LLOAD_1, NoValueInstruction.class);
        register(Opcodes.LLOAD_2, NoValueInstruction.class);
        register(Opcodes.LLOAD_3, NoValueInstruction.class);
        register(Opcodes.LMUL, NoValueInstruction.class);
        register(Opcodes.LNEG, NoValueInstruction.class);
        register(Opcodes.LOOKUPSWITCH, LookupSwitchInstruction.class, (i, c) -> {
            int[] keys = i.getKeys();
            int[] values = i.getValues();
            StringBuilder builder = new StringBuilder("[ ");
            for (int j = 0; j < keys.length; j++) {
                builder.append(String.format("{ match: %d, offsets: %d }", keys[j], values[j]));
                if (j != keys.length - 1) builder.append(", ");
            }

            return String.format(
                    "{ opcode: \"%s\", padding: #%d, default-case-offset: #%d, caseCount: #%d, cases: %s }",
                    i.getOpcodeStr(), i.getPadding(), i.getDefaultCaseOffset(), i.getCaseCount(), builder.append(" ]")
            );
        });
        register(Opcodes.LOR, NoValueInstruction.class);
        register(Opcodes.LREM, NoValueInstruction.class);
        register(Opcodes.LRETURN, NoValueInstruction.class);
        register(Opcodes.LSHL, NoValueInstruction.class);
        register(Opcodes.LSHR, NoValueInstruction.class);
        register(Opcodes.LSTORE, SingleValueInstruction.class);
        register(Opcodes.LSTORE_0, NoValueInstruction.class);
        register(Opcodes.LSTORE_1, NoValueInstruction.class);
        register(Opcodes.LSTORE_2, NoValueInstruction.class);
        register(Opcodes.LSTORE_3, NoValueInstruction.class);
        register(Opcodes.LSUB, NoValueInstruction.class);
        register(Opcodes.LUSHR, NoValueInstruction.class);
        register(Opcodes.LXOR, NoValueInstruction.class);
        register(Opcodes.MONITORENTER, NoValueInstruction.class);
        register(Opcodes.MONITOREXIT, NoValueInstruction.class);
        register(Opcodes.MULTIANEWARRAY, MultiANewArrayInstruction.class, (i, c) -> {
            ClassConstant classConstant = (ClassConstant) c.get(i.getAddress());
            String clazz = classConstant.getName(c);

            return String.format(
                    "{ opcode: \"%s\", type: \"%s\", dimensions: #%d }",
                    i.getOpcodeStr(), clazz, i.getDimensions()
            );
        });
        register(Opcodes.NEW, AddressInstruction.class, (i, c) -> {
            ClassConstant classConstant = (ClassConstant) c.get(i.getAddress());
            String clazz = classConstant.getName(c);

            return String.format(
                    "{ opcode: \"%s\", type: \"%s\" }",
                    i.getOpcodeStr(), clazz
            );
        });
        register(Opcodes.NEWARRAY, SingleValueInstruction.class, (i, c) -> {
            String type = Stringify.atypeToDescriptorString(i.getValue());

            return String.format(
                    "{ opcode: \"%s\", type: \"%s\" }",
                    i.getOpcodeStr(), type
            );
        });
        register(Opcodes.NOP, NoValueInstruction.class);
        register(Opcodes.POP, NoValueInstruction.class);
        register(Opcodes.POP2, NoValueInstruction.class);
        register(Opcodes.PUTFIELD, AddressInstruction.class, (i, c) -> {
            FieldRefConstant fieldRefConstant = (FieldRefConstant) c.get(i.getAddress());
            NameAndTypeConstant nameAndTypeConstant = fieldRefConstant.getNameAndType(c);
            String clazz = fieldRefConstant.getClass(c).getName(c);
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", class: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), clazz, name, desc
            );
        });
        register(Opcodes.PUTSTATIC, AddressInstruction.class, (i, c) -> {
            FieldRefConstant fieldRefConstant = (FieldRefConstant) c.get(i.getAddress());
            NameAndTypeConstant nameAndTypeConstant = fieldRefConstant.getNameAndType(c);
            String clazz = fieldRefConstant.getClass(c).getName(c);
            String name = nameAndTypeConstant.getName(c);
            String desc = nameAndTypeConstant.getDescriptor(c);

            return String.format(
                    "{ opcode: \"%s\", class: \"%s\", name: \"%s\", descriptor: \"%s\" }",
                    i.getOpcodeStr(), clazz, name, desc
            );
        });
        register(Opcodes.RET, SingleValueInstruction.class, (i, c) -> {
            int localVarIdx = i.getValue();

            return String.format(
                    "{ opcode: \"%s\", local-var-idx: \"%d\" }",
                    i.getOpcodeStr(), localVarIdx
            );
        });
        register(Opcodes.RETURN, NoValueInstruction.class);
        register(Opcodes.SALOAD, NoValueInstruction.class);
        register(Opcodes.SASTORE, NoValueInstruction.class);
        register(Opcodes.SIPUSH, AddressInstruction.class, (i, c) -> {
            int address = i.getAddress();

            return String.format(
                    "{ opcode: \"%s\", value: \"%d\" }",
                    i.getOpcodeStr(), address
            );
        });
        register(Opcodes.SWAP, NoValueInstruction.class);
        register(Opcodes.TABLESWITCH, TableSwitchInstruction.class, (i, c) -> {
            int[] offsets = i.getOffsets();
            StringBuilder builder = new StringBuilder("[ ");
            for (int j = 0; j < offsets.length; j++) {
                builder.append(String.format("{ match: %d, offsets: %d }", j + i.getStart(), offsets[j]));
                if (j != offsets.length - 1) builder.append(", ");
            }

            return String.format(
                    "{ opcode: \"%s\", padding: #%d, default-case-offset: #%d, caseCount: #%d, cases: %s }",
                    i.getOpcodeStr(), i.getPadding(), i.getDefaultCaseOffset(), i.getCaseCount(), builder.append(" ]")
            );
        });
        register(Opcodes.WIDE, WideInstruction.class);
//        register(Opcodes.WIDE, WideInstruction.class, (i, c) -> {
//            return String.format(
//                    "{ opcode: \"%s\", padding: #%d, default-case-offset: #%d, caseCount: #%d, cases: %s }",
//                    i.getOpcodeStr(), i.getPadding(), i.getDefaultCaseOffset(), i.getCaseCount(), builder.append(" ]")
//            );
//        });
    }

}
