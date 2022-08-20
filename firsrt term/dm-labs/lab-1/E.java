import java.util.Scanner;
import java.util.Arrays;
public class E {
    public static int pow(int value, int powValue) {
        return (int) Math.pow(value, powValue);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int a = pow(2, n);
        int[][] arr = new int[a][n];
        int[] f = new int[a];
        int[] polinom = new int[a];
        // Заполняем два массива
        for (int i = 0; i < a; i++) {
            String str = sc.nextLine();
            int flag = 0;
            for (int j = 0; j < str.length(); j++) {
                if (str.charAt(j) == ' ') {
                    flag++;
                }
                if ((str.charAt(j) == '0' || str.charAt(j) == '1') && flag == 0) {
                    int k = str.charAt(j)-'0';
                    arr[i][j] = k;
                }
                if ((str.charAt(j) == '0' || str.charAt(j) == '1') && flag > 0) {
                    int k = str.charAt(j)-'0';
                    f[i] = k;
                }
            }
        }
        polinom[0] = f[0];
        int count = 1;
        while (count < a) {
            int[] tek = new int[f.length - 1];
            for (int i = 0; i < f.length - 1; i++) {
                tek[i] = (f[i] + f[i + 1]) % 2;
            }
            f = Arrays.copyOf(tek, tek.length);
            polinom[count] = f[0];
            count++;
        }


        //Вывод операндов
        for (int i = 0; i < a; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println(" " + polinom[i]);
        }
    }
}