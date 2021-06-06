package Parser.BooleanExpresion;

public abstract class PrimaryBoolean {

    protected boolean negate = false;
    protected PrimaryBooleanType primaryBooleanType;

    public abstract PrimaryBooleanType getPrimaryBooleanType() ;
}
