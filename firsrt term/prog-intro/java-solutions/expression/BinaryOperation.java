package expression;

import java.util.Objects;

public abstract class BinaryOperation implements ExpressionFunc {
    private final ExpressionFunc first;
    private final ExpressionFunc second;

    public BinaryOperation(ExpressionFunc first, ExpressionFunc second) {
        this.first = first;
        this.second = second;
    }
    protected abstract int completeFun(int x, int y);
    protected abstract String sign();

    @Override
    public int evaluate(int x, int y, int z) {
        return completeFun(first.evaluate(x, y, z), second.evaluate(x, y, z));
    }

    @Override
    public int evaluate(int x) {
        return completeFun(first.evaluate(x), second.evaluate(x));
    }

    @Override
    public String toString() {
        return "(" + first + " " + sign() + " " + second + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof BinaryOperation) {
            BinaryOperation curr = (BinaryOperation) object;
            return Objects.equals(this.getClass(), object.getClass()) && Objects.equals(first, curr.first) && Objects.equals(second, curr.second);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second, this.getClass());
    }
}
