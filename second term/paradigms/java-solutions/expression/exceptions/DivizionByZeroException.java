package expression.exceptions;

public class DivizionByZeroException extends RuntimeException {

    public DivizionByZeroException(int x, int y, String sign) {
        super("division by zero " + x + " " + sign + " " + y);
    }
}