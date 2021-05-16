package Parser;

import LexicalAnalyzer.CreationInstruction;
import LexicalAnalyzer.Instruction;
import Parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class ParserImpl implements Parser {
    private final List<Instruction> instructions = new ArrayList<>();

    @Override
    public void parse(String file) {
        instructions.add(new CreationInstruction());
    }

    @Override
    public List<Instruction> getInstructions() {
        return instructions;
    }
}
