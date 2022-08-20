import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Treap {

    static PrintWriter out = new PrintWriter(System.out);

    public static class Node
    {
        int prior, num;
        Node l, r, parent;

        Node(int pr, int n)
        {
            prior = pr;
            num = n;
        }
    }
    static void preorder(Node node, int[][] ans) {
        if (node == null) {
            return;
        }

        int n = node.num - 1;
        ans[n][0] = node.r == null ? 0 : node.r.num;
        ans[n][1] = node.l == null ? 0 : node.l.num;
        ans[n][2] = node.parent == null ? 0 : node.parent.num;

        preorder(node.l, ans);
        preorder(node.r, ans);
    }

    static void print(int n, int[][] ans) {
        out.println("YES");
        for (int i = 0; i < n; i++) {
            out.print(ans[i][2]);
            out.print(" ");
            out.print(ans[i][1]);
            out.print(" ");
            out.print(ans[i][0]);
            out.println();
        }
        out.flush();
    }

    static Node insert(int[][] arr, int n) {
        Node root = new Node(arr[0][1], arr[0][2]);
        int prior, num;
        for (int i = 1; i < n; i++) {
            prior = arr[i][1];
            num = arr[i][2];
            if (root.prior > prior) {
                Node curr = root;
                while (curr.prior > prior && curr.parent != null) {
                    curr = curr.parent;
                }
                if (curr.prior < prior) {
                    root = new Node(prior, num);
                    root.l = curr.r;
                    root.parent = curr;
                    curr.r = root;
                    root.parent = curr;
                    root.l.parent = root;
                } else {
                    root = new Node(prior, num);
                    root.l = curr;
                    curr.parent = root;
                }
            } else {
                root.r = new Node(prior, num);
                root.r.parent = root;
                root = root.r;
            }
        }
        while (root.parent != null) {
            root = root.parent;
        }

        return root;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(reader.readLine());
        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++)
        {
            String line = reader.readLine();
            int k = 0;
            for (int j = 0; j < 2; j++) {
                StringBuilder num = new StringBuilder();
                while(k != line.length() && line.charAt(k) != ' ') {
                    num.append(line.charAt(k));
                    k++;
                }
                if (j == 0) {
                    k++;
                }
                arr[i][j] = Integer.parseInt(num.toString());
            }
            arr[i][2] = i + 1;
        }
        Arrays.sort(arr, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        Node treap = insert(arr, n);
        int[][] res = new int[n][3];
        preorder(treap, res);
        print(n, res);
    }
}