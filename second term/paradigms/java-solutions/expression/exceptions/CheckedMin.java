package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedMin extends BinaryOperation {

    public CheckedMin(ExpressionFunc first, ExpressionFunc second) {
        super(first, second, "min");
    }

    @Override
    public int completeFun(int x, int y) {

        if (x > Integer.MAX_VALUE || x < Integer.MIN_VALUE || y > Integer.MAX_VALUE || y < Integer.MIN_VALUE) {
            throw new OverflowException(x, y, "min");
        }
        if (x >= y) return y;
        else return x;
    }
}