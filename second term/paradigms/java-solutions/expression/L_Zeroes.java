package expression;

public class L_Zeroes extends UnaryOperation {

    public L_Zeroes(ExpressionFunc func) {
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