package game;

import java.util.Scanner;

public class Game {

    private final Player player1;
    private final Player player2;
    private final boolean enableLogging;

    public Game(final Player player1, final Player player2, final boolean enableLogging) {
        this.player1 = player1;
        this.player2 = player2;
        this.enableLogging = enableLogging;
    }

    public static boolean isInt(String line) {
        try {
            Integer.parseInt(line);
            return true;
        } catch (Exception e) {
            System.out.println("Invalid input - " + line);
            return false;
        }
    }

    private boolean CorrectInput(String countLine, String mLine, String nLine, String kLine) {
        if (isInt(mLine) && isInt(nLine) && isInt(kLine) && isInt(countLine)
                && Integer.parseInt(mLine) > 0 && Integer.parseInt(nLine) > 0 && Integer.parseInt(kLine) > 0 &&  Integer.parseInt(countLine) > 0) {
            if (Integer.parseInt(kLine) <= Math.max(Integer.parseInt(mLine), Integer.parseInt(nLine))) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    private int[] GameBoard(Scanner sc) {
        int countsInLine;
        String mLine, nLine, kLine, countLine;
        String[] inputArr;
        Scanner lineScanner;
        do {
            countsInLine = 0;
            inputArr = new String[4];
            System.out.println("Please, input correct m, n, k, count (number)");
            System.out.println("'k' should be less than 'm' or 'n'.");
            lineScanner = new Scanner(sc.nextLine());
            while (lineScanner.hasNext() && countsInLine < 4) {
                inputArr[countsInLine] = lineScanner.next();
                countsInLine++;
            }
            mLine = inputArr[0];
            nLine = inputArr[1];
            kLine = inputArr[2];
            countLine = inputArr[3];
        } while (lineScanner.hasNext() || !CorrectInput(mLine, nLine, kLine, countLine));

        return new int[]{Integer.parseInt(mLine), Integer.parseInt(nLine), Integer.parseInt(kLine), Integer.parseInt(countLine)};
    }

    private void regularGame(BoarCout boarCout) {
        int result = -1;
        int resultOne = 0;
        int resulTwo = 0;
        int count  = 0;
        while (resultOne != boarCout.getCount() && resulTwo != boarCout.getCount()) {
            boarCout.NewBoard(boarCout.getM(), boarCout.getN(), boarCout.getK());
            while (true) {
                if (count % 2 == 0) {
                    result = makeMove(boarCout, 1, this.player1);
                    if (result >= 0) {
                        break;
                    }
                    result = makeMove(boarCout, 2, this.player2);
                    if (result >= 0) {
                        break;
                    }
                } else {
                    result = makeMove(boarCout, 2 , this.player2);
                    if (result >= 0) {
                        break;
                    }
                    result = makeMove(boarCout, 1, this.player1);
                    if (result >= 0) {
                        break;
                    }
                }
            }
            if (result == 0) {
                System.out.println("Draw in this round");

            } else {
                System.out.println("Round result: player " + result + " won in this round");
                if (result == 2) {
                    resulTwo++;
                } else {
                    resultOne++;
                }
            }
            count++;
        }
        if (resultOne > resulTwo) {
            System.out.println("Game result: player " + 1 + " won");
        } else if (resultOne < resulTwo) {
            System.out.println("Game result: player " + 2 + " won");
        } else {
            System.out.println("Game result: Draw");
        }
    }

    public void play() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the game MNK");
        int[] tempMNK = GameBoard(sc);
        int m = tempMNK[0], n = tempMNK[1], k = tempMNK[2], count = tempMNK[3];
        BoarCout boarCout = new BoarCout(m, n, k, count);

        regularGame(boarCout);
    }

    private int makeMove(final BoarCout boarCout, final int playerNum, final Player player) {
        final Move move = player.makeMove(boarCout, boarCout.getSign());
        final Result results = boarCout.makeMove(move);
        log("Player " + playerNum + " move: " + move);
        log("Position:" + boarCout);
        if (results == Result.WIN) {
            log("Player " + playerNum + " won");
            return playerNum;
        } else if (results == Result.DRAW) {
            return 0;
        } else if (results == Result.LOSE) {
            log("Player " + playerNum + " lose");
            return 3 - playerNum;
        } else if (results == Result.UNKNOWN) {
            return -1;
        } else {
            throw new AssertionError("Invalid Input");
        }
    }

    private void log(final String message) {
        if (enableLogging) {
            System.out.println(message);
        }
    }
}