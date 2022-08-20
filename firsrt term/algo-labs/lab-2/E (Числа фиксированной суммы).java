import java.util.Arrays;
import java.util.Scanner;

public class E {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int k = scan.nextInt();
        int s = scan.nextInt();
        int[] mas = new int[k * 9 + 1];
        Arrays.fill(mas,0,10,1);

        for (int i = 1; i < k; i++) {
            int[] ChtoProishoditYaXz = Arrays.copyOf(mas,k * 9 +1);

            for (int j = 1; j < s + 1; j++) {
                ChtoProishoditYaXz[j]= (ChtoProishoditYaXz[j] +mas[j - 1]) % (1000000007);
                for (int zopa = 1; zopa < 9; zopa++) {

                    if (j > zopa) {
                        ChtoProishoditYaXz[j] = (ChtoProishoditYaXz[j] + mas[j - zopa - 1]) % (1000000007);
                        continue;
                    }
                    break;
                }
            }
            mas = Arrays.copyOf(ChtoProishoditYaXz,k * 9 + 1);
        }
        System.out.println(mas[s]);
    }
}