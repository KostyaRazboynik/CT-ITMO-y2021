import java.util.Scanner;
import java.util.Arrays;

public class J {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        String[] mas = new String[n];
        for (int i = 0; i < n; i++) {
            mas[i] = sc.nextLine();
        }
        Arrays.sort(mas);
        for (int i = 0; i < n; i++) {
            String curr = mas[i];
            int lastIndex = curr.lastIndexOf('/');
            int count = 0;
            for (int j = 0; j < curr.length(); j++) {
                if (curr.charAt(j) == '/') {
                    count++;
                }
            }
            if (lastIndex >= 0) {
                System.out.println(" ".repeat(count * 2) + curr.substring(lastIndex + 1));
            } else {
                System.out.println(curr);
            }
        }
    }
}