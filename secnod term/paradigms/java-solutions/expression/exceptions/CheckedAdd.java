package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedAdd extends BinaryOperation {

    public CheckedAdd(ExpressionFunc first, ExpressionFunc second) {
        super(first, second, "+");
    }

    @Override
    public int completeFun(int x, int y) {
        if (x >= 0) {
            if (Integer.MAX_VALUE - x < y) throw new OverflowException(x, y, "+");
        } else {
            if (Integer.MIN_VALUE - x > y) throw new OverflowException(x, y, "+");
        }

        return x + y;
    }
}