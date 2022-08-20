package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public class GenericNegate<T> extends GenericUnaryOperations<T> {

    public GenericNegate(GenericExpression<T> first) {
        super(first);
    }

    @Override
    public T completeFun(T x, OperationMods<T> mode) {
        return mode.negate(x);
    }
}