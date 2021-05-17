package Parser.BooleanExpresion;

public class Brackets extends PrimaryBoolean{

    private BooleanExpresion expresion;

    public Brackets(BooleanExpresion expresion, boolean negate) {
        this.expresion = expresion;
        this.negate = negate;
    }

    @Override
    public PrimaryBooleanType getPrimaryBooleanType() {
        return PrimaryBooleanType.BRACKETS;
    }
}
