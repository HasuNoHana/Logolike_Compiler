package LexicalAnalyzer;

import exceptions.MissingEndBracketException;
import exceptions.WrongTokenExeption;
import org.junit.Assert;
import org.junit.Test;

public class LexicalAnalyserSingleTest {

    @Test
    public void shouldContainEOFToken() throws WrongTokenExeption {
        // given
        String programCode = "def";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);

        // when
        Token token1 = analyzer.findNextToken();
        Token token2 = analyzer.findNextToken();

        //then
        Assert.assertEquals(token1.getType(), TokenType.DEF);
        Assert.assertEquals(token2.getType(), TokenType.EOF);


    }

    @Test(expected = WrongTokenExeption.class)
    public void shouldThrowWrongTokenExeption_AND() throws WrongTokenExeption {
        // given
        String programCode = "x & y";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);

        // when
        analyzer.findNextToken();
        analyzer.findNextToken();
    }
    @Test(expected = WrongTokenExeption.class)
    public void shouldThrowWrongTokenExeption_OR() throws WrongTokenExeption {
        // given
        String programCode = "x | y";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);

        // when
        analyzer.findNextToken();
        analyzer.findNextToken();
    }

}
