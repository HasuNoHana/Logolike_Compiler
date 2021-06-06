package Parser.Expresion;

public class SimplePoint extends PrimaryExpresion {
    private Expresion x;
    private Expresion y;

    public SimplePoint(Expresion x, Expresion y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public PrimaryExpresionEnum getPrimaryExpresionEnum() {
        return PrimaryExpresionEnum.POINT;
    }

    public Expresion getX() {
        return x;
    }

    public Expresion getY() {
        return y;
    }
}
