package Parser.Expresion;

import LexicalAnalyzer.TokenType;

public class MulExpresion{
    private TokenType mulOperand;
    private PrimaryExpresion left;
    private MulExpresion right;

    public MulExpresion(PrimaryExpresion left, TokenType operand, MulExpresion right) {
        this.left = left;
        this.right = right;
        this.mulOperand = operand;
    }

    public PrimaryExpresion getLeft() {
        return left;
    }

    public MulExpresion(PrimaryExpresion left) {
        this.left = left;
    }


    public void setOperand(TokenType mulOperand) {
        this.mulOperand = mulOperand;
    }

    public void setRight(MulExpresion right) {
        this.right = right;
    }

    public MulExpresion getRight() {
        return this.right;
    }

    public TokenType getOperand() {
        return mulOperand;
    }
}
