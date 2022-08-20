import java.util.*;


public class G {


    public static void main(String[] args) throws NumberFormatException {

        int impos = 0; // чеккер

        int is_null = 0;
        int pos = 0;
        int is_one = 0;
        int chek = 0;

        List<String> formula = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int kolvo = Integer.parseInt(scan.nextLine());
        int vectors[][] = new int[kolvo][33];
        Scanner sc = new Scanner(scan.nextLine());

        // записали вектора в 33 бита входных чисел
        for (int i = 0; i < kolvo; i++) {
            long num = Long.parseLong(sc.next()); //записали колличество переменных
            if (num == 0) {
                is_null++;
                pos =  i + 1;
            }
            StringBuilder tek = new StringBuilder(Long.toBinaryString(num));
            while (tek.length() < 33) {
                tek.insert(0, "0");
            }
            for (int j = 0; j < 33; j++) {
                vectors[i][j] = tek.charAt(j) - '0';
            }
        }

        // записали "ответ", который хотим получить
        long itog = Long.parseLong(scan.nextLine());
        if (itog == 1) {
            is_one++;
        }
        int[] itog_vector = new int[33];
        StringBuilder itog_tek = new StringBuilder(Long.toBinaryString(itog));
        while (itog_tek.length() < 33) {
            itog_tek.insert(0, "0");
        }
        for (int j = 0; j < 33; j++) {
            itog_vector[j] = itog_tek.charAt(j) - '0';
        }

        // проверка на 0 -- 1
        if (is_null > 0 && is_one > 0) {
            chek++;
            System.out.println("~" + pos);
        }
        // проверка на Impossible
        if (chek == 0) {
            for (int j = 0; j < 33; j++) {
                StringBuilder stolb = new StringBuilder();
                for (int i = 0; i < kolvo; i++) {
                    stolb.append(Integer.toString(vectors[i][j]));
                }
                for (int l = 0; l < 33; l++) {
                    StringBuilder stolb_sled = new StringBuilder();
                    for (int k = 0; k < kolvo; k++) {
                        stolb_sled.append(Integer.toString(vectors[k][l]));
                    }
                    if (stolb.toString().equals(stolb_sled.toString()) && itog_vector[j] != itog_vector[l]) {
                        impos++;
                        break;
                    }
                }
                if (impos > 0) {
                    break;
                }
            }
        }

        // если не Impossible
        if (chek == 0 && impos == 0) {
            // составляем формулу
            for (int j = 0; j < 33; j++) {
                if (itog_vector[j] == 1) {
                    StringBuilder con = new StringBuilder("(");
                    for (int i = 0; i < kolvo; i++) {
                        if (i != 0) {
                            con.append("&");
                        }
                        if (vectors[i][j] == 0) {
                            con.append("~");
                        }
                        con.append(Integer.toString(i + 1));
                    }
                    con.append(")");
                    formula.add(con.toString());
                }
            }
            if (formula.size() == 0) {
                System.out.println("1&(~1)");
            } else {
                if (formula.size() == 1) {
                    System.out.println(formula.get(0).substring(1, formula.get(0).length() - 1));
                } else {
                    for (int i = 0; i < formula.size() - 1; i++) {
                        System.out.print(formula.get(i) + "|");
                    }
                    System.out.print(formula.get(formula.size() - 1));
                }
            }
        } else if (chek == 0) {
            System.out.println("Impossible");
        }
    }
}