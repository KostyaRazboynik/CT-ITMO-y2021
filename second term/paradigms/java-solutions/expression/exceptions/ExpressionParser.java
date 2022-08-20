package expression.exceptions;

import expression.ExpressionFunc;
import expression.Variable;
import expression.Const;

public class ExpressionParser implements TripleParser {

    private String input;
    private int point;
    private ExpressionFunc lastFunc;
    private char prevOp;
    private char lastOp;
    private String prevExcTag;
    private String nowExcTag;
    private int brLevel = 0;

    @Override
    public ExpressionFunc parse(String expression) {
        input = expression;
        point = 0;
        tanos();
        prevExcTag = "START";
        ExpressionFunc temp = parseExpFunc();
        if (brLevel < 0 || point != expression.length()) {
            throw new ParsingException("No opening brack");
        }
        return temp;
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
        prevOp = 'z';            //z - стартовое значение; null - число, константа, переменная, закрывающая скобочка
    }

    private void skipWhiteSpaces() {
        while (hasNext() && Character.isWhitespace(curr())) {
            next();
        }
    }

    private ExpressionFunc parseExpFunc() {
        skipWhiteSpaces();

        if (!hasNext() || next() == ')' && !prevExcTag.equals("L_BRACKET")) {
            throw new ParsingException("No last argument in binary operation");
        }
        point--;
        char symbol = next();
        StringBuilder parsSomething = new StringBuilder(Character.toString(symbol));
        ExceptionsCheck(symbol);
        prevExcTag = nowExcTag;
        if (symbol == '(') {
            parseExpInBrack();
        } else if (symbol == 'l' || symbol == 't') {
            parseLzerTzer(symbol);
        } else if (symbol == 'm') {
            parseMinMax(parsSomething);
        } else if (symbol == '-' && prevOp != ' ') {
            parseUnaryMines();
        } else if (Character.isDigit(symbol)) {
            parseConst(parsSomething);
        } else if (Character.isAlphabetic(symbol)) {
            parseVar(parsSomething);
        }  else {
            if (symbol == ')') {
                ExceptionsCheck(symbol);
                return lastFunc;
            }
            parseBinOp(symbol);
            if (closingBrack(lastOp, true)) {
                return lastFunc;
            }
        }

        if (closingBrack(prevOp, false)) {
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
        char tempPrevOp = prevOp;
        char tempLastBinOp = lastOp;
        tanos();
        brLevel++;
        parseExpFunc();
        if (!hasNext()) {
            throw new ParsingException("No closing bracket");
        }
        next();
        prevOp = tempPrevOp;
        lastOp = tempLastBinOp;
        skipWhiteSpaces();
    }

    private boolean closingBrack(char checkFunc, boolean Operation) {
        prevOp = ' ';

        if (!hasNext()) return true;
        else {
            char symbol = curr();
            if (!Operation) {
                ExceptionsCheck(symbol);
            }
            return priorOfFunc(checkFunc) >= priorOfFunc(symbol);
        }
    }

    private int priorOfFunc(char symbol) {
        if (symbol == '_' || symbol == 'l' || symbol == 't') return 3;
        else if (symbol == '*' || symbol == '/') return 2;
        else if (symbol == '+' || symbol == '-' || symbol == 'm') return 1;
        else return 0;
    }

    private void parseBinOp(char symbol) {
        char now = lastOp;
        prevOp = symbol;
        lastOp = symbol;
        if (symbol == '*') lastFunc = new CheckedMultiply(lastFunc, parseExpFunc());
        else if (symbol == '/') lastFunc = new CheckedDivide(lastFunc, parseExpFunc());
        else if (symbol == '+') lastFunc = new CheckedAdd(lastFunc, parseExpFunc());
        else if (symbol == '-') lastFunc = new CheckedSubtract(lastFunc, parseExpFunc());
        else throw new ParsingException("Something went wong");

        lastOp = now;
        skipWhiteSpaces();
    }

    private void parseLzerTzer(char tek) {
        char now = prevOp;
        prevOp = tek;

        char check1 = next();
        char check2 = next();
        char check3 = next();
        point -= 2;
        if (point != input.length() && check1 == '0' && (check2 == ' ' || check2 == '(') && check3 != '*') {
            char symbol = next();
            if (Character.isDigit(symbol)) parseConst(new StringBuilder(symbol));
            else {
                point--;
                if (tek == 'l') lastFunc = new CheckedLZeroes(parseExpFunc());
                else lastFunc = new CheckedTZeroes(parseExpFunc());
            }
            prevOp = now;
            skipWhiteSpaces();
        } else {
            throw new ParsingException("l0 or t0 problems");
        }
    }

    private void parseMinMax(StringBuilder sb) {
        char now = lastOp;
        prevOp = sb.charAt(0);
        while (hasNext() && Character.isAlphabetic(curr()) && !Character.isWhitespace(curr())) {
            sb.append(next());
        }
        if (sb.toString().equals("min") || sb.toString().equals("max")) {
            next();
            if (next() == '*' || lastOp == ' ') throw new ParsingException("MinMax problems");
            else {
                point -= 2;

                if (sb.toString().equals("min")) lastFunc = new CheckedMin(lastFunc, parseExpFunc());
                else if (sb.toString().equals("max")) lastFunc = new CheckedMax(lastFunc, parseExpFunc());
                else throw new ParsingException("MinMax problems");
                lastOp = now;
                skipWhiteSpaces();
            }
        }
    }

    private void parseUnaryMines() {
        char now = prevOp;
        prevOp = '_';
        char symbol;
        if (hasNext()) symbol = next();
        else throw new ParsingException("Invalid variable");

        if (Character.isDigit(symbol)) {
            parseConst(new StringBuilder("-" + symbol));
            prevExcTag = "NUMB";
        } else {
            point--;
            lastFunc = new CheckedNegate(parseExpFunc());
        }
        prevOp = now;
        skipWhiteSpaces();
    }

    private void parseVar(StringBuilder sb) {
        while (hasNext() && Character.isAlphabetic(curr()) && !Character.isWhitespace(curr())) {
            sb.append(next());
        }
        if (!sb.toString().equals("x") && !sb.toString().equals("y") && !sb.toString().equals("z")) throw new ParsingException("Invalid variable");
        else {
            lastFunc = new Variable(sb.toString());
            skipWhiteSpaces();
        }
    }
    private void parseConst(StringBuilder sb) throws OverflowException {
        while (hasNext() && Character.isDigit(curr())) {
            sb.append(next());
        }
        try {
            lastFunc = new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException exception) {
            throw new OverflowException(sb.toString());
        }
        skipWhiteSpaces();
    }

    /*private void parseConst(StringBuilder sb) {
        while (hasNext() && Character.isDigit(curr())) {
            sb.append(next());
        }
        try {
            lastFunc = new Const(Integer.parseInt(sb.toString()));
        } catch (NumberFormatException exception) {
            throw new OverflowException(sb.toString());
        }
        skipWhiteSpaces();
    }*/

    public void ExceptionsCheck(char symbol) {
        if (symbol == '+' || symbol == '-' || symbol == '/' || symbol == '*' || symbol == 'm') nowExcTag = "OPERATION";
        else if (Character.isDigit(symbol) || symbol == 'x' || symbol == 'y' || symbol == 'z') nowExcTag = "NUMB";
        else if (symbol == 'l' || symbol == 't') nowExcTag = "L_T_ZEROES";
        else if (symbol == '(') nowExcTag = "L_BRACKET";
        else if (symbol == ')') nowExcTag = "R_BRACKET";
        else throw new ParsingException("Input unknown character");

        if ((prevExcTag.equals("START") || prevExcTag.equals("L_BRACKET")) && nowExcTag.equals("OPERATION") && symbol != '-') {
            throw new ParsingException("No first argument in binary operation");
        } else if (brLevel < 0) {
            throw new ParsingException("No opening bracket");
        } else if (nowExcTag.equals("R_BRACKET") && prevExcTag.equals("L_BRACKET")) {
            throw new ParsingException("No arguments in brackets");
        } else if (prevExcTag.equals("OPERATION") && nowExcTag.equals("OPERATION") && symbol != '-' && hasNext()) {
            throw new ParsingException("No middle argument in binary operation");
        }
    }
}