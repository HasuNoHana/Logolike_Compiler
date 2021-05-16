package LexicalAnalyzer;

import exceptions.CanNotReadInputException;
import exceptions.WrongTokenExeption;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

import static java.lang.Character.*;



public class LexicalAnalyser {
    static final int LASTCHARACTERS = 30;
    private final BufferedReader reader;
    private String tokenInside;
    private char currentLetter;
    private int currentLine = 0;
    private int numberOfCharacterInLine = 0;
    CircularFifoQueue lastCharacters = new CircularFifoQueue(LASTCHARACTERS);
    ArrayList<Token> tokens;

    public LexicalAnalyser(String program) {
        StringReader stringBuilder = new StringReader(program);
        reader = new BufferedReader(stringBuilder);
        this.tokens = new ArrayList<>();
    }

    public ArrayList<Token> getTokens() throws WrongTokenExeption {
        this.tokens.clear();
        Token token = findNextToken();
        while(!token.getType().equals(TokenType.EOF)){
            tokens.add(token);
            token = findNextToken();
        }
        return this.tokens;
    }

    public Token findNextToken() throws WrongTokenExeption {
        if (tokenInside == null) {
            loadNextLetter();
        }

        tokenInside = String.valueOf(currentLetter);
        deleteWhiteCharacters();

        if (currentLetter == 'd') {
            return gotoState_d();
        } else if (currentLetter == 'f') {
            return gotoState_f();
        } else if (currentLetter == 'i') {
            return gotoState_i();
        } else if (currentLetter == 'e') {
            return gotoState_e();
        } else if (currentLetter == 'T') {
            return gotoState_T();
        } else if (currentLetter == 'F') {
            return gotoState_F();
        } else if (isLetter(currentLetter)) {
            return gotoState_ID();
        } else if (currentLetter == '.') {
            return gotoState_DOT();
        } else if (currentLetter == ',') {
            return gotoState_COMMA();
        } else if (currentLetter == ';') {
            return gotoState_SEMICOLON();
        } else if (currentLetter == '(') {
            return gotoState_ROUND_OPEN_BRACKET();
        } else if (currentLetter == ')') {
            return gotoState_ROUND_CLOSED_BRACKET();
        } else if (currentLetter == '{') {
            return gotoState_CURLY_OPEN_BRACKET();
        } else if (currentLetter == '}') {
            return gotoState_CURLY_CLOSED_BRACKET();
        } else if (currentLetter == '[') {
            return gotoState_SQUARE_OPEN_BRACKET();
        } else if (currentLetter == ']') {
            return gotoState_SQUARE_CLOSED_BRACKET();
        } else if (currentLetter == '=') {
            return gotoState_EQUAL();
        } else if (isPartOfDigit(currentLetter)) {
            return gotoState_NUMBER(Character.getNumericValue(currentLetter));
        } else if (currentLetter == '-') {
            return gotoState_MINUS();
        } else if (currentLetter == '|') {
            return gotoState_PIPE();
        } else if (currentLetter == '&') {
            return gotoState_ONE_AND();
        } else if (currentLetter == '<') {
            return gotoState_LESSER();
        } else if (currentLetter == '>') {
            return gotoState_GREATER();
        } else if (currentLetter == '+') {
            return gotoState_ADD();
        } else if (currentLetter == '*') {
            return gotoState_MULTIPLY();
        } else if (currentLetter == '/') {
            return gotoState_DIVIDE();
        } else if (currentLetter == '!') {
            return gotoState_NEGATION();
        } else if (isEOF()) {
            return new Token("", TokenType.EOF);
        } else {
            throwWrongTokenExeption(tokenInside);
        }
        return null; //it will never run because throwWrongTokenExeption will throw an exception
    }

    private void deleteWhiteCharacters() {
        while(Character.isWhitespace(currentLetter)){
            if(currentLetter == '\r' || currentLetter == '\n'){
                this.currentLine++;
                this.numberOfCharacterInLine = 0;
            }
            loadNextLetter();
            tokenInside = String.valueOf(currentLetter);
        }
    }

    private boolean isEOF() {
        return (int) currentLetter == 65535; // EOF char
    }

    private boolean isPartOfDigit(char currentLetter) {
        return currentLetter == '0' || isPartOfNonZeroDigit(currentLetter);
    }

    private boolean isPartOfNonZeroDigit(char c) {
        return c == '1' || c == '2' || c == '3' || c == '4' || c == '5' || c == '6' || c == '7' || c == '8' || c == '9';
    }

    private boolean isPartOfId(char currentLetter) {
        return isLetter(currentLetter) || isDigit(currentLetter) || currentLetter == '_';
    }

    private void loadNextLetter() {
        try {
            currentLetter = (char) reader.read();

        } catch (IOException e) {
            throw new CanNotReadInputException("Can not read input",e); // TODO make more verbose
        }

        this.numberOfCharacterInLine++;
        this.lastCharacters.add(currentLetter);
        tokenInside += currentLetter;
    }

    private String getFinalToken() {
        int finalTokenLenght = tokenInside.length() - 1;
        return tokenInside.substring(0, finalTokenLenght);
    }

    private void throwWrongTokenExeption(String s) throws WrongTokenExeption {
        System.out.println("There has been WrongTokenExeption in line: "+currentLine + " letter: "+(numberOfCharacterInLine-1));
        lastCharacters.printContent();
        throw new WrongTokenExeption(s);
    }

//    --------------------------------------------------------------------------------
//                                  GO TO STATE
//    --------------------------------------------------------------------------------


    private Token gotoState_F() {
        loadNextLetter();
        if (currentLetter == 'a') {
            return gotoState_Fa();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Fa() {
        loadNextLetter();
        if (currentLetter == 'l') {
            return gotoState_Fal();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Fal() {
        loadNextLetter();
        if (currentLetter == 's') {
            return gotoState_Fals();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Fals() {
        loadNextLetter();
        if (currentLetter == 'e') {
            return gotoState_False();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_False() {
        loadNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.FALSE);
        } else {
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_T() {
        loadNextLetter();
        if (currentLetter == 'r') {
            return gotoState_Tr();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Tr() {
        loadNextLetter();
        if (currentLetter == 'u') {
            return gotoState_Tru();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Tru() {
        loadNextLetter();
        if (currentLetter == 'e') {
            return gotoState_True();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_True() {
        loadNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.TRUE);
        } else {
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_e() {
        loadNextLetter();
        if (currentLetter == 'l') {
            return gotoState_el();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_el() {
        loadNextLetter();
        if (currentLetter == 's') {
            return gotoState_els();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_els() {
        loadNextLetter();
        if (currentLetter == 'e') {
            return gotoState_else();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_else() {
        loadNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.ELSE);
        } else {
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_i() {
        loadNextLetter();
        if (currentLetter == 'f') {
            return gotoState_if();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_if() {
        loadNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.IF);
        } else {
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_f() {
        loadNextLetter();
        if (currentLetter == 'o') {
            return gotoState_fo();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_fo() {
        loadNextLetter();
        if (currentLetter == 'r') {
            return gotoState_for();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_for() {
        loadNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.FOR);
        } else {
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_NEGATION() {
        loadNextLetter();
        if (currentLetter == '=') {
            return gotoState_NOT_EQUALS();
        }
        return new Token(getFinalToken(), TokenType.NEGATION);
    }

    private Token gotoState_NOT_EQUALS() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.NOT_EQUALS);
    }

    private Token gotoState_DIVIDE() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.DIVIDE);
    }

    private Token gotoState_MULTIPLY() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.MULTIPLY);
    }

    private Token gotoState_ADD() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.PLUS);
    }

    private Token gotoState_GREATER() {
        loadNextLetter();
        if (currentLetter == '=') {
            return gotoState_GREATER_EQUALS();
        }
        return new Token(getFinalToken(), TokenType.GREATER);
    }

    private Token gotoState_GREATER_EQUALS() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.GREATER_EQUAL);
    }

    private Token gotoState_LESSER() {
        loadNextLetter();
        if (currentLetter == '=') {
            return gotoState_LESSER_EQUALS();
        }
        return new Token(getFinalToken(), TokenType.LESSER);
    }

    private Token gotoState_LESSER_EQUALS() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.LESSER_EQUAL);
    }

    private Token gotoState_ONE_AND() throws WrongTokenExeption {
        loadNextLetter();
        if (currentLetter == '&') {
            return gotoState_AND();
        }
        throwWrongTokenExeption("&");
        return null;//it will never run because throwWrongTokenExeption will throw an exception
    }

    private Token gotoState_AND() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.AND);
    }

    private Token gotoState_PIPE() throws WrongTokenExeption {
        loadNextLetter();
        if (currentLetter == '|') {
            return gotoState_OR();
        }
        throwWrongTokenExeption("|");
        return null;
    }

    private Token gotoState_OR() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.OR);
    }

    private Token gotoState_MINUS() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.MINUS);
    }


    private Token gotoState_SQUARE_CLOSED_BRACKET() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.SQUARE_CLOSED_BRACKET);
    }

    private Token gotoState_SQUARE_OPEN_BRACKET() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.SQUARE_OPEN_BRACKET);
    }

    private Token gotoState_EQUAL() {
        loadNextLetter();
        if (currentLetter == '=') {
            return gotoState_IS_EQUAL();
        }
        return new Token(getFinalToken(), TokenType.ASSIGN);
    }

    private Token gotoState_IS_EQUAL() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.EQUAL);
    }

    private Token gotoState_CURLY_CLOSED_BRACKET() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.CURLY_CLOSED_BRACKET);
    }

    private Token gotoState_CURLY_OPEN_BRACKET() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.CURLY_OPEN_BRACKET);
    }

    private Token gotoState_ROUND_CLOSED_BRACKET() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.ROUND_CLOSED_BRACKET);
    }

    private Token gotoState_ROUND_OPEN_BRACKET() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.ROUND_OPEN_BRACKET);
    }

    private Token gotoState_SEMICOLON() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.SEMICOLON);
    }

    private Token gotoState_COMMA() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.COMMA);
    }

    private Token gotoState_DOT() {
        loadNextLetter();
        return new Token(getFinalToken(), TokenType.DOT);
    }

    private Token gotoState_d() {
        loadNextLetter();
        if (currentLetter == 'e') {
            return gotoState_de();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_de() {
        loadNextLetter();

        if (currentLetter == 'f') {
            return gotoState_def();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_def() {
        loadNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.DEF);
        } else {
            return gotoState_ID(currentLetter);
        }
    }


    private Token gotoState_ID(char currentLetter) {
        if (isPartOfId(currentLetter)) {
            return gotoState_ID();
        } else {
            return new Token(getFinalToken(), TokenType.ID);
        }
    }

    private Token gotoState_ID() {
        loadNextLetter();
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_NUMBER(int number) {
        loadNextLetter();
        if (isPartOfDigit(currentLetter)) {
            number = number*10 + Character.getNumericValue(currentLetter);
            return gotoState_NUMBER(number);
        }
        return new Token(number, TokenType.NUMBER);
    }


}
