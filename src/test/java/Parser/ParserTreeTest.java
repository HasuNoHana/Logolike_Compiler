package Parser;

import LexicalAnalyzer.LexicalAnalyser;
import LexicalAnalyzer.Token;
import LexicalAnalyzer.TokenType;
import Parser.Expresion.Expresion;
import Parser.Expresion.Number;
import Parser.Expresion.PrimaryExpresionEnum;
import Parser.Expresion.SimplePoint;
import exceptions.WrongTokenExeption;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ParserTreeTest {

    @Test
    public void functionDefinition() throws WrongTokenExeption {
        // given
        String programCode = "def int function() {turtle;}";
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
    public void functionDefinitionwithArguments() throws WrongTokenExeption {
        // given
        String programCode = "def int function(String s, int i) { turtle; }";
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
    public void functionDefinitionwithInsides() throws WrongTokenExeption {
        // given
        String programCode = "def int function() {turtleRed.c().ab(1); }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Function function1 = functions.get(0);
        ProgramFragments programFragments = function1.getInsides().get(0);

        Assert.assertEquals(function1.getName(), "function");
        Assert.assertEquals(programFragments.getType(), ProgramFragmentsType.MEMBERACESS);

        Instruction i = (Instruction) programFragments;
        Assert.assertEquals(i.getMemberAcess().getMembers().get(0).getName(), "turtleRed");

        Assert.assertEquals(i.getMemberAcess().getMembers().get(1).getName(), "c");
        Assert.assertTrue(i.getMemberAcess().getMembers().get(1).getExpresions().isEmpty());

        Assert.assertEquals(i.getMemberAcess().getMembers().get(2).getName(), "ab");
        Number n = (Number) i.getMemberAcess().getMembers().get(2).getExpresions().get(0).getLeft().getLeft();
        Assert.assertEquals(1, n.getNumber());
    }

    @Test
    public void functionDefinitionwithInsidesParameters() throws WrongTokenExeption {
        // given
        String programCode = "def int function() {turtleRed.c(1+2); }";
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
    public void functionDefinitionwithInsidesParametersMultiply() throws WrongTokenExeption{
        // given
        String programCode = "def int function() {turtleRed.c(1+2*3); }";
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

    @Test
    public void functionDefinitionwithInsidesParametersBrackets() throws WrongTokenExeption {
        // given
        String programCode = "def int function() {turtleRed.c((1+2)*3); }";
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

        Brackets p1 = (Brackets) e.getLeft().getLeft();
        Assert.assertEquals(PrimaryExpresionEnum.BRACKETS, p1.getPrimaryExpresionEnum());
        Number n = (Number) p1.getExpresion().getLeft().getLeft();
        Assert.assertEquals(1, n.getNumber());
    }

    @Test
    public void functionDefinitionwithInsidesManyParameters() throws WrongTokenExeption {
        // given
        String programCode = "def int function() {turtleRed([1,1], ala); }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Function function1 = functions.get(0);
        ProgramFragments programFragments = function1.getInsides().get(0);
        Instruction i = (Instruction) programFragments;

        Assert.assertEquals(i.getMemberAcess().getMembers().get(0).getName(), "turtleRed");
        ArrayList<Member> members = i.getMemberAcess().getMembers();
        Expresion e = members.get(0).getExpresions().get(0);
        SimplePoint p = (SimplePoint) e.getLeft().getLeft();

        Assert.assertEquals(PrimaryExpresionEnum.POINT, p.getPrimaryExpresionEnum());
        Number n = (Number)p.getX().getLeft().getLeft();
        Number n2 = (Number)p.getY().getLeft().getLeft();
        Assert.assertEquals(1, n.getNumber());
        Assert.assertEquals(1, n2.getNumber());

        Expresion e2 = members.get(0).getExpresions().get(1);
        MemberAcess memberAcess = (MemberAcess) e2.getLeft().getLeft();
        Assert.assertEquals("ala", memberAcess.getMembers().get(0).getName());

    }

    @Test
    public void functionDefinitionwithInsidesManyNumericalParameters() throws WrongTokenExeption {
        // given
        String programCode = "def int function() {turtleRed(1, 2); }";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Function function1 = functions.get(0);
        ProgramFragments programFragments = function1.getInsides().get(0);
        Instruction i = (Instruction) programFragments;

        Assert.assertEquals(i.getMemberAcess().getMembers().get(0).getName(), "turtleRed");
        ArrayList<Member> members = i.getMemberAcess().getMembers();
        Expresion e = members.get(0).getExpresions().get(0);
        Number p = (Number) e.getLeft().getLeft();

        Assert.assertEquals(1, p.getNumber());

        Expresion e2 = members.get(0).getExpresions().get(1);
        Number p2 = (Number) e2.getLeft().getLeft();

        Assert.assertEquals(2, p2.getNumber());

    }

}
