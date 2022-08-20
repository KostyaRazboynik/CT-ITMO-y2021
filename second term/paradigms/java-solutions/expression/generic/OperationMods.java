package expression.generic;

public interface OperationMods<T> {

    T add(T x, T y);
    T subtract(T x, T y);
    T multiply(T x, T y);
    T divide(T x, T y);
    T negate(T x);
    T min(T x, T y);
    T max(T x, T y);
    T lZeroes(T x);
    T tZeroes(T x);
    T parsConst(Number one);
    T parsString(String one);
    T count(T x);
}