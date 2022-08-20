package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public abstract class GenericBinaryOperation<T> implements GenericExpression<T> {

    private final GenericExpression<T> first;
    private final GenericExpression<T> second;

    public GenericBinaryOperation(GenericExpression<T> first, GenericExpression<T> second) {
        this.first = first;
        this.second = second;
    }

    protected abstract T completeFun(T x, T y, OperationMods<T> mode);

    @Override
    public T evaluate(T x, T y, T z, OperationMods<T> mode) {
        return completeFun(first.evaluate(x, y, z, mode), second.evaluate(x, y, z, mode), mode);
    }
}