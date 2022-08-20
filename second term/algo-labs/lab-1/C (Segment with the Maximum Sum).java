import java.util.Scanner;

public class C {

    public static long[][] build(long[] arr, int x, int lx, int rx, long[][] tree) {
        if (rx == lx + 1) {
            if (lx < arr.length) {
                if (arr[lx] > 0) {
                    tree[x][0] = arr[lx];
                    tree[x][1] = arr[lx];
                    tree[x][2] = arr[lx];
                    tree[x][3] = arr[lx];
                } else {
                    tree[x][0] = arr[lx];
                    tree[x][1] = 0;
                    tree[x][2] = 0;
                    tree[x][3] = 0;
                }
            }
        } else {
            int m = (lx + rx) >> 1;
            build(arr, (x << 1) + 1, lx, m, tree);
            build(arr, (x << 1) + 2, m, rx, tree);
            tree[x][0] = tree[(x << 1) + 1][0] + tree[(x << 1) + 2][0];
            tree[x][1] = Math.max(tree[(x << 1) + 1][1],  tree[(x << 1) + 1][0] + tree[(x << 1) + 2][1]);
            tree[x][2] = Math.max(tree[(x << 1) + 2][2],  tree[(x << 1) + 1][2] + tree[(x << 1) + 2][0]);
            tree[x][3] = Math.max(Math.max(tree[(x << 1) + 1][3], tree[(x << 1) + 2][3]), tree[(x << 1) + 1][2] + tree[(x << 1) + 2][1]);
        }

        return tree;
    }
    public static long[][] update(int idx, int v, int x, int lx, int rx, long[][] tree) {
        if (rx == lx + 1) {
            if (v > 0) {
                tree[x][0] = tree[x][1] = tree[x][2] = tree[x][3] = v;
            }
            else {
                tree[x][0] = v;
                tree[x][1] = tree[x][2] = tree[x][3] = 0;
            }
        } else {
            int m = (lx + rx) >> 1;
            if (idx < m) {
                update(idx, v, (x << 1) + 1, lx, m, tree);
            } else {
                update(idx, v, (x << 1) + 2, m, rx, tree);
            }
            tree[x][0] = tree[(x << 1) + 1][0] + tree[(x << 1) + 2][0];
            tree[x][1] = Math.max(tree[(x << 1) + 1][1],  tree[(x << 1) + 1][0] + tree[(x << 1) + 2][1]);
            tree[x][2] = Math.max(tree[(x << 1) + 2][2],  tree[(x << 1) + 1][2] + tree[(x << 1) + 2][0]);
            tree[x][3] = Math.max(Math.max(tree[(x << 1) + 1][3], tree[(x << 1) + 2][3]), tree[(x << 1) + 1][2] + tree[(x << 1) + 2][1]);
        }
        return tree;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        int treeSize = 1 << (int) (Math.floor(Math.log10(n) / Math.log10(2) + 1));
        long[][] a = new long[(int) (treeSize << 1)][4];
        long[][] tree = build(arr, 0, 0, treeSize, a);
        System.out.println(tree[0][3]);
        for (int i = 0; i < m; i++) {
           int  x = sc.nextInt();
           int y = sc.nextInt();
           update(x, y, 0, 0, treeSize, tree);
            System.out.println(tree[0][3]);
        }


        System.out.println();
    }
}