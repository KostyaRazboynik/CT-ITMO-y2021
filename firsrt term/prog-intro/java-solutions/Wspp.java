import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Wspp {

    private static final Map<String, ArrayList<Integer>> linkedM = new LinkedHashMap<>();
    private static List<Integer> array = new ArrayList<>();
    private static final StringBuilder curr = new StringBuilder();

    static void addWord(int position) {
        String word = curr.toString();
        if (linkedM.containsKey(word)) {
            array = linkedM.get(word);
            array.set(0, array.get(0) + 1);
        } else {
            array.add(1);
        }
        array.add(position);
        linkedM.put(word, new ArrayList<>(array));
    }
    public static void main(String[] args) throws IOException {
        MyScanner in = new MyScanner(args[0]);
        int position = 1;
        int str = in.read();
        while (str != -1) {
            char symbol = (char) str;
            if (symbol == '\'' || Character.isLetter(symbol) || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
                curr.append(Character.toLowerCase(symbol));
            } else {
                if (!curr.isEmpty()) { //добавляем в мапу слово или меняем занчение
                    addWord(position);
                    ++position;
                }
                curr.setLength(0);
                array.clear();
            }
            str = in.read(); //считали следующую строку
        }
        in.close();
        try (Writer out = new FileWriter((args[1]), StandardCharsets.UTF_8)) {
            for (Map.Entry<String, ArrayList<Integer>> item : linkedM.entrySet()) {   //запись в файл "out"
                String key = item.getKey();
                array = item.getValue();
                out.write(key);
                for (int i: array) {
                    out.write(" " + i);
                }
                out.write("\n");
            }
	    out.close();
            linkedM.clear();
            array.clear();            

        } catch (FileNotFoundException e) {
            System.out.println("File not found" + e.getMessage());
        }
    }
}