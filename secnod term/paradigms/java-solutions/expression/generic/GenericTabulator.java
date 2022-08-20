package expression.generic;

import expression.exceptions.ParsingException;

import java.util.Map;

public class GenericTabulator implements Tabulator {

    private final Map<String, OperationMods<?>> modsMap = Map.of(
            "i", new MyInteger(),
            "d", new MyDouble(),
            "bi", new MyBigInteger()
    );

    @Override
    public Object[][][] tabulate(String mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        OperationMods<?> type = modsMap.get(mode);
        if (type != null)
            return calculate(type, expression, x1, x2, y1, y2, z1, z2);

        throw new ParsingException("incorrect type argument");
    }

    private <T> Object[][][] calculate(OperationMods<T> mode, String expression, int x1, int x2, int y1, int y2, int z1, int z2) {
        Object[][][] table = new Object[x2 - x1 + 1][y2 - y1 + 1][z2 - z1 + 1];

        for (int i = x1; i < x2 + 1; i++) {
            for (int j = y1; j < y2 + 1; j++) {
                for (int k = z1; k < z2 + 1; k++) {
                   T x = mode.parsConst(i);
                   T y = mode.parsConst(j);
                   T z = mode.parsConst(k);
                    try {
                        table[i - x1][j - y1][k - z1] = new GenericExpressionParser<T>().parse(expression, mode).evaluate(x, y, z, mode);
                    } catch (Exception ex) {
                        table[i - x1][j - y1][k - z1] = null;
                    }
                }
            }
        }

        return table;
    }
}