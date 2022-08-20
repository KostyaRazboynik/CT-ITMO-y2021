package expression;

public class UnaryMinus extends UnaryOperation {

    public UnaryMinus(ExpressionFunc func) {
        super (func);
    }
    @Override
    public int completeFun(int x) {
        return x * (-1);
    }
    @Override
    public String sign()  {
        return "-";
    }
}