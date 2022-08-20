import java.util.Arrays;
import java.util.Scanner;

public class I {

    static void DecodeAndFix(String code) {
        StringBuilder result = new StringBuilder();
        int[] noProblem = new int[code.length()];
        for (int i = 0; i < code.length(); i++) {
            noProblem[i] = code.charAt(i) - '0';
        }
        int bitCount = (int) Math.ceil(Math.log10(code.length()) / Math.log10(2));
        int error = 0;
        for (int i = 0; i < bitCount; i++) {
            int sum = 0;
            for (int j = (int) Math.pow(2, i); j < code.length(); j++) {
                String sJ = Integer.toBinaryString(j + 1);
                if (sJ.charAt(sJ.length() - (i + 1)) == '1') {
                    sum += code.charAt(j);
                }
            }
            if (sum % 2 != code.charAt(((int) Math.pow(2, i)) - 1) - '0') {
                error += Math.pow(2, i);
            }
        }
        if (error != 0) {
            if (noProblem[error - 1] == 1) {
                noProblem[error - 1] = 0;
            } else {
                noProblem[error - 1] = 1;
            }
        }
        int j = 0;

        for (int i = 0; i < noProblem.length; i++) {
            if (i + 1 != (int) Math.pow(2, j)) {
                result.append(noProblem[i]);
            } else {
                j++;
            }
        }
        System.out.println(result);
    }

    static void Encode (String code) {
        int n = code.length();
        StringBuilder result = new StringBuilder();
        int bitCount = (int) Math.ceil(Math.log10(n + 1 + Math.ceil(Math.log10(n + 1) / Math.log10(2)))/ Math.log10(2));
        int[] res = new int[n + bitCount];
        int j = 0;
        int t = 0;
        for (int i = 0; i < res.length; i++) {
            if (i + 1 == Math.pow(2, j)) {
                res[i] = 2;
                j++;
            } else {
                res[i] = code.charAt(t++) - '0';
            }
        }
        j = 0;
        for (int i = 0; i < res.length; i++) {
            if (res[i] == 2) {
                int sum = 0;
                for (int k = i + 1; k < res.length; k++) {
                    String sK = Integer.toBinaryString(k + 1);
                    if (sK.charAt(sK.length() - (j + 1)) == '1') {
                        sum += res[k];
                    }
                }
                res[i] = sum % 2;
                j++;
            }
        }
        for (int i = 0; i < res.length; i++) {
            result.append(res[i]);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String num = scan.nextLine();
        String code = scan.nextLine();
        if (num.equals("1")) {
            Encode(code);
        } else {
            DecodeAndFix(code);
        }
    }
}