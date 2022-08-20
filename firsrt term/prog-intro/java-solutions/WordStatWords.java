import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.TreeMap;

public class WordStatWords {
    static void addWord(StringBuilder stringBuilder, Map<String, Integer> treeM) { // возвращет значение с ключом stringBuilder и прибавляет единицу
        int prev = treeM.getOrDefault(stringBuilder.toString(), 0);
        treeM.put(stringBuilder.toString(), ++prev);
    }
    public static void main(String[] args) {
        try {
            StringBuilder curr = new StringBuilder();
            Map<String, Integer> dict = new TreeMap<>();
            try (BufferedReader in = new BufferedReader(new FileReader(args[0], StandardCharsets.UTF_8));
                BufferedWriter out = new BufferedWriter(new FileWriter(args[1], StandardCharsets.UTF_8))) {
                int str = in.read(); //считали строку
                while (str != -1) {
                    char symbol = (char) str;
                    if (symbol == '\'' || Character.isLetter(symbol) || Character.getType(symbol) == Character.DASH_PUNCTUATION) {
                        curr.append(Character.toLowerCase(symbol));
                    } else {
                        if (!curr.isEmpty()) { //добавляем в мапу слово или меняем занчение
                            addWord(curr, dict);
                        }
                        curr.setLength(0);
                    }
                    str = in.read(); //считали следующую строку
                }
                in.close();
                if (!curr.isEmpty()) {
                    addWord(curr, dict);
                }

                for (Map.Entry<String, Integer> item : dict.entrySet()) { //запись в файл "out"
                    out.write(item.getKey() + " " + item.getValue());
                    out.newLine();
                }
                out.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found" + e.getMessage());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong" + e.getMessage());
        }
    }
}