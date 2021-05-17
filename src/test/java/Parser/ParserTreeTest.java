package Parser;

import LexicalAnalyzer.LexicalAnalyser;
import LexicalAnalyzer.Token;
import exceptions.ExpresionExeption;
import exceptions.WrongTokenExeption;
import exceptions.functionDefinedUncorrectly;
import exceptions.MemberAcessExeption;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ParserTreeTest {

    @Test
    public void functionDefinition() throws WrongTokenExeption, functionDefinedUncorrectly, MemberAcessExeption, ExpresionExeption {
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

    @Test
    public void functionDefinitionwithArguments() throws WrongTokenExeption, functionDefinedUncorrectly, MemberAcessExeption, ExpresionExeption {
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
    public void functionDefinitionwithInsides() throws WrongTokenExeption, functionDefinedUncorrectly, MemberAcessExeption, ExpresionExeption {
        // given
        String programCode = "def function() {turtleRed.c().ab(1) }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Function function1 = functions.get(0);
        ProgramFragments programFragments = function1.getInsides().get(0);

        Assert.assertEquals(function1.getName(), "function");
        Assert.assertEquals(programFragments.getType(), "Instruction");

        Instruction i = (Instruction) programFragments;
        Assert.assertEquals(i.getMemberAcess().getMembers().get(0).getName(), "turtleRed");

        Assert.assertEquals(i.getMemberAcess().getMembers().get(1).getName(), "c");
        Assert.assertTrue(i.getMemberAcess().getMembers().get(1).getExpresions().isEmpty());

        Assert.assertEquals(i.getMemberAcess().getMembers().get(2).getName(), "ab");
        Number n = (Number) i.getMemberAcess().getMembers().get(2).getExpresions().get(0).getLeft().getLeft();
        Assert.assertEquals(1, n.getNumber());
    }

}
