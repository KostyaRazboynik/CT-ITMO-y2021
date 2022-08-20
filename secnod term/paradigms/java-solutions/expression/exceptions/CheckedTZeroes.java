package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedTZeroes extends UnaryOperation {

    public CheckedTZeroes (ExpressionFunc func) {
        super (func);
    }

    @Override
    public int completeFun(int x) {
        return Integer.numberOfTrailingZeros(x);
    }

    @Override
    public String sign() {
        return "t0";
    }
}