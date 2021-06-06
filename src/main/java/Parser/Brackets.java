package Parser;

import Parser.Expresion.Expresion;
import Parser.Expresion.PrimaryExpresion;
import Parser.Expresion.PrimaryExpresionEnum;

public class Brackets extends PrimaryExpresion {
    Expresion expresion;

    public Expresion getExpresion() {
        return expresion;
    }

    public Brackets(Expresion expresion) {
        this.expresion = expresion;
    }

    @Override
    public PrimaryExpresionEnum getPrimaryExpresionEnum() {
        return PrimaryExpresionEnum.BRACKETS;
    }
}
