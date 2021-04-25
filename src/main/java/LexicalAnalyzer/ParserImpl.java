package LexicalAnalyzer;

import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser {
    private final List<Instruction> instructions = new ArrayList<Instruction>();

    @Override
    public void parse(String file) {
        instructions.add(new CreationInstruction());
    }

    @Override
    public List<Instruction> getInstructions() {
        return instructions;
    }
}
