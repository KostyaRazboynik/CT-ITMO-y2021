import java.util.Scanner;
public class A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        Scanner sc = new Scanner(str);
        float a = sc.nextFloat();
        float b = sc.nextFloat();
        float n = sc.nextFloat();
        System.out.println(2 * Math.round(Math.ceil((n - b)/(b - a))) + 1);
    }
}