package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedNegate extends UnaryOperation {

    public CheckedNegate(ExpressionFunc first) {
        super(first);
    }

    @Override
    public int completeFun(int x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException(x);
        }
        return -x;
    }

    @Override
    public String sign() {
        return "-";
    }
}