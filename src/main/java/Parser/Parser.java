package Parser;

import LexicalAnalyzer.Instruction;

import java.util.List;

public interface Parser {

    void parse(String file);

    List<Instruction> getInstructions();
}
