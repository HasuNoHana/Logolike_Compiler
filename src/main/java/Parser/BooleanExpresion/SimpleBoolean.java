package Parser.BooleanExpresion;

import LexicalAnalyzer.TokenType;

public class SimpleBoolean extends PrimaryBoolean{
    private boolean value;

    public SimpleBoolean(boolean value, boolean negate) {
        this.value = value;
        this.negate = negate;
    }

    public SimpleBoolean(TokenType value, boolean negate) {
        if(value == TokenType.TRUE)
            this.value = true;
        else
            this.value = false;
        this.negate = negate;
    }

    @Override
    public PrimaryBooleanType getPrimaryBooleanType() {
        return PrimaryBooleanType.SIMPLEBOOLEAN;
    }
}
