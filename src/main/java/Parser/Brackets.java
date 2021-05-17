package Parser;

import Parser.Expresion.Expresion;
import Parser.Expresion.PrimaryExpresion;
import Parser.Expresion.PrimaryExpresionType;

public class Brackets extends PrimaryExpresion {
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
