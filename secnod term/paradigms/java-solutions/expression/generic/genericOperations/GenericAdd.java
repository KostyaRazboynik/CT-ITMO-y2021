package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public class GenericAdd<T> extends GenericBinaryOperation<T> {

    public GenericAdd(GenericExpression<T> first, GenericExpression<T> second) {
        super(first, second);
    }

    @Override
    public T completeFun(T x, T y, OperationMods<T> mode) {
        return mode.add(x, y);
    }
}