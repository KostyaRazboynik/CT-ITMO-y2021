import java.util.Scanner;
public class F {
 
    public static void main(String[] args) {
        int count = 0;
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] mas = new int[n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            String str = sc.nextLine();
            if (!str.equals("1")) {
                mas[j++] = Integer.parseInt(str.substring(2));
            } else {
                int localMax = 0;
                int indexMax = -1;
                for (int k = 0; k < i; k++) {
                    if (mas[k] > localMax) {
                        localMax = mas[k];
                        indexMax = k;
                    }
                }
                System.out.println(localMax);
                mas[indexMax] = -1;
            }
        }
    }
}