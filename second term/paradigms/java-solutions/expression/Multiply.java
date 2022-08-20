package expression;

public final class Multiply extends BinaryOperation {

    public Multiply(ExpressionFunc first, ExpressionFunc second) {
        super(first, second);
    }

    @Override
    public int completeFun(int x, int y)  {
        /*if (x == 0 || y == 0) {
            return 0;
        }*/
        return x * y;
    }

    @Override
    public String sign()  {
        return "*";
    }
}