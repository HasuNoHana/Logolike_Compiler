package Parser.BooleanExpresion;

public class BooleanBrackets extends PrimaryBoolean{

    private BooleanExpresion expresion;

    public BooleanBrackets(BooleanExpresion expresion, boolean negate) {
        this.expresion = expresion;
        this.negate = negate;
    }

    @Override
    public PrimaryBooleanType getPrimaryBooleanType() {
        return PrimaryBooleanType.BRACKETS;
    }
}
