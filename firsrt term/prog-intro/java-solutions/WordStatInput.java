import java.util.*;
import java.io.*;

public class WordStatInput {
    public static void main(String[] args) {
        String[] mas = new String[1000];
        int[] num = new int[1000];
        int k = 0;
        try{
            FileInputStream fis =  new FileInputStream(args[0]);
            BufferedReader br = new BufferedReader(new InputStreamReader(fis, "utf-8"));
            String s;
            while ((s = br.readLine()) != null){
                StringBuilder tek = new StringBuilder();
                s=s.toLowerCase() + "  ";
                for (int i = 0; i<s.length()-1; i++){
                    if (Character.isLetter(s.charAt(i)) || (s.charAt(i) == '\'') || (Character.getType(s.charAt(i)) == Character.DASH_PUNCTUATION)) {
                        tek.append(s.charAt(i));
                    }else{
                        if (Arrays.asList(mas).contains(tek.toString()) && !tek.isEmpty()){
                            num[Arrays.asList(mas).indexOf(tek.toString())]++;
                        }else if (!tek.isEmpty()){
                            num[k]++;
                            mas[k++] = tek.toString();
                        }
                        tek = new StringBuilder();
                    }
                }
            }
            br.close();

        } catch (IOException ex) {
            System.out.print("Error" + ex.getMessage());
        }

        try{
            File out = new File(args[1]);
            PrintWriter pw = new PrintWriter(out, "utf-8");

            for (int i = 0; i<k; i++){
                pw.println(mas[i]+" "+num[i]);
            }

            pw.close();

        }catch (IOException ex){
            System.out.print("Error "+ex);
        }
    }
}