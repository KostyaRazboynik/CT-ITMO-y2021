"use strict"
let operations = new Map()
let VARIABLES = ["x", "y", "z"]
function ExpressionConstructor(toString, prefix, evaluate, diff) {
    function Expression(...expr) {
        this.expr = expr
    }
    Expression.prototype.toString = toString
    Expression.prototype.prefix = prefix
    Expression.prototype.evaluate = evaluate
    Expression.prototype.diff = diff
    return Expression
}
let Variable = ExpressionConstructor(
    function () {
        return this.expr[0]
    },
    function () {
        return this.expr[0]
    },
    function (...values) {
        return values[VARIABLES.indexOf(this.expr[0])]
    },
    function (diffMethod) {
        if (this.expr[0] === diffMethod) {
            return ONE
        }
        return ZERO
    })
let Const = ExpressionConstructor(
    function () {
        return this.expr.toString()
    },
    function () {
        return this.expr.toString()
    },
    function () {
        return this.expr[0]
    },
    () => ZERO)
const ZERO = new Const(0)
const ONE = new Const(1)
let abstractOperation = ExpressionConstructor(
    function () {
        return this.expr.join(" ") + " " + this.symbol
    },
    function () {
        return "(" + this.symbol + " " + this.expr.map(ex => ex.prefix()).join(" ") + ")"
    },
    function (...values) {
        return this.calculate(...this.expr.map(expression => expression.evaluate(...values)))
    },
    function (diffMethod) {
        return this.diffRule(...this.expr)(...this.expr.map(expr => expr.diff(diffMethod)))
    })
function operationConstructor(symbol, calculate, diffMethod) {
    operations.set(symbol, Operation)
    function Operation(...args) {
        abstractOperation.call(this, ...args)
    }
    Operation.prototype = Object.create(abstractOperation.prototype)
    Operation.prototype.symbol = symbol
    Operation.prototype.calculate = calculate
    Operation.prototype.diffRule = diffMethod
    return Operation
}
let Add = operationConstructor("+", (x, y) => x + y, () => (dx, dy) => new Add(dx, dy))
let Subtract = operationConstructor("-", (x, y) => x - y, () => (dx, dy) => new Subtract(dx, dy))
let Multiply = operationConstructor("*", (x, y) => x * y, (x, y) => (dx, dy) => new Add(new Multiply(dx, y), new Multiply(x, dy)))
let Divide = operationConstructor("/", (x, y) => x / y, (x, y) => (dx, dy) => new Divide(new Subtract(new Multiply(dx, y), new Multiply(x, dy)), new Multiply(y, y)))
let Negate = operationConstructor("negate", x => -x, () => dx => new Negate(dx))
let Sinh = operationConstructor("sinh", x => Math.sinh(x), (x) => dx => new Multiply(new Cosh(x), dx))
let Cosh = operationConstructor("cosh", x => Math.cosh(x), (x) => dx => new Multiply(new Sinh(x), dx))
let Min3 = operationConstructor("min3", (x, y, z) => Math.min(x, y, z), () => Max5(0))
let Max5 = operationConstructor("max5", (x, y, z, a, b) => Math.max(x, y, z, a, b), () => Min3(0))
let parse = expression => {
    let stack = []
    let elements = expression.split(" ")
    for (let i = 0; i < elements.length; i++) {
        let el = elements[i]
        if (el.length > 0) {
            let psh = undefined
            if (operations.has(el)) {
                let op = operations.get(el)
                let curr = op.prototype.calculate.length
                psh = new op(...stack.splice(-curr))
            } else if (VARIABLES.includes(el)) {
                psh = new Variable(el)
            } else {
                psh = new Const(parseInt(el))
            }
            stack.push(psh)
        }
    }
    return stack[0]
}
function ParseException(message) {
    Error.call(this, message)
    this.message = message
}
ParseException.prototype = Object.create(Error.prototype)
ParseException.prototype.constructor = ParseException
function isLetter(el) {
    return 'a' <= el && el <= 'z'
}
function isDigit(el) {
    return '0' <= el && el <= '9' || el === '-'
}
class BaseParser {
    constructor(expression) {
        this.expr = expression
        this.position = 0
        this.next()
    }
    next() {
        if (this.position < this.expr.length)
            this.el = this.expr[this.position++]
        else
            this.el = '\0'
    }
    equality(got) {
        for (let c of got) {
            if (c === this.el)
                this.next()
            else
                return false
        }
        return true
    }

    skipWhitespace() {
        while (" " === this.el) {
            this.next()
        }
    }
}
class ExpressionParser {
    constructor(expression) {
        this.expr = new BaseParser(expression)
    }
    parseExpression() {
        this.expr.skipWhitespace()
        let result;
        result = this.parseValues()
        this.expr.skipWhitespace()
        if (!this.expr.equality('\0'))
            throw new ParseException("Unexpected char")
        return result
    }
    parseOperation() {
        this.expr.skipWhitespace()
        for (let op of operations.keys()) {
            if (this.expr.equality(op)) {
                let operation = operations.get(op)
                if (isDigit(this.expr.el) || isLetter(this.expr.el))
                    throw new ParseException("Unexpected char")
                return new operation(...this.parseArgs(operation.prototype.calculate.length))
            }
        }
        throw new ParseException("Unknown Operation");
    }
    parseArgs(n) {
        let args = []
        this.expr.skipWhitespace()
        while (!this.expr.equality(')')) {
            args.push(this.parseValues())
            this.expr.skipWhitespace()
        }
        if (n !== 0 && n !== args.length)
            throw new ParseException("Illegal Argument")
        return args
    }
    parseValues() {
        this.expr.skipWhitespace()
        if (isDigit(this.expr.el)) {
            let num = ''
            while (isDigit(this.expr.el)) {
                num += this.expr.el
                this.expr.next()
            }
            return new Const(Number.parseInt(num))
        } else if (isLetter(this.expr.el)) {
            let v = ''
            while (isLetter(this.expr.el)) {
                v += this.expr.el
                this.expr.next()
            }
            if (VARIABLES.includes(v))
                return new Variable(v)
            else
                throw new ParseException("Illegal Variable")
        } else if (this.expr.equality('('))
            return this.parseOperation()
        else
            throw new ParseException("Lost Argument")
    }
}
let parsePrefix = (expression) => new ExpressionParser(expression).parseExpression()