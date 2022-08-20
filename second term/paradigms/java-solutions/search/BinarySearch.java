package search;

public class BinarySearch {

    // Pred: x == Z (целое число) && mas[] отсортированный по невозрастанию массив
    // ...
    // Post: res == 0 || (res == N₊ && res <= mas.length && x >= mas[res] && x < mas[res - 1]
    // :NOTE: copy-paste from iterative, not valid for recursive
/*
    public static int recursiveBinSearch(int l, int r) {
        // I: mas[] отсортированный по невозрастанию массив && x == Z (целое число) && r <= mas.length && l >= -1
        if (r != l + 1) {
            // Pred: r != l + 1
            int m = (l + r) / 2;
            // Post: r != l + 1 && m == (l + r) / 2

            // Pred: -1 < m < mas.length
            if (mas[m] <= x) {
                // mas[r] <= x
                return recursiveBinSearch(l, m);
            } else {
                // mas[r] > x
                return recursiveBinSearch(m, r);
            }
            //Post: (m == r && mas[r] <= x) || (m == l && mas[l] > x)
        }
        // Post: r == ℤ && mas[r] <= x && 0 <= r <= mas.length
        // l >= -1 && x < mas[l] && && (x == N || x == 0) && mas[] отсортированный по невозрастанию массив && r == l + 1

        return r;
    }

    // :NOTE: notation too informal
    // :NOTE: res might be negative ? && postcondition doesn't satisfiy binsearch properties for res = 0
    // Pred: x == Z (целое число) && mas[] отсортированный по невозрастанию массив
    // ...
    // Post: res == 0 || (res == Z && res <= mas.length && x >= mas[res] && x < mas[res - 1]

    public static int iterativeBinSearch() {
        // Pred: true
        int r = mas.length;
        int l = -1;
        // Post: l == -1 && r == mas.length

        // I: mas[] отсортированный по невозрастанию массив && x == Z (целое число) && r <= mas.length && l >= -1
        // :NOTE: no finishing proof, no l < r invariant
        while (r != l + 1) {
            // Pred: r != l + 1
            int m = (l + r) / 2;
            // Post: l + 1 != r && m == (l + r) / 2

            // Pred: -1 < m < mas.length
            if (mas[m] <= x) {
                r = m;
            } else {
                l = m;
            }
            // Post: (m == r && mas[r] <= x) || (m == l && mas[l] > x)
        }
        // Post: r == ℤ && mas[r] <= x && 0 <= r <= mas.length
        // l >= -1 && x < mas[l] && && (x == N || x == 0) && mas[] отсортированный по невозрастанию массив && r == l + 1

        return r;
    }

    // :NOTE: static vars to pass info to functions
    private static int[] mas;
    private static int x;
*/
    // :NOTE: no pre-/post- conditions
    public static void main(String[] args) {
        int[] mas;
        int x;
        x = Integer.parseInt(args[0]);
        int len = args.length;
        mas = new int[len - 1];
        for (int i = 1; i < len; i++) {
            mas[i - 1] = Integer.parseInt(args[i]);
        }
        //int res;
        // :NOTE: only one method is tested
        //res = iterativeBinSearch();

        int r = len - 1;
        int l = -1;
        // Post: l == -1 && r == mas.length

        // I: mas[] отсортированный по невозрастанию массив && x == Z (целое число) && r <= mas.length && l >= -1
        // :NOTE: no finishing proof, no l < r invariant
        while (r != l + 1) {
            // Pred: r != l + 1
            int m = (l + r) / 2;
            // Post: l + 1 != r && m == (l + r) / 2

            // Pred: -1 < m < mas.length
            if (mas[m] <= x) {
                r = m;
            } else {
                l = m;
            }
            // Post: (m == r && mas[r] <= x) || (m == l && mas[l] > x)
        }

        /*int l = -1;
        int r = mas.length;
        res = recursiveBinSearch(l, r);*/

        System.out.println(r);
    }
}