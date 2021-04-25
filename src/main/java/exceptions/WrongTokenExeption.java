package exceptions;

public class WrongTokenExeption extends Throwable {
    public WrongTokenExeption(String s) {
        super("Błąd składni, nie ma znaku: "+s);
    }
}
