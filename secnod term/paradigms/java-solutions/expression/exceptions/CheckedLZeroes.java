package expression.exceptions;

import expression.ExpressionFunc;

public class CheckedLZeroes extends UnaryOperation {

    public CheckedLZeroes(ExpressionFunc func) {
        super (func);
    }

    @Override
    public int completeFun(int x) {
        return Integer.numberOfLeadingZeros(x);
    }

    @Override
    public String sign()  {
        return "l0";
    }
}