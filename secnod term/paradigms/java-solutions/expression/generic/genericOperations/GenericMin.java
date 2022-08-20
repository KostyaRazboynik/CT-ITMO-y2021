package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public class GenericMin<T> extends GenericBinaryOperation<T> {

    public GenericMin(GenericExpression first, GenericExpression second) {
        super(first, second);
    }

    @Override
    public T completeFun(T x, T y, OperationMods<T> mode) {
        return mode.min(x, y);
    }
}