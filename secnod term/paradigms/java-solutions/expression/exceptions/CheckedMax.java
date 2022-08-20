package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedMax extends BinaryOperation {

    public CheckedMax(ExpressionFunc first, ExpressionFunc second) {
        super(first, second, "max");
    }

    @Override
    public int completeFun(int x, int y) {

        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE || y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) {
            throw new OverflowException(x, y, "max");
        }
        if (x >= y) return x;
        else return y;
    }
}