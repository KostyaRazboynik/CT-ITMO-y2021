package expression.generic;

import expression.exceptions.DivizionByZeroException;
import expression.exceptions.OverflowException;

public class MyInteger implements OperationMods<Integer> {


    @Override
    public Integer add(Integer x, Integer y) {
        if (x >= 0) {
            if (Integer.MAX_VALUE - x < y) throw new OverflowException(x, y, "+");
        } else {
            if (Integer.MIN_VALUE - x > y) throw new OverflowException(x, y, "+");
        }

        return x + y;
    }

    @Override
    public Integer subtract(Integer x, Integer y) {
        if ((x > 0 && y < x - Integer.MAX_VALUE) || (x < 0 && y > x - Integer.MIN_VALUE) || (x == 0 && y == Integer.MIN_VALUE)) {
            throw new OverflowException(x, y, "-");
        }

        return x - y;
    }

    @Override
    public Integer multiply(Integer x, Integer y) {
        if (x != 0 && y != 0 && ((x * y) / y != x || (x * y) / x != y)) {
            throw new OverflowException(x, y, "*");
        }

        return x * y;
    }

    @Override
    public Integer divide(Integer x, Integer y) {
        if (y == 0) {
            throw new DivizionByZeroException(x, y, "/");
        }

        if ((x == Integer.MIN_VALUE) && (y == -1)) {
            throw new OverflowException(x, y, "/");
        }

        return x / y;
    }

    @Override
    public Integer negate(Integer x) {
        if (x == Integer.MIN_VALUE) {
            throw new OverflowException(x);
        }
        return -x;
    }

    @Override
    public Integer parsConst(Number one) {
        return one.intValue();
    }

    @Override
    public Integer parsString(String one) {
        return Integer.parseInt(one);
    }

    @Override
    public Integer min(Integer x, Integer y) {
        return Integer.min(x, y);
    }

    @Override
    public Integer max(Integer x, Integer y) {
        return Integer.max(x, y);
    }

    @Override
    public Integer lZeroes(Integer x) {
        return Integer.numberOfLeadingZeros(x);
    }

    @Override
    public Integer tZeroes(Integer x) {
        return Integer.numberOfTrailingZeros(x);
    }

    @Override
    public Integer count(Integer x) {
        return Integer.bitCount(x);
    }
}