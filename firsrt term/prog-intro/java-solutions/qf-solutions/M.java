import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class M {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        int n, result;
        int[] a;
        Map<Integer, Integer> C = new HashMap<>();
        for (int i = 0; i < t; i++){
            n = in.nextInt();
            result = 0;
            a = new int[n];
            for (int j = 0; j < n; j++){
                a[j] = in.nextInt();
            }
            for (int j = 1; j < n - 1; j++){
                for (int k = j - 1; k >= 0; k--){
                    if (C.containsKey(a[j] - a[k])){
                        C.put(a[j] - a[k], C.get(a[j] - a[k]) + 1);
                    } else {
                        C.put(a[j] - a[k], 1);
                    }
                }
                for (int k = j + 1; k < n; k++){
                    if (C.containsKey(a[k] - a[j])){
                        result += C.get(a[k] - a[j]);
                    }
                }
                C.clear();
            }
            System.out.println(result);
        }
    }
}