package expression.exceptions;

import expression.ExpressionFunc;

public abstract class BinaryOperation implements ExpressionFunc {

    private ExpressionFunc x;
    private ExpressionFunc second;
    private String sign;

    public BinaryOperation(ExpressionFunc first, ExpressionFunc second, String sign) {
        this.x = first;
        this.second = second;
        this.sign = sign;
    }

    protected abstract int completeFun(int one, int two);

    @Override
    public int evaluate(int x) {
        return completeFun(this.x.evaluate(x), second.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return completeFun((this.x).evaluate(x, y, z), (second).evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return "(" + x + " " + sign + " " + second + ")";
    }
}