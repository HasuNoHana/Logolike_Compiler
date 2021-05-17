package Parser;

import LexicalAnalyzer.LexicalAnalyser;
import LexicalAnalyzer.Token;
import LexicalAnalyzer.TokenType;
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

    @Test
    public void functionDefinitionwithInsidesParameters() throws WrongTokenExeption, functionDefinedUncorrectly, MemberAcessExeption, ExpresionExeption {
        // given
        String programCode = "def function() {turtleRed.c(1+2) }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Function function1 = functions.get(0);
        ProgramFragments programFragments = function1.getInsides().get(0);

        Instruction i = (Instruction) programFragments;

        Assert.assertEquals(i.getMemberAcess().getMembers().get(1).getName(), "c");
        Expresion e = i.getMemberAcess().getMembers().get(1).getExpresions().get(0);
        Number n1 = (Number) e.getLeft().getLeft();
        Number n2 = (Number) e.getRight().getLeft().getLeft();
        Assert.assertEquals(1, n1.getNumber());
        Assert.assertEquals(TokenType.PLUS, e.getOperand());
        Assert.assertEquals(2, n2.getNumber());
    }

    @Test
    public void functionDefinitionwithInsidesParametersMultiply() throws WrongTokenExeption, functionDefinedUncorrectly, MemberAcessExeption, ExpresionExeption {
        // given
        String programCode = "def function() {turtleRed.c(1+2*3) }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Function function1 = functions.get(0);
        ProgramFragments programFragments = function1.getInsides().get(0);

        Instruction i = (Instruction) programFragments;

        Assert.assertEquals(i.getMemberAcess().getMembers().get(1).getName(), "c");
        Expresion e = i.getMemberAcess().getMembers().get(1).getExpresions().get(0);

        Number n1 = (Number) e.getLeft().getLeft();
        Number n2 = (Number) e.getRight().getLeft().getLeft();
        Number n3 = (Number) e.getRight().getLeft().getRight().getLeft();
        Assert.assertEquals(1, n1.getNumber());
        Assert.assertEquals(TokenType.PLUS, e.getOperand());
        Assert.assertEquals(2, n2.getNumber());
        Assert.assertEquals(TokenType.MULTIPLY, e.getRight().getLeft().getOperand());
        Assert.assertEquals(3, n3.getNumber());
    }

}
