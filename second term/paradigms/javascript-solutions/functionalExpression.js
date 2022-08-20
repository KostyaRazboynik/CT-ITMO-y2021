'use strict'
let binaryOperation = calculate => (first, second) => (x, y, z) => calculate(first(x, y, z), second(x, y, z))
let add = binaryOperation((x, y) => (x + y))
let subtract = binaryOperation((x, y) => (x - y))
let multiply = binaryOperation((x, y) => (x * y))
let divide = binaryOperation((x, y) => (x / y))
let unaryOpertion = calculate => first => (x, y, z) => calculate(first(x, y, z))
let negate = unaryOpertion(x => -x)
let sinh = unaryOpertion(x => Math.sinh(x))
let cosh = unaryOpertion(x => Math.cosh(x))
let cnst = num => () => num
let e = () => Math.E
let pi = () => Math.PI
function variable(v) {
    return function (x, y, z) {
        if (v === 'x') return x
        if (v === 'y') return y
        if (v === 'z') return z
    }
}
let parse = function (expression) {
    let VAR = ["x", "y", "z"]
    let BINSIGNS = {"+": add, "-": subtract, "*": multiply, "/": divide}
    let UNSIGNS = {"negate": negate, "sinh": sinh, "cosh": cosh}
    let CONSTS = {"e": cnst(Math.E), "pi": cnst(Math.PI)}
    let elements = expression.split(" ")
    let stack = []
    for (let i = 0; i < elements.length; i++) {
        if (elements[i].length > 0) {
            let psh = undefined
            let el = elements[i]
            if (VAR.includes(el))
                psh = variable(el)
            else if (el in BINSIGNS)
                psh = BINSIGNS[el].apply(null, [stack.pop(), stack.pop()].reverse())
            else if (el in UNSIGNS)
                psh = UNSIGNS[el].apply(null, [stack.pop()])
            else  if (el in CONSTS)  psh = CONSTS[el]
            else psh = cnst(parseInt(el))
            stack.push(psh);
        }
    }
    return stack.pop()
}