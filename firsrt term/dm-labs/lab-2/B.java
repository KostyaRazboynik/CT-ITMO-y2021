import java.util.Scanner;
import java.util.Arrays;

public class B {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        int n = str.length();
        String[] mas = new String[n];
        for (int i = 0; i < n; i++) {
            String curr = str;
            mas[i] = curr.substring(n - 1 - i) + curr.substring(0, n - 1 - i);
        }
        Arrays.sort(mas);
        StringBuilder res = new StringBuilder("");
        for (int i = 0; i < n; i++) {
            res.append(mas[i].charAt(n - 1));
        }
        System.out.println(res);
    }
}