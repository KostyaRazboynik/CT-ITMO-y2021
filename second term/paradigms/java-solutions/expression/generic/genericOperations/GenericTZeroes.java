package expression.generic.genericOperations;

import expression.generic.OperationMods;
import expression.generic.GenericExpression;

public class GenericTZeroes<T> extends GenericUnaryOperations<T> {

    public GenericTZeroes(GenericExpression first) {
        super(first);
    }

    @Override
    public T completeFun(T x, OperationMods<T> mode) {
        return mode.tZeroes(x);
    }
}