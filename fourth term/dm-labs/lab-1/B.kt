package itmo.dm
 
import java.util.Scanner
 
 
private val scan = Scanner(System.`in`)
 
private const val MOD = 998244353L
private lateinit var arr: MutableList<Long>
private var n = 0
private var m = 0
private var x1 = 0L
private var x2 = 0L
private var x3 = 0L
private var y1 = 0L
private var y2 = 0L
private var y3 = 0L
 
private fun gcd1(a: Long, updM: Long): Triple<Long, Long, Long> {
    if (a == 0L) {
        return Triple(updM, 0L, 1L)
    }
 
    val cur = gcd1(updM % a, a)
    y1 = cur.second
    x1 = cur.third - (updM / a) * cur.second
    return Triple(cur.first, x1, y1)
}
 
private fun gcd2(a: Long, updM: Long): Triple<Long, Long, Long> {
    if (a == 0L) {
        return Triple(updM, 0L, 1L)
    }
 
    val cur = gcd2(updM % a, a)
    y2 = cur.second
    x2 = cur.third - (updM / a) * cur.second
    return Triple(cur.first, x2, y2)
}
 
private fun gcd3(a: Long, updM: Long): Triple<Long, Long, Long> {
    if (a == 0L) {
        return Triple(updM, 0L, 1L)
    }
 
    val cur = gcd3(updM % a, a)
    y3 = cur.second
    x3 = cur.third - (updM / a) * cur.second
    return Triple(cur.first, x3, y3)
}
 
private fun multiply(a: MutableList<Long>, b: MutableList<Long>): MutableList<Long> {
    val res = MutableList(m + 1) { 0L }
    for (i in 0 until m + 1) {
        for (j in 0..i)
            res[i] = (res[i] + (b[i - j] * a[j] + MOD) % MOD + MOD) % MOD
    }
    return res
}
 
private fun printRes(a: MutableList<Long>) {
    var now = MutableList(m + 1) { 0L }
    val res = MutableList(m + 1) { 0L }
    now[0] = 1
    for (i in 0 until m) {
        for (j in 0..m) {
            res[j] = (res[j] + (a[i] * now[j] + MOD) % MOD + MOD) % MOD
        }
        print("${res[i]} ")
        now = multiply(now, arr)
    }
    println()
}
 
 
fun main() {
 
    n = scan.nextInt()
    m = scan.nextInt()
 
    val sqrt = MutableList(m + 1) { 0L }
    val exp = MutableList(m + 1) { 0L }
    val ln = MutableList(m + 1) { 0L }
    val res = MutableList(250) { 0L }
    val resExp = MutableList(250) { 0L }
    arr = MutableList(250) { 0L }
 
    exp[0] = 1
    resExp[0] = 1
    sqrt[0] = 1
    sqrt[0] = 1
    ln[0] = 0
    res[0] = 0
 
    for (i in 0 until n + 1) {
        arr[i] = scan.nextLong()
    }
 
    var f = 1L
    var e = 1L
    var p = 1L
 
    for (i in 1 until m + 1) {
        val modF = (f * i + MOD) % MOD
        val pp = (p * 2 + MOD) % MOD
        val nb = if (i % 2 != 0) 1 else -1
        val ef = if (i > 1) (e * (2 * i - 3) + MOD) % MOD else e
        gcd1(modF, MOD)
        gcd2((nb * i + MOD) % MOD, MOD)
        gcd3(((nb * modF + MOD) % MOD * pp + MOD) % MOD, MOD)
        exp[i] = (x1 % MOD + MOD) % MOD
        ln[i] = (x2 % MOD + MOD) % MOD
        sqrt[i] = (ef * ((x3 % MOD + MOD) % MOD) + MOD) % MOD
 
        f = (f * i + MOD) % MOD
        p = (p * 2 + MOD) % MOD
        e = ef
    }
    printRes(sqrt)
    printRes(exp)
    printRes(ln)
}