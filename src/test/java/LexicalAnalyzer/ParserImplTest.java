package LexicalAnalyzer;

import Parser.Parser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class ParserImplTest {

    @Test
    public void shouldCreateTurtle(){
        // given
        Parser analyzer = new ParserImpl();
        String programCode = "turtle = new Turtle(0, 0);";

        // when
        analyzer.parse(programCode);
        List<Instruction> instructionList = analyzer.getInstructions();

        // then
        Assert.assertEquals(InstructionType.CREATION,instructionList.get(0).getInstructionType());
    }


}