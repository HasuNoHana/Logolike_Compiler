package Parser.BooleanExpresion;

import LexicalAnalyzer.TokenType;

public class BooleanExpresion {
    private TokenType operand;
    private AndExpresion left;
    private BooleanExpresion right;

    public BooleanExpresion(AndExpresion left) {
        this.left = left;
    }

    public BooleanExpresion(AndExpresion left, TokenType operand, BooleanExpresion right) {
        this.left = left;
        this.right = right;
        this.operand = operand;
    }

    public TokenType getOperand() {
        return operand;
    }

    public AndExpresion getLeft() {
        return left;
    }

    public BooleanExpresion getRight() {
        return right;
    }
}
