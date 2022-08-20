import java.io.IOException;
import java.util.ArrayList;

public class Reverse {
    public static void main(String[] args) throws IOException{

        MyFastScanner sc = new MyFastScanner(System.in);
        ArrayList<ArrayList<Integer>> nums = new ArrayList<>();
        int k = -1;

        while (sc.hasNext()) {
            k++;
            String curr = sc.nextLine();
            nums.add(new ArrayList<>());
            MyFastScanner line = new MyFastScanner(curr);
            while(line.hasNext()) {
                String word = line.next();
                if (!Character.isWhitespace(word.charAt(0)))
                    nums.get(k).add(Integer.parseInt(word));
            }
            line.close();
        }
        sc.close();

        for (int i = k; i >= 0; i--) {
            if (nums.get(i).size() > 0) {
                for (int j = nums.get(i).size() - 1; j >= 0; j--)
                    System.out.print(nums.get(i).get(j) + " ");
            }
            System.out.print('\n');
        }
    }
}