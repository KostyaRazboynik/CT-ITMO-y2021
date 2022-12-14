import java.util.Scanner;

public class J {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        String line;
        int[][] array = new int[n][n];
        int[][] result = new int[n][n];
        for (int i = 0; i < n; i++) {
            line = sc.nextLine();
            for (int j = 0; j < n; j++) {
                array[i][j] = line.charAt(j) - '0';
            }
        }
        for (int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                for (int k = i + 1; k < j; k++){
                    result[i][j] = (result[i][j] + result[i][k] * array[k][j]) % 10;
                }
                if (result[i][j] == array[i][j]) {
                    result[i][j] = 0;
                } else {
                    result[i][j] = 1;
                }
            }
        }
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                System.out.print(result[i][j]);
            }
            System.out.println();
        }
    }
}