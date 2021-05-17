package Parser;

import LexicalAnalyzer.LexicalAnalyser;
import LexicalAnalyzer.Token;
import Parser.BooleanExpresion.PrimaryBoolean;
import Parser.BooleanExpresion.PrimaryBooleanType;
import Parser.Expresion.Number;
import exceptions.WrongTokenExeption;
import org.junit.Assert;
import org.junit.Test;
import Parser.*;

import java.util.ArrayList;

public class BooleanExpresionParserTreeTest {

    @Test
    public void functionDefinition() throws WrongTokenExeption {
        // given
        String programCode = "def function() {for i=2; i<3; 1{" +
                " turtle; }}";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);
        Parser parser = new Parser();

        // when
        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);

        //then
        Assert.assertEquals(functions.get(0).getName(), "function");
        Assert.assertTrue(functions.get(0).getArguments().isEmpty());
        ForStatment f = (ForStatment) functions.get(0).getInsides().get(0);
        Assert.assertEquals(f.getI(), "i");
        Number n = (Number) f.getiAssign().getLeft().getLeft();
        Assert.assertEquals(n.getNumber(), 2);
        PrimaryBoolean b = (PrimaryBoolean) f.getCondition().getLeft().getLeft().getLeft();
        Assert.assertEquals(b.getPrimaryBooleanType(), PrimaryBooleanType.EXPRESION);
    }
}
