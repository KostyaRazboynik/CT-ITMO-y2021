package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public abstract class GenericUnaryOperations<T> implements GenericExpression<T> {

    private final GenericExpression<T> first;

    public GenericUnaryOperations(GenericExpression<T> first) {
        this.first = first;
    }

    protected abstract T completeFun(T x, OperationMods<T> mode);

    @Override
    public T evaluate(T x, T y, T z, OperationMods<T> mode) {
        return completeFun(first.evaluate(x, y, z, mode), mode);
    }
}