package expression;

public final class Add extends BinaryOperation {

    public Add(ExpressionFunc first, ExpressionFunc second) {
        super(first, second);
    }

    @Override
    protected int completeFun(int x, int y)  {
        return x + y;
    }

    @Override
    public String sign()  {
        return "+";
    }
}