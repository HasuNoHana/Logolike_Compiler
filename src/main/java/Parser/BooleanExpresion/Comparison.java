package Parser.BooleanExpresion;

import LexicalAnalyzer.TokenType;

public class Comparison {
    private TokenType operand;
    private PrimaryBoolean left;
    private Comparison right;

    public Comparison(PrimaryBoolean left, TokenType operand, Comparison right) {
        this.left = left;
        this.right = right;
        this.operand = operand;
    }

    public Comparison(PrimaryBoolean left) {
        this.left = left;
    }
}
