import java.util.Scanner;

public class D {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        int[] nums = new int[26];
        for (int i = 0; i < 26; i++) {
            nums[i] = i + 1;
        }
        char[] alf = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            for (int j = 0; j < 26; j++) {
                if (c == alf[j]) {
                    System.out.print(nums[j] + " ");
                    for (int k = j; k > 0; k--) {
                        alf[k] = alf[k - 1];
                    }
                    alf[0] = c;
                    break;
                }
            }
        }
    }
}