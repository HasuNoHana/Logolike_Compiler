package Parser;

public class Brackets extends PrimaryExpresion{
    Expresion expresion;

    public Expresion getExpresion() {
        return expresion;
    }

    public Brackets(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public PrimaryExpresionType getPrimaryExpresionType() {
        return PrimaryExpresionType.BRACKETS;
    }
}
