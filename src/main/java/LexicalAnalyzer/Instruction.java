package LexicalAnalyzer;

public abstract class Instruction {
    protected InstructionType instructionType;

    protected Instruction(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }
}
