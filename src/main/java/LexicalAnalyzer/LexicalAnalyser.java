package LexicalAnalyzer;

import exceptions.MissingEndBracketException;
import exceptions.WrongTokenExeption;

import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;

public class LexicalAnalyser {
    private final String program;
    private int current = 0;
    private String tokenInside;

    public LexicalAnalyser(String program) {
        this.program = program;
    }

    public Token findNextToken() throws MissingEndBracketException, WrongTokenExeption {
        char currentLetter = tokenInside == null ? getNextLetter() : tokenInside.substring(tokenInside.length()-1).charAt(0);
        tokenInside = String.valueOf(currentLetter);
        try {
            if (currentLetter == ' ' || currentLetter == '\n'){
                getNextLetter();
                return findNextToken();
            }
            else if (currentLetter == 'd') {
                return gotoState_d();
            }
            else if (currentLetter == 'f') {
                return gotoState_f();
            }
            else if (currentLetter == 'i') {
                return gotoState_i();
            }
            else if (currentLetter == 'e') {
                return gotoState_e();
            }
            else if (currentLetter == 'T') {
                return gotoState_T();
            }
            else if (currentLetter == 'F') {
                return gotoState_F();
            }
            else if(isPartOfId(currentLetter)){
                return gotoState_ID(currentLetter);
            }
            else if(currentLetter == '.'){
                return gotoState_DOT();
            }
            else if(currentLetter == ','){
                return gotoState_COMMA();
            }
            else if(currentLetter == ';'){
                return gotoState_SEMICOLON();
            }
            else if(currentLetter == '('){
                return gotoState_ROUND_OPEN_BRACKET();
            }
            else if(currentLetter == ')'){
                return gotoState_ROUND_CLOSED_BRACKET();
            }
            else if(currentLetter == '{'){
                return gotoState_CURLY_OPEN_BRACKET();
            }
            else if(currentLetter == '}'){
                return gotoState_CURLY_CLOSED_BRACKET();
            }
            else if(currentLetter == '['){
                return gotoState_SQUARE_OPEN_BRACKET();
            }
            else if(currentLetter == ']'){
                return gotoState_SQUARE_CLOSED_BRACKET();
            }
            else if(currentLetter == '='){
                return gotoState_EQUAL();
            }
            else if(isPartOfDigit(currentLetter)){
                return gotoState_NUMBER();
            }
            else if(currentLetter == '-'){
                return gotoState_MINUS();
            }
            else if(currentLetter == '|'){
                return gotoState_PIPE();
            }
            else if(currentLetter == '&'){
                return gotoState_ONE_AND();
            }
            else if(currentLetter == '<'){
                return gotoState_LESSER();
            }
            else if(currentLetter == '>'){
                return gotoState_GREATER();
            }
            else if(currentLetter == '+'){
                return gotoState_ADD();
            }
            else if(currentLetter == '*'){
                return gotoState_MULTIPLY();
            }
            else if(currentLetter == '/'){
                return gotoState_DIVIDE();
            }
            else if(currentLetter == '!'){
                return gotoState_NEGATION();
            }
            return null;
        } catch (StringIndexOutOfBoundsException stringIndexOutOfBoundsException) {
            throw new MissingEndBracketException("Your file is missing } at the end");
        }
    }

    private boolean isPartOfDigit(char currentLetter) {
        return currentLetter=='0' || isPartOfNonZeroDigit(currentLetter);
    }

    private boolean isPartOfNonZeroDigit(char c) {
        return c=='1' ||c=='2' ||c=='3' ||c=='4' ||c=='5' ||c=='6' ||c=='7' ||c=='8' ||c=='9';
    }

    private boolean isPartOfId(char currentLetter) {
        return isLetter(currentLetter) || isDigit(currentLetter) || currentLetter == '_';
    }

    private char getNextLetter() {
        char currentLetter = program.charAt(current);
        current++;
        tokenInside += currentLetter;
        return currentLetter;
    }

    private String getFinalToken() {
        int finalTokenLenght = tokenInside.length() -1;
        return tokenInside.substring(0, finalTokenLenght);
    }



//    --------------------------------------------------------------------------------
//                                  GO TO STATE
//    --------------------------------------------------------------------------------


    private Token gotoState_F() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'a') {
            return gotoState_Fa();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Fa() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'l') {
            return gotoState_Fal();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Fal() {
        char currentLetter = getNextLetter();
        if (currentLetter == 's') {
            return gotoState_Fals();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Fals() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'e') {
            return gotoState_False();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_False() {
        char currentLetter = getNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.FALSE);
        }
        else{
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_T() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'r') {
            return gotoState_Tr();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Tr() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'u') {
            return gotoState_Tru();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_Tru() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'e') {
            return gotoState_True();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_True() {
        char currentLetter = getNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.TRUE);
        }
        else{
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_e() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'l') {
            return gotoState_el();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_el() {
        char currentLetter = getNextLetter();
        if (currentLetter == 's') {
            return gotoState_els();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_els() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'e') {
            return gotoState_else();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_else() {
        char currentLetter = getNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.ELSE);
        }
        else{
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_i() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'f') {
            return gotoState_if();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_if() {
        char currentLetter = getNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.IF);
        }
        else{
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_f() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'o') {
            return gotoState_fo();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_fo() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'r') {
            return gotoState_for();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_for() {
        char currentLetter = getNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.FOR);
        }
        else{
            return gotoState_ID(currentLetter);
        }
    }

    private Token gotoState_NEGATION() {
        char currentLetter = getNextLetter();
        if(currentLetter == '='){
            return gotoState_NOT_EQUALS();
        }
        return new Token(getFinalToken(), TokenType.NEGATION);
    }

    private Token gotoState_NOT_EQUALS() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.NOT_EQUALS);
    }

    private Token gotoState_DIVIDE() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.DIVIDE);
    }

    private Token gotoState_MULTIPLY() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.MULTIPLY);
    }

    private Token gotoState_ADD() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.PLUS);
    }

    private Token gotoState_GREATER() {
        char currentLetter = getNextLetter();
        if(currentLetter == '='){
            return gotoState_GREATER_EQUALS();
        }
        return new Token(getFinalToken(), TokenType.GREATER);
    }

    private Token gotoState_GREATER_EQUALS() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.GREATER_EQUAL);
    }

    private Token gotoState_LESSER() {
        char currentLetter = getNextLetter();
        if(currentLetter == '='){
            return gotoState_LESSER_EQUALS();
        }
        return new Token(getFinalToken(), TokenType.LESSER);
    }

    private Token gotoState_LESSER_EQUALS() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.LESSER_EQUAL);
    }

    private Token gotoState_ONE_AND() throws WrongTokenExeption {
        char currentLetter = getNextLetter();
        if(currentLetter == '&'){
            return gotoState_AND();
        }
        throw new WrongTokenExeption("&");
    }

    private Token gotoState_AND() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.AND);
    }

    private Token gotoState_PIPE() throws WrongTokenExeption {
        char currentLetter = getNextLetter();
        if(currentLetter == '|'){
            return gotoState_OR();
        }
        throw new WrongTokenExeption("|");
    }

    private Token gotoState_OR() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.OR);
    }

    private Token gotoState_MINUS() {
        char currentLetter = getNextLetter();
        if(isPartOfDigit(currentLetter)){
            return gotoState_NUMBER();
        }
        return new Token(getFinalToken(), TokenType.MINUS);
    }

    private Token gotoState_NUMBER() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.NUMBER);
    }



    private Token gotoState_SQUARE_CLOSED_BRACKET() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.SQUARE_CLOSED_BRACKET);
    }

    private Token gotoState_SQUARE_OPEN_BRACKET() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.SQUARE_OPEN_BRACKET);
    }

    private Token gotoState_EQUAL() {
        char currentLetter = getNextLetter();
        if(currentLetter == '='){
            return gotoState_IS_EQUAL();
        }
        return new Token(getFinalToken(), TokenType.ASSIGN);
    }

    private Token gotoState_IS_EQUAL() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.EQUAL);
    }

    private Token gotoState_CURLY_CLOSED_BRACKET() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.CURLY_CLOSED_BRACKET);
    }

    private Token gotoState_CURLY_OPEN_BRACKET() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.CURLY_OPEN_BRACKET);
    }

    private Token gotoState_ROUND_CLOSED_BRACKET() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.ROUND_CLOSED_BRACKET);
    }

    private Token gotoState_ROUND_OPEN_BRACKET() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.ROUND_OPEN_BRACKET);
    }

    private Token gotoState_SEMICOLON() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.SEMICOLON);
    }

    private Token gotoState_COMMA() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.COMMA);
    }

    private Token gotoState_DOT() {
        getNextLetter();
        return new Token(getFinalToken(), TokenType.DOT);
    }

    private Token gotoState_d() {
        char currentLetter = getNextLetter();
        if (currentLetter == 'e') {
            return gotoState_de();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_de() {
        char currentLetter = getNextLetter();

        if (currentLetter == 'f') {
            return gotoState_def();
        }
        return gotoState_ID(currentLetter);
    }

    private Token gotoState_def() {
        char currentLetter = getNextLetter();
        if (!isPartOfId(currentLetter)) {
            return new Token(getFinalToken(), TokenType.DEF);
        }
        else{
            return gotoState_ID(currentLetter);
        }
    }



    private Token gotoState_ID(char currentLetter) {
        if (isPartOfId(currentLetter)){
            return gotoState_ID();
        }
        else {
            return new Token(getFinalToken(), TokenType.ID);
        }
    }

    private Token gotoState_ID() {
        return gotoState_ID(getNextLetter());
    }


}
