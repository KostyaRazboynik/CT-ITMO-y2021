package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedMultiply extends BinaryOperation {

    public CheckedMultiply(ExpressionFunc first, ExpressionFunc second) {
        super(first, second, "*");
    }

    @Override
    public int completeFun(int x, int y) {

        if (x != 0 && y != 0 && ((x * y) / y != x || (x * y) / x != y)) {
            throw new OverflowException(x, y, "*");
        }

        return x * y;
    }
}