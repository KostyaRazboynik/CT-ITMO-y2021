import java.util.Scanner;
public class H {

    public static StringBuilder Sheffer (int n) {
        if (n == 1) {
            StringBuilder shef1 = new StringBuilder();
            return shef1.append("((A0|B0)|(A0|B0))");
        }
        String num = Integer.toString(n-1);
        StringBuilder shef = Sheffer(n-1).insert(0, "((").append("|((A" + num + "|A" + num + ")|(B" + num + "|B" + num + ")))|(A" + num + "|B" + num + "))");
        return shef;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(Sheffer(sc.nextInt()));
    }
}