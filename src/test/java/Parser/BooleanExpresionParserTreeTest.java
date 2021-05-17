package Parser;

import LexicalAnalyzer.LexicalAnalyser;
import LexicalAnalyzer.Token;
import exceptions.WrongTokenExeption;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class BooleanExpresionParserTreeTest {

    @Test
    public void functionDefinition() throws WrongTokenExeption {
        // given
        String programCode = "def function() {turtle}";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Assert.assertEquals(functions.get(0).getName(), "function");
        Assert.assertTrue(functions.get(0).getArguments().isEmpty());
        Instruction i = (Instruction) functions.get(0).getInsides().get(0);
        Assert.assertEquals(i.getMemberAcess().getMembers().get(0).getName(), "turtle");
    }
}
