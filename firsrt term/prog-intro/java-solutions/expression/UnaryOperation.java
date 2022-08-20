package expression;
import java.util.Objects;

public abstract class UnaryOperation implements ExpressionFunc {

    private final ExpressionFunc first;

    public UnaryOperation(ExpressionFunc first) {
        this.first = first;
    }

    protected abstract int completeFun(int one);
    protected abstract String sign();

    @Override
    public int evaluate(int x) {
        return completeFun(first.evaluate(x));
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return completeFun(first.evaluate(x, y, z));
    }

    @Override
    public String toString() {
        return sign()+ "(" + first + ")";
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, this.getClass());
    }
}