package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public class GenericLZeroes<T> extends GenericUnaryOperations<T> {

    public GenericLZeroes(GenericExpression first) {
        super(first);
    }

    @Override
    public T completeFun(T x, OperationMods<T> mode) {
        return mode.lZeroes(x);
    }
}