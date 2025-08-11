package com.github.puzzle.cc.writer.bytecode;

import com.github.puzzle.cc.parsing.bytecode.BytecodeContainer;
import com.github.puzzle.cc.parsing.bytecode.opcodes.*;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.LookupSwitchInstruction;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.TableSwitchInstruction;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.WideAddressInstruction;
import com.github.puzzle.cc.parsing.bytecode.opcodes.wide.WideInstruction;
import com.github.puzzle.cc.parsing.containers.ConstantPool;

import java.util.ArrayList;
import java.util.List;

public class BytecodeWriter {

    List<Instruction> instructions;
    BytecodeContainer container;

    public BytecodeWriter(List<Instruction> instructions) {
        this.instructions = instructions;
        this.container = new BytecodeContainer(instructions);
    }

    public static BytecodeWriter create() {
        return new BytecodeWriter(new ArrayList<>());
    }

    public static BytecodeWriter create(BytecodeContainer container) {
        return new BytecodeWriter(new ArrayList<>(container.instructions));
    }

    @Override
    public String toString() {
        return container.toString();
    }

    public String asString(ConstantPool pool) {
        return container.asString(pool);
    }

    public void putInstruction(int opcode) {
        NoValueInstruction instruction = new NoValueInstruction();
        instruction.set(opcode);
        instructions.add(instruction);
    }

    public void putInstructionV(int opcode, short value) {
        SingleValueInstruction instruction = new SingleValueInstruction();
        instruction.set(opcode, value);
        instructions.add(instruction);
    }

    public void putInstructionVV(int opcode, short value0, short value1) {
        DualValueInstruction instruction = new DualValueInstruction();
        instruction.set(opcode, value0, value1);
        instructions.add(instruction);
    }

    public void putInstructionA(int opcode, short address) {
        AddressInstruction instruction = new AddressInstruction();
        instruction.set(opcode, address);
        instructions.add(instruction);
    }

    public void putInstructionAV(int opcode, short address, short value) {
        MultiANewArrayInstruction instruction = new MultiANewArrayInstruction();
        instruction.set(opcode, address, value);
        instructions.add(instruction);
    }

    public void putInstructionARR(int opcode, short address) {
        InvokeDynamicInstruction instruction = new InvokeDynamicInstruction();
        instruction.set(opcode, address);
        instructions.add(instruction);
    }

    public void putInstructionAVR(int opcode, short address, short value) {
        InvokeInterfaceInstruction instruction = new InvokeInterfaceInstruction();
        instruction.set(opcode, address, value);
        instructions.add(instruction);
    }

    public void putInstructionWA(int opcode, int address) {
        WideAddressInstruction instruction = new WideAddressInstruction();
        instruction.set(opcode, address);
        instructions.add(instruction);
    }

    public void putInstructionWideVAA(int opcode, short address0, short address1) {
        WideInstruction instruction = new WideInstruction();
        instruction.set(opcode, address0, address1);
        instructions.add(instruction);
    }

    public void putInstructionLookupSwitch(int defaultOffs, int[] keys, int[] values) {
        LookupSwitchInstruction instruction = new LookupSwitchInstruction();
        instruction.set(defaultOffs, keys, values);
        instructions.add(instruction);
    }

    public void putInstructionTableSwitch(int defaultOffs, int[] offsets, int start, int end) {
        TableSwitchInstruction instruction = new TableSwitchInstruction();
        instruction.set(defaultOffs, end, start, offsets);
        instructions.add(instruction);
    }

    public BytecodeContainer getContainer() {
        return container;
    }
}
