package Parser;

import LexicalAnalyzer.TokenType;

public class MulExpresion {
    private TokenType mulOperand;

    public PrimaryExpresion getLeft() {
        return left;
    }

    private PrimaryExpresion left;
    private MulExpresion right;

    public MulExpresion(PrimaryExpresion left) {
        this.left = left;
    }


    public void setOperand(TokenType mulOperand) {
        this.mulOperand = mulOperand;
    }

    public void setRight(MulExpresion right) {
        this.right = right;
    }
}
