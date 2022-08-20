package expression;

public final class Subtract extends BinaryOperation {

    public Subtract(ExpressionFunc first, ExpressionFunc second) {
        super(first, second);
    }

    @Override
    public int completeFun(int x, int y) {
        return x - y;
    }

    @Override
    public String sign()  {
        return "-";
    }
}