package Parser;

public class Number extends PrimaryExpresion{
    public int getNumber() {
        return number;
    }

    int number;

    public Number(int number) {
        this.number = number;
    }

    @Override
    public PrimaryExpresionType getPrimaryExpresionType() {
        return PrimaryExpresionType.NUMBER;
    }
}
