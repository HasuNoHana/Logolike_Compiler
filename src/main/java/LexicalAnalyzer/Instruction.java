package LexicalAnalyzer;

public abstract class Instruction {
    protected final InstructionType instructionType;

    protected Instruction(InstructionType instructionType) {
        this.instructionType = instructionType;
    }

    public InstructionType getInstructionType() {
        return instructionType;
    }
}
