import java.util.Arrays;
import java.util.Scanner;
public class L {
    static int binSearch(int[] mas, int key) {
        int left = -1;
        int right = mas.length;
        while (left + 1 < right) {
            int middle = (left + right) / 2;

            if (mas[middle] <= key)
                left = middle;
            else
                right = middle;
        }
        return left;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] mas = new int[n];
        for (int i = 0; i < n; i++) {
            mas[i] = sc.nextInt();
        }
        Arrays.sort(mas);
        int k = sc.nextInt();
        for (int i = 0; i < k; i++) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int count = binSearch(mas, r) - binSearch(mas, l - 1);
            System.out.println(count);
        }
    }
}