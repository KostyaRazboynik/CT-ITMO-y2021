package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;


public class GenericMultiply<T> extends GenericBinaryOperation<T> {

    public GenericMultiply(GenericExpression<T> first, GenericExpression<T> second) {
        super(first, second);
    }

    @Override
    public T completeFun(T x, T y, OperationMods<T> mode) {
        return mode.multiply(x, y);
    }
}