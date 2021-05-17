package Parser;

public class SimplePoint extends PrimaryExpresion{
    private Expresion X;
    private Expresion Y;

    public SimplePoint(Expresion x, Expresion y) {
        X = x;
        Y = y;
    }

    @Override
    public PrimaryExpresionType getPrimaryExpresionType() {
        return PrimaryExpresionType.POINT;
    }
}
