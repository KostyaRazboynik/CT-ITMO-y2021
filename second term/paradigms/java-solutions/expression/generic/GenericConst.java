package expression.generic;

import java.util.Objects;

public final class GenericConst<T> implements GenericExpression<T> {

    private T num;

    public GenericConst(T num) {
        this.num = num;
    }

    @Override
    public T evaluate(T x, T y, T z, OperationMods<T> mode) {
        return num;
    }

    @Override
    public String toString() {
        return num.toString();
    }

    @Override
    public boolean equals(Object object) {
        if (object instanceof expression.Const) {
            GenericConst t = (GenericConst) object;
            return num == t.num;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(num);
    }
}