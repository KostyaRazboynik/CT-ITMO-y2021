import java.io.IOException;
import java.util.ArrayList;

public class ReverseAbc2 {

    public static void main(String[] args) throws IOException {
        MyFastScanner sc = new MyFastScanner(System.in);
        ArrayList<ArrayList<String>> input = new ArrayList<>();
        int lines = 0;
        while (sc.hasNext()) {
            String curr = sc.nextLine();
            MyFastScanner line = new MyFastScanner(curr);
            input.add(new ArrayList<>());
            while (line.hasNext()) {
                input.get(lines).add(line.next());
            }
            line.close();
            lines++;
        }
        sc.close();
        for (int i = input.size() - 1; i >= 0; i--) {
            for (int j = input.get(i).size() - 1; j >= 0; j--) {
                String stroka = input.get(i).get(j);
                StringBuilder result = new StringBuilder();
                for (int k = 0; k < stroka.length(); k++) {
                    char el = stroka.charAt(k);
                    if (Character.isLetter(el)) {
                        result.append(el - 'a');
                    } else {
                        result.append(el);
                    }
                }
                System.out.print(result + " ");
            }
            System.out.print('\n');
        }
    }
}