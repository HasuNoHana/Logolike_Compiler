package LexicalAnalyzer;

import exceptions.MissingEndBracketException;
import exceptions.WrongTokenExeption;
import org.junit.Assert;
import org.junit.Test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(Parameterized.class)
public class LexicalAnalyserIntegrationTest {

    @Parameterized.Parameters(
            name = "{index}: Code: \"{0}\", expected tokens: \"{1}\" "
    )
    public static Iterable<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"def ", new ArrayList<String>(){{
                    add("def");
                }}},
                {"def ", new ArrayList<String>(){{
                    add("def");
                }}},
                {"def function() ", new ArrayList<String>(){{
                    add("def");
                    add("function");
                    add("(");
                    add(")");
                }}},
                {"def function(){} ", new ArrayList<String>(){{
                    add("def");
                    add("function");
                    add("(");
                    add(")");
                    add("{");
                    add("}");
                }}},
                {"def function(){\n turtle = new Turtle([0,2]) } ", new ArrayList<String>(){{
                    add("def");add("function");add("(");add(")");
                    add("{");
                    add("turtle");add("=");add("new");add("Turtle");
                    add("(");add("[");add("0");add(",");add("2");add("]");add(")");
                    add("}");
                }}},
                {"def function(){\n turtle = new Turtle([-3,200]); }\n", new ArrayList<String>(){{
                    add("def");add("function");add("(");add(")");
                    add("{");
                    add("turtle");add("=");add("new");add("Turtle");
                    add("(");
                    add("[");add("-3");add(",");add("200");add("]");
                    add(")");add(";");
                    add("}");
                }}},
                {"def function(){\n turtle = new Turtle([-3,200]);" +
                        "turtle.rect(20, 20);" +
                        "if !(x >= 2 && z>10 ) || (y<-2 && z<=3) {}" +
                        "else {} }\n", new ArrayList<String>(){{
                    add("def");add("function");add("(");add(")");
                    add("{");
                    add("turtle");add("=");add("new");add("Turtle");
                    add("(");
                    add("[");add("-3");add(",");add("200");add("]");
                    add(")");add(";");

                    add("turtle");add(".");add("rect");
                    add("(");add("20");add(",");add("20");add(")");add(";");

                    add("if");add("!");add("(");add("x");add(">=");add("2");add("&&");
                    add("z");add(">");add("10");add(")");
                    add("||");
                    add("(");add("y");add("<");add("-2");
                    add("&&");add("z");add("<=");add("3");add(")");

                    add("{");add("}");
                    add("else");add("{");add("}");
                    add("}");
                }}},
                {"def function(){\n " +
                        "for i=0; 2; 1 {} }\n", new ArrayList<String>(){{
                    add("def");add("function");add("(");add(")");
                    add("{");

                    add("for");add("i");add("=");add("0");add(";");
                    add("2");add(";");add("1");add("{");add("}");

                    add("}");
                }}},
                {"def function(){\n " +
                        "k = 3+4*5/2; }\n", new ArrayList<String>(){{
                    add("def");add("function");add("(");add(")");
                    add("{");

                    add("k");add("=");add("3");add("+");add("4");add("*");add("5");
                    add("/");add("2");add(";");

                    add("}");
                }}}
        });
    }

    private final String code;

    private final ArrayList<String> expectedToken;


    public LexicalAnalyserIntegrationTest(String code, ArrayList<String> expectedToken) {
        this.code = code;
        this.expectedToken = expectedToken;
    }

    @Test
    public void test() throws MissingEndBracketException {
        // given
        LexicalAnalyser analyzer = new LexicalAnalyser(code);
        ArrayList<String> tokens = new ArrayList<String>();

        // when
        while(true){
            try{
                tokens.add(analyzer.findNextToken().getContent());
            } catch (WrongTokenExeption missingEndBracketException) {
                break;
            }
        }

        // then
        Assert.assertEquals(expectedToken, tokens);
    }

}