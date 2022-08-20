import java.util.Scanner;
public class H {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        StringBuilder stec = new StringBuilder();
        int flag = 0;
        if (str.length() % 2 == 0) {
            for (int i = 0; i < str.length(); i++) {
                char c = str.charAt(i);
                if (c == '{' || c == '[' || c == '(') {
                    stec.append(c);
                } else {
                    if (stec.length() > 0) {
                        char a = stec.charAt(stec.length() - 1);
                        if (c == '}' && a != '{' || c == ']' && a != '[' || c == ')' && a != '(') {
                            flag++;
                            break;
                        } else {
                            stec.setLength(stec.length() - 1);
                        }
                    } else {
                        flag++;
                    }
                }
            }
            if (flag == 0) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        } else {
            System.out.println("NO");
        }
    }
}