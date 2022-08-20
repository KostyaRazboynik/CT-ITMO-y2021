package md2html;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class Md2Html {
    public static void main(String[] args) {
        StringBuilder result = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(new File(args[0])), StandardCharsets.UTF_8))) {
            String s = "";
            StringBuilder line = new StringBuilder();
            while (s != null && (s = reader.readLine()) != null) {
                while (s != null && !s.equals("")) {
                    line.append(s).append('\n');
                    s = reader.readLine();
                }
                if (line.length() != 0) {
                    line.setLength(line.length() - 1);
                    new FirstStageParser(line).toHtml(result);
                    result.append('\n');
                    line = new StringBuilder();
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException" + e.getMessage());
        }

        try (Writer writer = new FileWriter((args[1]), StandardCharsets.UTF_8)) {
            writer.write(result.toString());
        } catch (FileNotFoundException e) {
            System.out.println("Output file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}