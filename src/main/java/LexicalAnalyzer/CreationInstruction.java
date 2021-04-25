package LexicalAnalyzer;

public class CreationInstruction extends Instruction {


    public CreationInstruction() {
        super(InstructionType.CREATION);
    }

    public InstructionType getInstructionType() {
        return this.instructionType;
    }
}
