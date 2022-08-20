import java.util.*;


public class B {

    public static int xor (int a, char b) {
        if ((a == 0 && b == '1') || (a == 1 && b == '0')) {
            return 1;
        } else {
            return 0;
        }
    }


    public static boolean major (int[][] table, int a, int b, int num) {
        for (int i = 0; i < num ; i++) {
            if (table[a][i] < table[b][i]) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {

        int save0 = 0;
        int save1 = 0;
        int lin = 0;
        int selfdual = 0;
        int mono = 0;

        int flag = 0;

        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        for (int k = 0; k < n; k++) {
            Scanner sc = new Scanner(scan.nextLine()); //считали строку
            int kolvo = Integer.parseInt(sc.next()); //записали колличество переменных
            String f = sc.next(); // записали ззначение функции

            //Сохранение 0
            if (save0 == 0) {
                if (f.length() == 1 && f.equals("1")) {
                    save0++;
                }
                if (f.charAt(0) == '1') {
                    save0++;
                }
            }


            //Сохранение 1
            if (save1 == 0) {
                if (f.length() == 1 && f.equals("0")) {
                    save1++;
                }
                if (f.charAt(f.length() - 1) == '0') {
                    save1++;
                }
            }


            //Монотонность
            if (mono == 0) {
                // заполняю массив векторов
                int[][] vectors = new int[f.length()][kolvo];
                for (int i = 1; i < f.length(); i++) {
                    StringBuilder tek = new StringBuilder(Integer.toBinaryString(i));
                    while (tek.length() < kolvo) {
                        tek.insert(0, "0");
                    }
                    for (int j = 0; j < kolvo; j++) {
                        vectors[i][j] = tek.charAt(j) - '0';
                    }

                    // если вектор префиксно мажжорирует над другим вектором, а значение функции у него мменьше, то функция не монотонна
                    for (int j = 0; j < i; j++) {
                        if (major(vectors, i, j, kolvo)) {
                            if (f.charAt(i) < f.charAt(j)) {
                                mono++;
                                break;
                            }
                        }
                    }
                    if (mono > 0) {
                        break;
                    }
                }
            }


            //Самодвойственность
            if (selfdual == 0) {
                StringBuilder revers = new StringBuilder(f);
                revers.reverse();
                for (int i = 0; i < f.length(); i++) {
                    if (f.charAt(i) == revers.charAt(i)) {
                        selfdual++;
                        break;
                    }
                }
            }


            //Линейность
            if (lin == 0 && f.length() != 1) {

                int[] polinom = new int[f.length()];
                int[] b = new int[f.length()];
                for (int i = 0; i < f.length(); i++) {
                    b[i] = f.charAt(i) - '0';
                }
                polinom[0] = b[0];
                for (int i = 1; i < f.length(); i++) {
                    int[] a = new int[f.length() - i];
                    for (int j = 0; j < b.length - 1; j++) {
                        a[j] = (b[j] + b[j + 1]) % 2;
                    }
                    polinom[i] = a[0];
                    b = Arrays.copyOf(a, a.length);
                }
                int x = 1;
                for (int i = 1; i < f.length(); i++) {
                    if (polinom[i] == 1 && i != x) {
                        lin++;
                        break;
                    } else if (i == x) {
                        x *= 2;
                    }
                }
            }
            if (lin > 0 && mono > 0 && selfdual > 0 && save1 > 0 && save0 > 0) {
                System.out.print("YES");
                flag++;
                break;
            }
        }

        if (flag == 0) {
            System.out.print("NO");
        }
    }
}