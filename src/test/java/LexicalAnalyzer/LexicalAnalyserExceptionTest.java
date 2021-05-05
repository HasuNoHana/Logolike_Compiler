package LexicalAnalyzer;

import exceptions.MissingEndBracketException;
import exceptions.WrongTokenExeption;
import org.junit.Test;

public class LexicalAnalyserExceptionTest {
    @Test(expected = MissingEndBracketException.class)
    public void shouldThrowExceptionIfEndBracketNotPresent() throws MissingEndBracketException, WrongTokenExeption {
        // given
        String programCode = "def";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);

        // when
        analyzer.findNextToken();
    }

    @Test(expected = WrongTokenExeption.class)
    public void shouldThrowWrongTokenExeption_AND() throws MissingEndBracketException, WrongTokenExeption {
        // given
        String programCode = "x & y";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);

        // when
        analyzer.findNextToken();
        analyzer.findNextToken();
    }
    @Test(expected = WrongTokenExeption.class)
    public void shouldThrowWrongTokenExeption_OR() throws MissingEndBracketException, WrongTokenExeption {
        // given
        String programCode = "x | y";
        LexicalAnalyser analyzer = new LexicalAnalyser(programCode);

        // when
        analyzer.findNextToken();
        analyzer.findNextToken();
    }

}
