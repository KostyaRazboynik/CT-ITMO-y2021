import java.util.Scanner;
public class I {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        int x_l = Integer.MAX_VALUE, x_r = Integer.MIN_VALUE, y_l = Integer.MAX_VALUE, y_r = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            String str = scan.nextLine();
            Scanner sc = new Scanner(str);
            int x_i = sc.nextInt(), y_i = sc.nextInt(), h_i = sc.nextInt();
            x_l = Math.min(x_l, x_i - h_i);
            x_r = Math.max(x_r, x_i + h_i);
            y_l = Math.min(y_l, y_i - h_i);
            y_r = Math.max(y_r, y_i + h_i);
        }
        int h = (int) Math.ceil((double) Math.max(x_r - x_l, y_r - y_l) / 2);
        int x = (x_l + x_r) / 2;
        int y = (y_l + y_r) / 2;
        System.out.println(x + " " + y + " " + h);
    }
}