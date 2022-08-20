import java.util.ArrayDeque;
import java.util.Scanner;

public class I {
    static int babki[][];
    static int mas[];


    static int FFF(int i, int j) {
        //System.out.println(Arrays.deepToString(babki));
        if (j > i) return 100000;
        else {
            int res;
            int cost = mas[i];
            if (j <= 0) {
                if (i >= 1) {
                    if (cost <= 100) {
                        int dif = Math.min(FFF(i - 1, j + 1), FFF(i - 1, j) + cost);
                        res = dif;
                    } else {
                        return FFF(i - 1, j + 1);
                    }
                } else return 0;
            } else {
                if (babki[i][j] != -1) return babki[i][j];
                if (cost > 100) {
                    int dif = Math.min(FFF(i - 1, j + 1), FFF(i - 1, j - 1) + cost);
                    res = dif;
                } else {
                    int dif = Math.min(FFF(i - 1, j + 1), FFF(i - 1, j) + cost);
                    res = dif;
                }
            }
            babki[i][j] = res;
            return res;
        }
    }

    static void PrivetSnimemsya(ArrayDeque<Integer> used, int i, int j) {
        if (j < i) {
            int cost = mas[i];
            if (j <= 0) {
                if (i >= 1) {
                    if (cost > 100) {
                        used.add(i);
                        PrivetSnimemsya(used, i - 1, j + 1);
                    } else {
                        boolean addi = (FFF(i, j) == FFF(i - 1, j + 1));
                        if (addi) {
                            used.add(i);
                            PrivetSnimemsya(used, i - 1, j + 1);
                        } else PrivetSnimemsya(used, i - 1, j);
                    }
                }
            } else {
                if (cost <= 100) {
                    boolean addi = (FFF(i - 1, j + 1) == FFF(i, j));
                    if (addi) {
                        used.add(i);
                        PrivetSnimemsya(used, i - 1, j + 1);
                    } else {
                        PrivetSnimemsya(used, i - 1, j);
                    }
                } else {
                    boolean addi = (FFF(i - 1, j + 1) == FFF(i, j));
                    if (addi) {
                        used.add(i);
                        PrivetSnimemsya(used, i - 1, j + 1);
                    } else {
                        PrivetSnimemsya(used, i - 1, j - 1);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int k1 = 0;
        int k2 = 0;
        mas = new int[n + 1];
        for (int i = 1; i <= n; i++) mas[i] = scanner.nextInt();
        babki = new int[n + 1][n + 2];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n + 1; j++)
                babki[i][j] = -1;
        }
        int ans = 100000;

        for (int i = 0; i <= n; i++) {
            int curr = FFF(n, i);
            if (ans >= curr) {
                ans = curr;
                k1 = i;
            }
        }

        System.out.println(ans);

        ArrayDeque<Integer> used = new ArrayDeque<>();
        PrivetSnimemsya(used, n, k1);

        k2 = used.size();

        System.out.println(k1 + " " + k2);

        while (!used.isEmpty()) {
            System.out.println(used.removeLast());
        }
    }
}