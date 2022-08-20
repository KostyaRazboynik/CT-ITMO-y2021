package expression.generic;

import java.util.Objects;

public final class GenericVariable<T> implements GenericExpression<T> {
    private String var;

    public GenericVariable(String var) {
        this.var = var;
    }

    @Override
    public String toString() {
        return var;
    }

    @Override
    public T evaluate(T x, T y, T z, OperationMods<T> mode) {
        if ("x".equals(var)) {
            return x;
        } else if ("y".equals(var)) {
            return y;
        } else if ("z".equals(var)) {
            return z;
        }
        return null;
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof GenericVariable) {
            GenericVariable t = (GenericVariable) object;
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