import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class WsppPosition {

    private static final Map<String, List<String>> linkedM = new LinkedHashMap<>();
    private static List<String> array = new ArrayList<>();
    private static final StringBuilder curr = new StringBuilder();

    static void addWord(int position, int strokaNum) {
        String word = curr.toString();
        if (linkedM.containsKey(word)) {
            array = linkedM.get(word);
            int kolvo = Integer.parseInt(array.get(0));
            kolvo++;
            array.set(0, Integer.toString(kolvo));
        } else {
            array.add("1");
        }
        array.add(Integer.toString(strokaNum) + ":" + Integer.toString(position));
        linkedM.put(word, new ArrayList<>(array));
    }
    public static void main(String[] args) throws IOException {
        MyScanner in = new MyScanner(args[0]);
        int position = 1; // каунтер позиции
        int strokaNum = 1; // каунтер строки
        int str = in.read();
        while (str != -1) {
            char symbol = (char) str;
            if (symbol == '\'' || Character.isLetter(symbol) || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
                curr.append(Character.toLowerCase(symbol));
            } else {
                if (!curr.isEmpty()) {
                    addWord(position, strokaNum);
                    ++position;
                }
                curr.setLength(0);
                array.clear();
            }
            if (symbol == '\n') {
                strokaNum++;
                position = 1; // на каждой новой строке позаицтя
            }
            str = in.read(); //считали следующую строку
        }
        in.close();


        try (BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8))) {
            for (Map.Entry<String, List<String>> item : linkedM.entrySet()) {   //запись в файл "out"
                String key = item.getKey();
                out.write(key);
                for (String i: item.getValue()) {
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
