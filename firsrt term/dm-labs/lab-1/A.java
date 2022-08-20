import java.util.Scanner;
import java.util.Arrays;
public class A {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt (sc.nextLine());
        int[][] arr = new int[n][n];
        int[][] rel = new int[n][n];
        int[][] arrOrel = new int[n][n];
        for (int i = 0; i < n; i++) {
            Scanner scan = new Scanner(sc.nextLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = scan.nextInt();
            }
        }
        for (int i = 0; i < n; i++) {
            Scanner scan = new Scanner(sc.nextLine());
            for (int j = 0; j < n; j++) {
                rel[i][j] = scan.nextInt();
            }
        }
        StringBuilder itog1 = new StringBuilder();
        StringBuilder itog2 = new StringBuilder();

        //Проверка на рефлексивность
        int flag1 = 0;
        int flag2 = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i][i] == 1){
                flag1++;
            }
            if (rel[i][i] == 1){
                flag2++;
            }
        }
        if (flag1 == n){
            itog1.append("1 ");
        } else {
            itog1.append("0 ");
        }
        if (flag2 == n){
            itog2.append("1 ");
        } else {
            itog2.append("0 ");
        }

        //Проверка на антирефлексивность
        flag1 = 0;
        flag2 = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i][i] == 0){
                flag1++;
            }
            if (rel[i][i] == 0){
                flag2++;
            }
        }
        if (flag1 == n){
            itog1.append("1 ");
        } else {
            itog1.append("0 ");
        }
        if (flag2 == n){
            itog2.append("1 ");
        } else {
            itog2.append("0 ");
        }

        //Проверка на симметричность
        flag1 = 0;
        flag2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] != arr[j][i]) {
                    flag1++;
                }
                if (rel[i][j] != rel[j][i]) {
                    flag2++;
                }
            }
        }
        if (flag1 == 0){
            itog1.append("1 ");
        } else {
            itog1.append("0 ");
        }
        if (flag2 == 0){
            itog2.append("1 ");
        } else {
            itog2.append("0 ");
        }
        //Проверка на антисимметричность
        flag1 = 0;
        flag2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == arr[j][i] && arr[i][j] == 1 && i != j) {
                    flag1++;
                }
                if (rel[i][j] == rel[j][i] && rel[i][j] == 1 && i != j) {
                    flag2++;
                }
            }
        }
        if (flag1 == 0){
            itog1.append("1 ");
        } else {
            itog1.append("0 ");
        }
        if (flag2 == 0){
            itog2.append("1 ");
        } else {
            itog2.append("0 ");
        }


        //Проверка на транзитивность
        flag1 = 0;
        flag2 = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                for (int k = 0; k < n; k++){
                    if (arr[i][j] == 1 && arr[j][k] == 1 && arr[i][k] != 1){
                        flag1++;
                    }
                    if (rel[i][j] == 1 && rel[j][k] == 1 && rel[i][k] != 1) {
                        flag2++;
                    }
                }
            }
        }
        if (flag1 == 0){
            itog1.append("1");
        } else {
            itog1.append("0");
        }
        if (flag2 == 0){
            itog2.append("1");
        } else {
            itog2.append("0");
        }
        System.out.println(itog1);
        System.out.println(itog2);

        //Композиция
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1) {
                    for (int k = 0; k < n; k++) {
                        if (rel[j][k]==1) {
                            arrOrel[i][k] = 1;
                        }
                    }
                }
            }
        }

        //Вывод композиции
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(arrOrel[i][j] + " ");
            }
            System.out.println();
        }
    }
}