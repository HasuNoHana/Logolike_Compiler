import LexicalAnalyzer.LexicalAnalyser;
import LexicalAnalyzer.Token;
import Parser.Parser;
import Parser.Function;
import exceptions.WrongTokenExeption;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Compiler {

    public static void main(String[] args) throws IOException, WrongTokenExeption {
        Path filePath = Paths.get("src/main/resources/SimpleInput.txt");
        String content = Files.readString(filePath, StandardCharsets.US_ASCII);

        LexicalAnalyser analyzer = new LexicalAnalyser(content);
        Parser parser = new Parser();

        ArrayList<Token> tokens = analyzer.getTokens();
        ArrayList<Function> functions = parser.parse(tokens);
    }
}
