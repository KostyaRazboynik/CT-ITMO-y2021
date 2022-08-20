import java.util.Scanner;
public class E {
    public static void isHeap(int[] mas, int n) {
        int flag = 0;
        for (int i = 0; i < mas.length; i++) {
            if ((2 * i + 1) < n) {
                if (mas[i] > mas[2 * i + 1]) {
                    flag++;
                }
            } else if ((2 * i + 2) < n) {
                if (mas[i] > mas[2 * i + 2]) {
                    flag++;
                }
            }
        }
        if (flag == 0) {
            System.out.println("YES");
        } else {
            System.out.println("NO");
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] mas = new int[n];
        for (int i = 0; i < n; i++) {
            mas[i] = sc.nextInt();
        }
        isHeap(mas, n);
    }
}