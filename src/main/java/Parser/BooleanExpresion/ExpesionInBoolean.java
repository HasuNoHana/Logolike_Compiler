package Parser.BooleanExpresion;

public class ExpesionInBoolean extends PrimaryBoolean{

    private Exception exception;

    public ExpesionInBoolean(Exception exception, boolean negate) {
        this.exception = exception;
        this.negate = negate;
    }

    @Override
    public PrimaryBooleanType getPrimaryBooleanType() {
        return PrimaryBooleanType.EXPRESION;
    }

    public Exception getException() {
        return exception;
    }
}
