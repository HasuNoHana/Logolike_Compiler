package Parser;

import LexicalAnalyzer.TokenType;

public class Expresion {
    private TokenType operand;

    public MulExpresion getLeft() {
        return left;
    }

    private MulExpresion left;
    private Expresion right;

    public Expresion(MulExpresion left) {
        this.left = left;
    }

    public void setOperand(TokenType operand) {
        this.operand = operand;
    }

    public void setRight(Expresion right) {
        this.right = right;
    }
}
