package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public class GenericCount<T> extends GenericUnaryOperations<T> {

    public GenericCount(GenericExpression first) {
        super(first);
    }

    @Override
    public T completeFun(T x, OperationMods<T> mode) {
        return mode.count(x);
    }
}