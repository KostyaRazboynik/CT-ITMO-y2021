package expression.parser;

import expression.*;

public class ExpressionParser implements Parser {

    private String input;
    private int point;
    private ExpressionFunc lastFunc;
    private char prevOp;
    private char lastOp;

    @Override
    public TripleExpression parse(String expression) {
        input = expression;
        point = 0;
        tanos();
        return parseExpFunc();
    }

    public boolean hasNext() {
        return point < input.length();
    }

    public char next() {
        return input.charAt(point++);
    }

    public char curr() {
        return input.charAt(point);
    }

    private void tanos() {
        lastFunc = null;
        lastOp = 'z';
        prevOp = 'z';
    }

    private ExpressionFunc parseExpFunc() {
        skipWhiteSpaces();
        char symbol = next();
        StringBuilder parsSomething = new StringBuilder(Character.toString(symbol));
        if (symbol == '(') {
            parseExpInBrack();
        } else if (symbol == 'l' || symbol == 't') {
            parseLzerTzer(symbol);
        } else if (symbol == '-' && prevOp != ' ') {
            parseUnaryMinus();
        } else if (Character.isDigit(symbol)) {
            parseConst(parsSomething);
        } else if (Character.isAlphabetic(symbol)) {
            parseVar(parsSomething);
        } else {
            parseBinOp(symbol);
            if (closingBracK(lastOp)) {
                return lastFunc;
            }
        }

        if (closingBracK(prevOp)) {
            return lastFunc;
        }

        if (hasNext()) {
            symbol = curr();
            if (symbol == ')') {
                return lastFunc;
            }
            lastFunc = parseExpFunc();
        }
        return lastFunc;
    }

    private void parseExpInBrack() {
        char nowPrevOp = prevOp;
        char nowLastBinOp = lastOp;
        tanos();
        parseExpFunc();
        next();
        prevOp = nowPrevOp;
        lastOp = nowLastBinOp;
        skipWhiteSpaces();
    }

    private boolean closingBracK(char checkFunc) {
        prevOp = ' ';
        if (!hasNext()) return true;
        else {
            char ch = curr();
            return priorOfFunc(checkFunc) >= priorOfFunc(ch);
        }
    }

    private void parseBinOp(char ch) {
        char now = lastOp;
        prevOp = ch;
        lastOp = ch;

        if (ch == '*') lastFunc = new Multiply(lastFunc, parseExpFunc());
        else if (ch == '/') lastFunc = new Divide(lastFunc, parseExpFunc());
        else if (ch == '+') lastFunc = new Add(lastFunc, parseExpFunc());
        else if (ch == '-') lastFunc = new Subtract(lastFunc, parseExpFunc());

        lastOp = now;
        skipWhiteSpaces();
    }
    private void parseLzerTzer(char tek) {
        char now = prevOp;
        prevOp = tek;

        next();
        char ch = next();
        if (Character.isDigit(ch)) parseConst(new StringBuilder(ch));
        else {
            point--;
            if (tek == 'l') lastFunc = new L_Zeroes(parseExpFunc());
            else lastFunc = new T_Zeroes(parseExpFunc());
        }
        prevOp = now;
        skipWhiteSpaces();
    }

    private void parseUnaryMinus() {
        char now = prevOp;
        prevOp = '_';
        char ch = next();
        if (Character.isDigit(ch)) parseConst(new StringBuilder("-" + ch));
        else {
            point--;
            lastFunc = new UnaryMinus(parseExpFunc());
        }
        prevOp = now;
        skipWhiteSpaces();
    }

    private void parseVar(StringBuilder sb) {
        while (hasNext() && Character.isAlphabetic(curr())) {
            sb.append(next());
        }
        lastFunc = new Variable(sb.toString());
        skipWhiteSpaces();
    }

    private void parseConst(StringBuilder sb) {
        while (hasNext() && Character.isDigit(curr())) {
            sb.append(next());
        }
        lastFunc = new Const(Integer.parseInt(sb.toString()));
        skipWhiteSpaces();
    }

    private int priorOfFunc(char ch) {
        if (ch == '_' || ch == 'l' || ch == 't') return 3;
        else if (ch == '*' || ch == '/') return 2;
        else if (ch == '+' || ch == '-') return 1;
        else return 0;
    }

    private void skipWhiteSpaces() {
        while (hasNext() && Character.isWhitespace(curr())) {
            next();
        }
    }
}