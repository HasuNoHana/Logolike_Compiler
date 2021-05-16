package Parser;

import LexicalAnalyzer.LexicalAnalyser;
import LexicalAnalyzer.Token;
import exceptions.WrongTokenExeption;
import exceptions.functionDefinedUncorrectly;
import exceptions.instructionExeption;
import org.junit.Assert;
import org.junit.Test;
import Parser.Argument;

import java.util.ArrayList;

public class ParserTreeTest {

    @Test
    public void functionDefinition() throws WrongTokenExeption, functionDefinedUncorrectly, instructionExeption {
        // given
        String programCode = "def function() {turtle}";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Assert.assertEquals(functions.get(0).getName(), "function");
    }

    @Test
    public void functionDefinitionwithArguments() throws WrongTokenExeption, functionDefinedUncorrectly, instructionExeption {
        // given
        String programCode = "def function(String s, int i) { turtle }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Assert.assertEquals(functions.get(0).getName(), "function");
        Assert.assertEquals(functions.get(0).getArguments().get(0), new Argument("String", "s"));
        Assert.assertEquals(functions.get(0).getArguments().get(1), new Argument("int", "i"));
    }

    @Test
    public void functionDefinitionwithInsides() throws WrongTokenExeption, functionDefinedUncorrectly, instructionExeption {
        // given
        String programCode = "def function() {turtleRed.c().ab(ala) }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Assert.assertEquals(functions.get(0).getName(), "function");
        Assert.assertEquals(functions.get(0).getInsides().get(0).getType(), "Instruction");
        Instruction i = (Instruction) functions.get(0).getInsides().get(0);
        Assert.assertEquals(i.getMembers().get(0).getName(), "turtleRed");
        Assert.assertEquals(i.getMembers().get(1).getName(), "c");
        Assert.assertTrue(i.getMembers().get(1).getParameters().isEmpty());
        Assert.assertEquals(i.getMembers().get(2).getName(), "ab");
        Assert.assertEquals(i.getMembers().get(2).getParameters().get(0).getName(), "ala");
    }

}
