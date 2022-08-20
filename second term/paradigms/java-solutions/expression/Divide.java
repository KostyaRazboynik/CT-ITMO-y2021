package expression;

public final class Divide extends BinaryOperation {

    public Divide(ExpressionFunc first, ExpressionFunc second) {
        super(first, second);
    }

    @Override
    public int completeFun(int x, int y) {
        return x / y;
    }

    @Override
    public String sign() {
        return "/";
    }
}