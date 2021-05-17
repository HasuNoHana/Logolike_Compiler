package Parser.BooleanExpresion;

import Parser.Expresion.Expresion;

public class ExpesionInBoolean extends PrimaryBoolean{

    private Expresion exception;

    public ExpesionInBoolean(Expresion exception, boolean negate) {
        this.exception = exception;
        this.negate = negate;
    }

    @Override
    public PrimaryBooleanType getPrimaryBooleanType() {
        return PrimaryBooleanType.EXPRESION;
    }

    public Expresion getException() {
        return exception;
    }
}
