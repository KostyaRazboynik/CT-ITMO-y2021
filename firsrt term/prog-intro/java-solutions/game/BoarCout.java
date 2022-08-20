package game;

import java.util.Map;

public class BoarCout implements Position {
    private static final Map<Cell, Character> symbolsOfCout = Map.of(Cell.X, 'X', Cell.O, 'O', Cell.E, '.');
    private static int m, n, c;
    private int k, countsZero;
    private Cell[][] board;
    private Cell sign = Cell.X;

    public BoarCout(int m, int n, int k, int c) {
        this.m = m;
        this.n = n;
        this.k = k;
        this.c = c;
        NewBoard(m, n, k);
    }

    public static int getM() {
        return m;
    }

    public static int getN() {
        return n;
    }

    public int getK() {
        return k;
    }

    public int getCount() {return c;}

    public void NewBoard(int m, int n, int k) {
        countsZero = m * n;
        this.board = new Cell[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                board[i][j] = Cell.E;
            }
        }
        sign = Cell.X;
    }


    @Override
    public String toString() {
        StringBuilder returnBoardStringBuilder = new StringBuilder();
        StringBuilder up = new StringBuilder();
        up.append("  ");
        for (int i = 1; i <= m; i++) {
            up.append(i + " ");
        }
        up.append("\n");
        returnBoardStringBuilder.append(up);
        for (int i = 0; i < n; i++) {
            returnBoardStringBuilder.append(i + 1 + " ");
            for (int j = 0; j < m; j++) {
                returnBoardStringBuilder.append(symbolsOfCout.get(board[i][j]) + " ".repeat(Integer.toString(j + 1).length()));
            }
            returnBoardStringBuilder.append("\n");
        }
        return returnBoardStringBuilder.toString();
    }

    public Cell getSign() {
        return sign;
    }

    @Override
    public Cell get(final int row, final int col) {
        return board[row][col];
    }

    @Override
    public boolean isValid(final Move move) {
        int a = move.getRow(), b = getN(), c = move.getCol(), d = getM();
        if (0 <= a && a < b
                && 0 <= c && c < d) {
            return get(a, c) == Cell.E
                    && move.getValue() == sign;
        }
        return false;
    }

    public Result makeMove(Move move) {
        if (!isValid(move)) {
            return Result.LOSE;
        }
        board[move.getRow()][move.getCol()] = move.getValue();
        countsZero--;
        Result res;

        if (isLine(move.getRow(), getM(), true)
                || isLine(move.getCol(), getN(), false)
                || inDiag(true, move)
                || inDiag(false, move)) {
            res = Result.WIN;
        } else if (countsZero == 0) {
            res = Result.DRAW;
        } else {
            res = Result.UNKNOWN;
        }

        if (sign == Cell.X) {
            sign = Cell.O;
        } else {
            sign = Cell.X;
        }

        return res;
    }

    private boolean isLine(int constant, final int size, boolean CheckLine) {
        for (int i = 0; i < size; i++) {
            int countsInLine = 0;
            while (i < size) {
                if (CheckLine && get(constant, i) == sign) {
                    countsInLine++;
                } else if (!CheckLine && get(i, constant) == sign) {
                    countsInLine++;
                } else {
                    break;
                }
                i++;
            }
            if (countsInLine >= k) {
                return true;
            }
        }
        return false;
    }

    private boolean inDiag(boolean LeftToRight, Move move) {
        int rowNum = move.getRow(), colNum = move.getCol(), curRowNum, curColNum, minus;
        if (LeftToRight) {
            minus = Math.min(rowNum, colNum);
            curColNum = colNum - minus;
        } else {
            minus = Math.min(rowNum, getM() - colNum - 1);
            curColNum = colNum + minus;
        }
        curRowNum = rowNum - minus;

        while (curRowNum < getN() && checkBorder(LeftToRight, curColNum)) {
            int countsTogether = 0;
            while (curRowNum < getN() && checkBorder(LeftToRight, curColNum)) {
                if (get(curRowNum, curColNum) == sign) {
                    countsTogether++;
                    curRowNum++;

                } else {
                    break;
                }
            }
            if (countsTogether >= k) {
                return true;
            }
             curRowNum++;
        }

        return false;
    }

    private boolean checkBorder(boolean isItLeftToRight, int nowIdCol) {
        if (isItLeftToRight) {
            return nowIdCol < getM();
        } else {
            return nowIdCol > -1;
        }
    }
}