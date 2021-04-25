package LexicalAnalyzer;

import exceptions.MissingEndBracketException;
import exceptions.WrongTokenExeption;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

@RunWith(Parameterized.class)
public class LexicalAnalyserTokenType {

    @Parameterized.Parameters(
            name = "{index}: Code: \"{0}\", expected tokens: \"{1}\" "
    )
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"def ", TokenType.DEF},
                {". ", TokenType.DOT},
                {"{ ", TokenType.CURLY_OPEN_BRACKET},
                {"} ", TokenType.CURLY_CLOSED_BRACKET},
                {"[ ", TokenType.SQUARE_OPEN_BRACKET},
                {"] ", TokenType.SQUARE_CLOSED_BRACKET},
                {"for ", TokenType.FOR},
                {"defINI ", TokenType.ID},
                {"de ", TokenType.ID},
                {"True ", TokenType.TRUE},
                {"False ", TokenType.FALSE},
                {"if ", TokenType.IF},
                {"else ", TokenType.ELSE}
        });
    }

    private final String code;

    private final TokenType expectedToken;


    public LexicalAnalyserTokenType(String code, TokenType expectedToken) {
        this.code = code;
        this.expectedToken = expectedToken;
    }

    @Test
    public void test() throws MissingEndBracketException, WrongTokenExeption {
        // given
        LexicalAnalyser analyzer = new LexicalAnalyser(code);

        // when
        Token t = analyzer.findNextToken();

        // then
        Assert.assertEquals(expectedToken, t.getType());
    }

}
