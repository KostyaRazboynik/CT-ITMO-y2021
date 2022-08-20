import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class E {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        String str = scan.nextLine();
        Map<String, Integer>  alf = new HashMap<>();
        String symbols = "abcdefghijklmnopqrstuvwxyz";
        for (int i = 0; i < symbols.length(); i++) {
            alf.put(Character.toString(symbols.charAt(i)) , i);
        }
        int k = 26;
        String w = "";
        for (char c : str.toCharArray()) {
            String wc = w + c;
            if (alf.containsKey(wc)) {
                w = wc;
            } else {
                System.out.print(alf.get(w) + " ");
                alf.put(wc, k++);
                w = "" + c;
            }
        }
        if (!w.equals(""))
            System.out.print(alf.get(w) + " ");
    }
}