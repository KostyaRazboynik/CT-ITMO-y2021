package expression;

import java.util.Objects;

public final class Variable implements ExpressionFunc {
    private final String var;

    public Variable(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public int evaluate(int x) {
        return x;
    }

    @Override
    public int evaluate(int x, int y, int z) {
        if (var.equals("x")) {
            return x;
        } else if (var.equals("y")) {
            return y;
        } else if (var.equals("z")) {
            return z;
        } else {
            return -1;
        }
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof Variable) {
            Variable t = (Variable) object;
            return Objects.equals(t.var, var);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(var);
    }
}