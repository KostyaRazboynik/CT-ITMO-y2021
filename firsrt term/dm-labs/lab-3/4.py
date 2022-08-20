import sys

def main():
    n = int(sys.stdin.readline())
    cur = '0' * n
    result = [cur]
    sys.stdout.write(cur + '\n')
    while True:
        pref = cur[1:]
        if pref + '1' not in result:
            cur = pref + '1'
        elif pref + '0' not in result:
            cur = pref + '0'
        else:
            break
        result.append(cur)
        sys.stdout.write(cur + '\n')

if __name__ == '__main__':
    main()


# import java.util.Map;
# import java.util.HashMap;
# import java.util.Scanner;
#
# public class 4 {
#     public static void main(String[] args) {
#         Scanner sc = new Scanner(System.in);
#         int n = sc.nextInt();
#         Map<String, String> a = new HashMap<String, String>();
#         StringBuilder nulevoy = new StringBuilder();
#         for (int i = 0; i < n; i++) {
#             nulevoy.append("0");
#         }
#         String current = nulevoy.toString();
#         a.put(current, current);
#         System.out.println(current);
#         while (true) {
#             String prefix = current.substring(1);
#             if (!a.containsKey(prefix + "1")) {
#                 current = prefix + "1";
#             } else if (!a.containsKey(prefix + "0")) {
#                 current = prefix + "0";
#             } else {
#                 break;
#             }
#             a.put(current, current);
#             System.out.println(current);
#         }
#     }
# }