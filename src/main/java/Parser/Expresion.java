package Parser;

import LexicalAnalyzer.TokenType;

public class Expresion {
    private TokenType operand;
    private MulExpresion left;
    private Expresion right;

    public Expresion(MulExpresion left, TokenType operand, Expresion right) {
        this.left = left;
        this.right = right;
        this.operand = operand;
    }

    public MulExpresion getLeft() {
        return left;
    }

    public Expresion(MulExpresion left) {
        this.left = left;
    }

    public void setOperand(TokenType operand) {
        this.operand = operand;
    }

    public void setRight(Expresion right) {
        this.right = right;
    }

    public Expresion getRight() {
        return right;
    }

    public TokenType getOperand() {
        return operand;
    }
}
