package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public class GenericSubtract<T> extends GenericBinaryOperation<T> {

    public GenericSubtract(GenericExpression<T> first, GenericExpression<T> second) {
        super(first, second);
    }

    @Override
    public T completeFun(T x, T y, OperationMods<T> mode) {
        return mode.subtract(x, y);
    }
}