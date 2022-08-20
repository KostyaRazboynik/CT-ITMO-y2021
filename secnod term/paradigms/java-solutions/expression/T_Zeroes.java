package expression;

public class T_Zeroes extends UnaryOperation {

    public T_Zeroes (ExpressionFunc func) {
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