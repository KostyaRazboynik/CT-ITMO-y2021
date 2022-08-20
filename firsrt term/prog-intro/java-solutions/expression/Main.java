package expression;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int example = new Add(
                new Subtract(new Multiply(new Variable("x"), new Variable("x")), new Multiply(new Const(2), new Variable("x"))),
                new Const(1)
        ).evaluate(sc.nextInt());

        System.out.println(example);
    }
}