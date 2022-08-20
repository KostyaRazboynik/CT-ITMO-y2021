package expression.exceptions;

public class OverflowException extends RuntimeException {

    public OverflowException(int x, int y, String sign) {
        super("Overflow exception " + x + " " + sign + " " + y);
    }

    public OverflowException(int x) {
        super("Overflow exception " + x);
    }

    public OverflowException(String s) {
        super ("Overflow exception " + s);
    }
}