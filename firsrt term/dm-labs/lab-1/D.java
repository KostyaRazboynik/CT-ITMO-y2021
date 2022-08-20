import java.util.*;


public class D {

    public static int pow(int value, int powValue) {
        return (int) Math.pow(value, powValue);
    }



    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int a = pow(2, n);
        int[][] vectors = new int[a][n];
        int[] f = new int[a];
        int count_one = 0;
        List<String> array = new ArrayList<>();
        for (int i = 0; i < a; i++) {
            Scanner sc = new Scanner(scan.nextLine());
            String vek = sc.next();
            int b = sc.nextInt();
            if (b == 1) {
                count_one++;
            }
            f[i] = b;
            for (int j = 0; j < n; j++) {
                vectors[i][j] = vek.charAt(j) - '0';
            }
        }

        int m = n; // изначально m = кол-во элментов (n)


         //строим СДНФ (для единиц)
        if (count_one > 0) {
            int pred_deystvie = 0;
            int flag = 0;
            for (int i = 0; i < f.length; i++) {
                if (f[i] == 1) {
                    int[] otriz = new int[n];
                    for (int j = 0; j < n; j++) { //записывем все отрицания
                        if (vectors[i][j] == 0) {
                            m++;
                            array.add("1 " + Integer.toString(j + 1));
                            otriz[j] = m;
                        }
                    }
                    int flag_str = 0;
                    if (flag == 0) { //Первая скобка
                        for (int j = 0; j < n - 1; j++) {
                            if (flag_str == 0) { //Первые два элемента
                                if (vectors[i][j] == 0) {
                                    if (vectors[i][j + 1] == 0) { //прописать сумму, прописать первое произведения, без учета m
                                        array.add("2 " + Integer.toString(otriz[j]) + " " + Integer.toString(otriz[j + 1]));
                                    } else {
                                        array.add("2 " + Integer.toString(otriz[j]) + " " + Integer.toString(j + 2));
                                    }
                                    m++;
                                    flag_str++;
                                } else {
                                    if (vectors[i][j + 1] == 0) { //прописать сумму, прописать первое произведения, без учета m
                                        array.add("2 " + Integer.toString(j + 1) + " " + Integer.toString(otriz[j + 1]));
                                    } else {
                                        array.add("2 " + Integer.toString(j + 1) + " " + Integer.toString(j + 2));
                                    }
                                    m++;
                                    flag_str++;
                                }
                            } else { // для всех следующих элментов произведение равно прошлому произведению на текущий элемент
                                if (vectors[i][j + 1] == 0) {
                                    array.add("2 " + Integer.toString(otriz[j + 1]) + " " + Integer.toString(m));
                                    m++;
                                } else {
                                    array.add("2 " + Integer.toString(j + 2) + " " + Integer.toString(m));
                                    m++;
                                }
                            }
                        }
                        flag++;
                        pred_deystvie = m;
                    } else {
                        flag_str = 0;
                        for (int j = 0; j < n - 1; j++) {
                            if (flag_str == 0) { //Первые два элемента
                                if (vectors[i][j] == 0) {
                                    if (vectors[i][j + 1] == 0) {
                                        array.add("2 " + Integer.toString(otriz[j]) + " " + Integer.toString(otriz[j + 1]));
                                    } else {
                                        array.add("2 " + Integer.toString(otriz[j]) + " " + Integer.toString(j + 2));
                                    }
                                    m++;
                                    flag_str++;
                                } else {
                                    if (vectors[i][j + 1] == 0) {
                                        array.add("2 " + Integer.toString(j + 1) + " " + Integer.toString(otriz[j + 1]));
                                    } else {
                                        array.add("2 " + Integer.toString(j + 1) + " " + Integer.toString(j + 2));
                                    }
                                    m++;
                                    flag_str++;
                                }
                            } else { // для всех следующих элментов произведение равно прошлому произведению на текущий элемент
                                if (vectors[i][j + 1] == 0) {
                                    array.add("2 " + Integer.toString(otriz[j + 1]) + " " + Integer.toString(m));
                                    m++;
                                } else {
                                    array.add("2 " + Integer.toString(j + 2) + " " + Integer.toString(m));
                                    m++;
                                }
                            }
                        }
                        array.add("3 " + Integer.toString(pred_deystvie) + " " + Integer.toString(m));
                        m++;
                        pred_deystvie = m;
                    }
                }
            }
            System.out.println(m);
            for (int i = 0; i < array.size(); i++) {
                System.out.println(array.get(i));
            }
        } else {
            System.out.println(n + 2);
            System.out.println(1 + " " + 1);
            System.out.println(2 + " " + (n + 1) + " " + 1);
        }

    }
}