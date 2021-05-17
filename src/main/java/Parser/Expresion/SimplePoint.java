package Parser.Expresion;

import Parser.Expresion.Expresion;
import Parser.Expresion.PrimaryExpresion;
import Parser.Expresion.PrimaryExpresionType;

public class SimplePoint extends PrimaryExpresion {
    private Expresion x;
    private Expresion y;

    public SimplePoint(Expresion x, Expresion y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public PrimaryExpresionType getPrimaryExpresionType() {
        return PrimaryExpresionType.POINT;
    }

    public Expresion getX() {
        return x;
    }

    public Expresion getY() {
        return y;
    }
}
