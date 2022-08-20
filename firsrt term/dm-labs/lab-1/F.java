import java.util.Scanner;
import java.util.Arrays;
public class F {

    public static String horn(int[][] mas, int n, int k) { //n - колличесвто литералов, k - колличсевто дизъюнктов

        for (int g = 0; g < k; g++) {
            for (int i = 0; i < k; i++) {
                int is_null = 0;
                int ne_null = 0;
                int ne_null_index = 0;
                for (int j = 0; j < n; j++) { // проверяю строку на наличие "-1", если не "-1", то запоминаю индекс и значение
                    if (mas[i][j] == -1) {
                        ++is_null;
                    } else {
                        ne_null_index = j;
                        ne_null = mas[i][j];
                    }
                }
                // если строка содержит только одну не "-1", меняем все значнеия во всех строках под этим индексом
                if (is_null == n - 1) {
                    for (int j = 0; j < k; j++) {
                        if (ne_null == 1) {
                            if (mas[j][ne_null_index] != 1) {
                                mas[j][ne_null_index] = -1;
                            }
                        } else if (ne_null == 0) {
                            if (mas[j][ne_null_index] != 0) {
                                mas[j][ne_null_index] = -1;
                            }
                        }
                    }
                    // после замены ищем строку, у которой все -1 (она равна нулю, получается)
                    for (int j = 0; j < k; j++) {
                        int kolvo_null = 0;
                        for (int l = 0; l < n; l++) {
                            if (mas[j][l] == -1) {
                                ++kolvo_null;
                            } else {
                                break;
                            }
                        }
                        if (kolvo_null == n) {
                            return "YES";
                        }
                    }
                }
            }
        }
        return "NO";
    }

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        //считали первую строку
        String str = scanner.nextLine();
        Scanner sc = new Scanner(str);
        int sdvig = 0;
        int stroka = 0;
        int n = sc.nextInt(); //колличество литералов
        int k = sc.nextInt(); //колличество дизъюнктивов
        int[][] vvod_mas = new int[k][n]; // каждая строка - дизъюнктив
        //заполнили двумерный массив
        for (int i = 0; i < k; i++) {
            String disunkt = scanner.nextLine();
            Scanner scan_pro = new Scanner(disunkt);
            Scanner scan = new Scanner(disunkt);
            int count_minus_one = 0;
            for (int j = 0; j < n; j++) {
                if (scan_pro.nextInt() == -1) {
                    count_minus_one++;
                }
            }
            if (count_minus_one != n) {
                for (int j = 0; j < n; j++) {
                    vvod_mas[stroka][j] = scan.nextInt();
                }
                stroka++;
            } else {
                sdvig++;
            }
        }
        k -= sdvig;
        int[][] mas = new int[k][n];
        for (int i = 0; i < k; i++) {
            mas[i] = vvod_mas[i];
        }
        System.out.println(horn(mas, n, k));
    }
}