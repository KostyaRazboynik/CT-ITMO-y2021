package expression.exceptions;

import expression.ExpressionFunc;

public abstract class UnaryOperation implements ExpressionFunc {

    private ExpressionFunc first;


    public UnaryOperation(ExpressionFunc first) {
        this.first = first;
    }

    protected abstract int completeFun(int one);
    protected abstract String sign();

    @Override
    public int evaluate(int x, int y, int z) {
        return completeFun(first.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return completeFun(first.evaluate(x));
    }

    @Override
    public String toString() {
        return sign() + "(" + first + ")";
    }
}