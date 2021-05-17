package Parser.BooleanExpresion;

import LexicalAnalyzer.TokenType;
import Parser.Expresion.PrimaryExpresionType;

public abstract class PrimaryBoolean {

    protected boolean negate = false;
    protected PrimaryBooleanType primaryBooleanType;

    public abstract PrimaryBooleanType getPrimaryBooleanType() ;
}
