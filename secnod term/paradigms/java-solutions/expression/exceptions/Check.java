package expression.exceptions;

import expression.ExpressionFunc;

public class Check {
    public static void main(String[] args) throws Exception {
        TripleParser p = new ExpressionParser();
        ExpressionFunc m = (ExpressionFunc) p.parse("(90 / 0)");
        System.out.println(m.toString());
        System.out.println(m.evaluate(4, 4, 4));
    }
}