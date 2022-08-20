package expression;

public class Const implements ExpressionFunc {

    private final int num;

    public Const(int num) {
        this.num = num;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        return num;
    }
    @Override
    public int evaluate(int x) {
        return num;
    }

    @Override
    public String toString() {
        return Integer.toString(num);
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Const) {
            Const t = (Const) object;
            return num == t.num;
        } else {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(num);
    }
}