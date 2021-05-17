package Parser.BooleanExpresion;

import LexicalAnalyzer.TokenType;

public class AndExpresion {
    private TokenType operand;
    private Comparison left;
    private AndExpresion right;

    public AndExpresion(Comparison left) {
        this.left = left;
    }

    public AndExpresion(Comparison left, TokenType operand, AndExpresion right) {
        this.left = left;
        this.right = right;
        this.operand = operand;
    }

    public TokenType getOperand() {
        return operand;
    }

    public Comparison getLeft() {
        return left;
    }

    public AndExpresion getRight() {
        return right;
    }
}
