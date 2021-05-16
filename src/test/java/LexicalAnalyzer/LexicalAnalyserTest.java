package LexicalAnalyzer;

import exceptions.MissingEndBracketException;
import exceptions.WrongTokenExeption;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class LexicalAnalyserTest {

    @Parameterized.Parameters(
            name = "{index}: Code: \"{0}\", expected tokens: \"{1}\" "
    )
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"def", "def"},
                {"definition", "definition"},
                {"turtle", "turtle"},
                {".", "."},
                {",", ","},
                {";", ";"},


        });
    }

    private final String code;

    private final String expectedToken;


    public LexicalAnalyserTest(String code, String expectedToken) {
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
        if(t.getType() == TokenType.NUMBER){
            Assert.assertEquals(expectedToken, t.getIntContent());
        } else {
            Assert.assertEquals(expectedToken, t.getContent());
        }
    }


}

