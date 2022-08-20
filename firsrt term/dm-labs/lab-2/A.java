import java.util.Arrays;
import java.util.Scanner;

public class A {

    public static long[] arrSort(long mas[]) {
        int maxRadix = 0;
        mas[1] = mas[mas.length - 1];
        String[] stringMas = new String[mas.length - 1];
        for (int i = 0; i < stringMas.length; i++) {
            stringMas[i] = Long.toString(mas[i]);
            maxRadix = Math.max(maxRadix, stringMas[i].length());
        }
        for (int i = 0; i < stringMas.length; i++) {
            StringBuilder curr = new StringBuilder();
            for (int j = 0; j < maxRadix - stringMas[i].length(); j++) {
                curr.append("0");
            }
            curr.append(mas[i]);
            stringMas[i] = curr.toString();
        }
        Arrays.sort(stringMas);
        long[] res = new long[stringMas.length];
        for (int i = 0; i < res.length; i++) {
            res[i] = Long.parseLong(stringMas[i]);
        }
        return res;
    }



    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int maxIndex = 0;
        long count = 0;
        String[] input = new String[n];

        for (int i = 0; i < n; i++) {
            input[i] = Long.toString(sc.nextLong());
            if (input[i].length() > maxIndex) {
                maxIndex = input[i].length();
            }
        }
        for (int i = 0; i < n; i++) {
            StringBuilder curr = new StringBuilder();
            curr.append("0".repeat(Math.max(0, maxIndex - input[i].length())));
            curr.append(input[i]);
            input[i] = curr.toString();
        }
        Arrays.sort(input);
        long[] arr = new long[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Long.parseLong(input[i]);
        }

        while (arr.length != 1) {
            count += arr[0] + arr[1];
            arr[0] += arr[1];
            arr = arrSort(arr);
        }
        System.out.println(count);
    }
}