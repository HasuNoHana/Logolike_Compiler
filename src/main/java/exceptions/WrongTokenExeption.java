package exceptions;

public class WrongTokenExeption extends Throwable {
    public WrongTokenExeption(String s) {
        super("There is no sign: "+s);
    }
}
