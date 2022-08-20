import java.util.Scanner;
public class B {
    public static void CountingSort(int[] mas) {
        int[] count = new int[101];
        for (int i = 0; i < mas.length; i++){
            count[mas[i]]++;
        }
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) {
                    System.out.print(i + " ");
                }
            }
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] mas = new int[n];
        for (int i = 0; i < n; i++) {
            mas[i] = sc.nextInt();
        }
        CountingSort(mas);
    }
}