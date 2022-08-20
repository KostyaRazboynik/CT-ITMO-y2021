import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.Scanner;
 
public class B {
 
    static String KuznechikNaSpidax(int n, int k, int a[]) {
        String ans;
 
        int mas[] = new int[n + 1];
        int monetki[] = new int[n + 1];
 
        mas[1] = 0;
        int nmax;
        for (int i = 2; i <= n; i++) {
            nmax = i - 1;
            int max;
 
            if (i - k > 1) {
                max = i - k;
            } else {
                max = 1;
            }
 
            for (int j = max; j < i; j++) {
                if (mas[nmax] < mas[j]) {
                    nmax = j;
                }
            }
 
            monetki[i] = nmax;
            mas[i] = mas[nmax] + a[i];
        }
 
        int cnt = 0;
        int num = n;
 
        ArrayDeque<Integer> jumps = new ArrayDeque<>();
 
        jumps.addLast(num);
        while (num > 1) {
            num = monetki[num];
            jumps.add(num);
            cnt++;
        }
 
        ans = mas[n] + "\n" + cnt + "\n";
 
        while (!jumps.isEmpty()) {
            ans += jumps.removeLast() + " ";
        }
 
        return ans;
    }
 
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(new File("input.txt"));
        FileWriter fileWriter = new FileWriter(new File("output.txt"));
 
        int n = scanner.nextInt();
        int k = scanner.nextInt();
        int mas[] = new int[n + 1];
        mas[1] = 0;
        mas[n] = 0;
        for (int i = 2; i < n; i++) {
            mas[i] = scanner.nextInt();
        }
 
        fileWriter.write(KuznechikNaSpidax(n, k, mas));
        fileWriter.flush();
    }
}