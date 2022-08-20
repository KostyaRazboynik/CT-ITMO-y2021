package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedDivide extends BinaryOperation {

    public CheckedDivide(ExpressionFunc first, ExpressionFunc second) {
        super(first, second, "/");

    }

    @Override
    public int completeFun(int x, int y) {

        if (y == 0) {
            throw new DivizionByZeroException(x, y, "/");
        }

        if ((x == Integer.MIN_VALUE) && (y == -1)) {
            throw new OverflowException(x, y, "/");
        }
        return x / y;
    }
}