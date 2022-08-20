package expression.generic;

import java.math.BigInteger;

public class MyBigInteger implements OperationMods<BigInteger> {

    @Override
    public BigInteger add(BigInteger x, BigInteger y) {
        return x.add(y);
    }

    @Override
    public BigInteger subtract(BigInteger x, BigInteger y) {
        return x.subtract(y);
    }

    @Override
    public BigInteger multiply(BigInteger x, BigInteger y) {
        return x.multiply(y);
    }

    @Override
    public BigInteger divide(BigInteger x, BigInteger y) {
        return x.divide(y);
    }

    @Override
    public BigInteger negate(BigInteger x) {
        return x.negate();
    }

    @Override
    public BigInteger parsConst(Number x) {
        return new BigInteger(String.valueOf(x));
    }

    @Override
    public BigInteger parsString(String x) {
        return new BigInteger(x);
    }

    @Override
    public BigInteger min(BigInteger x, BigInteger y) {
        return x.min(y);
    }

    @Override
    public BigInteger max(BigInteger x, BigInteger y) {
        return x.max(y);
    }

    @Override
    public BigInteger lZeroes(BigInteger x) {
        return x;
    }

    @Override
    public BigInteger tZeroes(BigInteger x) {
        return x;
    }

    @Override
    public BigInteger count(BigInteger x) {
        return new BigInteger(Integer.toString(x.bitCount()));
    }
}