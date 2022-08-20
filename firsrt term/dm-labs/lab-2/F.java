import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.HashMap;

public class F {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int n = Integer.parseInt(scan.nextLine());
        List<Integer> mas = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            int a = scan.nextInt();
            mas.add(a);
        }
        Map<Integer, String>  alf = new HashMap<>();
        char[] symbols = new char[] {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
        int j = 0;
        for (char c: symbols)  {
            alf.put(j, "" + c);
            j++;
        }
        int k = 26;
        String w = "";
        StringBuilder result = new StringBuilder("");
        for (int el : mas) {
            String entry = "";
            if (alf.containsKey(el)) {
                entry = alf.get(el);
            } else if (el == k) {
                entry = w + w.charAt(0);
            }
            if (!alf.containsValue(w + entry.charAt(0))) {
                alf.put(k++, w + entry.charAt(0));
            }

            w = entry;
        }
        for (int el : mas) {
            result.append(alf.get(el));
        }
        System.out.println(result);
    }
}