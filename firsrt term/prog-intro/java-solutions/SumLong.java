public class SumLong {
    public static void main(String[] args) {
        long sum = 0;
        for (int i = 0; i < args.length; i++) {
            String tek = "";
            for (int j = 0; j < args[i].length(); j++) {
                char symbol = args[i].charAt(j);
                if (((symbol == '+' || symbol == '-') && (j != args[i].length() - 1) && (Character.isDigit(args[i].charAt(j + 1)))) || Character.isDigit(symbol)) {
                    if (j != 0) {
                        if ((symbol == '+' || symbol == '-') && (Character.isDigit(args[i].charAt(j - 1)))) {
                            sum += Long.parseLong(tek);
                            tek = "";
                        }
                    }
                    tek += symbol;
                    if (j == (args[i].length() - 1)) {
                        sum += Long.parseLong(tek);
                    }
                } else {
                    if ((tek.length() > 0)) {
                        sum += Long.parseLong(tek);
                        tek = "";
                    }
                }
            }
        }
        System.out.println(sum);
    }
}