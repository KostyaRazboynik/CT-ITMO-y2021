package search;

public class BinarySearchMissing {

    // Pred: x == Z (целое число) && mas[] отсортированный по неубыванию массив
    // ...
    // Post: res == Z && res <= mas.length && (x >= mas[res] && x < mas[res - 1] || x == Z)
/*
    public static int recursiveBinSearch(int l, int r) {
        // I: mas[] отсортированный по неубыванию массив && x == Z (целое число) && r <= mas.length && l >= -1
        if (r != l + 1) {
            // Pred: r != l + 1
            int m = (l + r) / 2;
            // Post: r != l + 1 && m == (l + r) / 2

            // Pred: -1 < m < mas.length
            if (mas[m] >= x) {
                // mas[r] >= x
                return recursiveBinSearch(l, m);
            } else {
                // mas[r] < x
                return recursiveBinSearch(m, r);
            }
            //Post: (m == r && mas[r] >= x) || (m == l && mas[l] < x)
        }
        // Post: r == ℤ && mas[r] >= x && 0 <= r <= mas.length
        // l >= -1 && x > mas[l] && x == Z && mas[] отсортированный по неубыванию массив && r == l + 1

        // Pred: r == Z
        if (r >= mas.length || mas[r] != x) {
            return -r - 1;
        }
        return r;
        // Post: r == r || r == -r - 1 (если элемента нет в массиве)
    }

    // Pred: x == Z (целое число) && mas[] отсортированный по неубыванию массив
    // ...
    // Post: res == Z && res <= mas.length && (x >= mas[res] && x < mas[res - 1] || x == Z)

    public static int iterativeBinSearch() {
        // Pred: true
        int r = mas.length;
        int l = -1;
        // Post: l == -1 && r == mas.length

        // I: mas[] отсортированный по неубыванию массив && x == Z (целое число) && r <= mas.length && l >= -1
        while (r != l + 1) {
            // Pred: r != l + 1
            int m = (l + r) / 2;
            // Post: l + 1 != r && m == (l + r) / 2

            // Pred: -1 < m < mas.length
            if (mas[m] >= x) {
                r = m;
            } else {
                l = m;
            }
            // Post: (m == r && mas[r] >= x) || (m == l && mas[l] < x)
        }
        // r == ℤ && mas[r] >= x && 0 <= r <= mas.length
        // l >= -1 && x > mas[l] && && (x == N || x == 0) && mas[] отсортированный по неубыванию массив && r == l + 1

        // Pred: r == Z
        if (r >= mas.length || mas[r] != x) {
            return -r - 1;
        }
        return r;
        // Post: r == r || r == -r - 1 (если элемента нет в массиве)
    }

    private static int[] mas;
    private static int x;
*/
    public static void main(String[] args) {
        int x = Integer.parseInt(args[0]);
        int len = args.length;
        int[] mas = new int[len - 1];
        if (len <= 1) {
            System.out.println(-1);
        } else {
            for (int i = 1; i < len; i++) {
                mas[i - 1] = Integer.parseInt(args[i]);
            }
            int res;
//            res = iterativeBinSearch();

            /*int l = -1;
            int r = mas.length;
            res = recursiveBinSearch(l, r);*/

            int r = len - 1;
            int l = -1;
            // Post: l == -1 && r == mas.length

            // I: mas[] отсортированный по неубыванию массив && x == Z (целое число) && r <= mas.length && l >= -1
            while (r != l + 1) {
                // Pred: r != l + 1
                int m = (l + r) / 2;
                // Post: l + 1 != r && m == (l + r) / 2

                // Pred: -1 < m < mas.length
                if (mas[m] >= x) {
                    r = m;
                } else {
                    l = m;
                }
                // Post: (m == r && mas[r] >= x) || (m == l && mas[l] < x)
            }
            // r == ℤ && mas[r] >= x && 0 <= r <= mas.length
            // l >= -1 && x > mas[l] && && (x == N || x == 0) && mas[] отсортированный по неубыванию массив && r == l + 1

            // Pred: r == Z
            if (r >= len - 1 || mas[r] != x) {
                res =  -r - 1;
            }
            else {
                res = r;
            }

            System.out.println(res);
        }
    }
}