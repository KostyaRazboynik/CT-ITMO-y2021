public class Sum {

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 0; i < args.length; i++) {
            String tek = "";
            for (int j = 0; j < args[i].length(); j++) {
                char symbol = args[i].charAt(j);
                if (((symbol == '+' || symbol == '-') && (j != args[i].length() - 1) && (args[i].charAt(j + 1) == '0' || args[i].charAt(j + 1) == '1' || args[i].charAt(j + 1) == '2' || args[i].charAt(j + 1) == '3' || args[i].charAt(j + 1) == '4' || args[i].charAt(j + 1) == '5' || args[i].charAt(j + 1) == '6' || args[i].charAt(j + 1) == '7' || args[i].charAt(j + 1) == '8' || args[i].charAt(j + 1) == '9')) || symbol == '0' || symbol == '1' || symbol == '2' || symbol == '3' || symbol == '4' || symbol == '5' || symbol == '6' || symbol == '7' || symbol == '8' || symbol == '9') {
                    if (j != 0) {
                        if ((symbol == '+' || symbol == '-') && (args[i].charAt(j - 1) == '0' || args[i].charAt(j - 1) == '1' || args[i].charAt(j - 1) == '2' || args[i].charAt(j - 1) == '3' || args[i].charAt(j - 1) == '4' || args[i].charAt(j - 1) == '5' || args[i].charAt(j - 1) == '6' || args[i].charAt(j - 1) == '7' || args[i].charAt(j - 1) == '8' || args[i].charAt(j - 1) == '9')) {
                            sum += Integer.parseInt(tek);
                            tek = "";
                        }
                    }
                    tek += symbol;
                    if (j == (args[i].length() - 1)) {
                        sum += Integer.parseInt(tek);
                    }
                } else {
                    if (tek.length() > 0) {
                        sum += Integer.parseInt(tek);
                        tek = "";
                    }
                }
            }
        }
        System.out.println(sum);
    }
}