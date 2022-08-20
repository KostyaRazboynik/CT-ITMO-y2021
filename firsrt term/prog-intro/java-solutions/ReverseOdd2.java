import java.util.Scanner;

import java.util.Arrays;
public class ReverseOdd2 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int[] arr = new int[10]; // массив выходных значений (в нем хранятся числа в порядке добавления, которые необходимо вывести)
        int[] numbersPerStr = new int[10]; // массив, хранящий кол-во выходных значений с каждой строки
        int k = 0; // кол-во выходных элементов
        int strnumber = 0; // номер строки
        while (scan.hasNextLine()) {
            if (numbersPerStr.length < strnumber + 1) {
                numbersPerStr = Arrays.copyOf(numbersPerStr, numbersPerStr.length * 2);
            }
            //String str = scan.nextLine();
            Scanner sc = new Scanner(scan.nextLine());
            int index = 0; // индекс (номер) числа в строке - "столбец"
            while (sc.hasNextInt()) {
                if (arr.length < k + 1) {
                    arr = Arrays.copyOf(arr, arr.length * 2);
                }
                int num = sc.nextInt(); //num - число в строке, входной элемент
                if ((index + strnumber) % 2 != 0) {
                    arr[k++] = num;
                    numbersPerStr[strnumber]++;
                }
                index++;
            }
            sc.close();
            strnumber++;
        }
        k--;
        for (int i = strnumber - 1; i >= 0; i--) {
            while (numbersPerStr[i]-- > 0) {
                System.out.print(arr[k--] + " ");
            }
            System.out.print('\n');
        }
    }
}