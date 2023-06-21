package itmo.dm
 
 
import java.util.Scanner
import kotlin.math.abs
 
private fun gcd(a: Long, b: Long): Long = if (b != 0L) gcd(b, a % b) else a
 
 
private fun final(res: Array<Long>): Array<Long> {
    val nod = gcd(abs(res[0]), abs(res[1]))
    res[0] /= abs(nod)
    res[1] /= abs(nod)
 
    if (res[1] < 0) {
        res[1] *= -1L
        res[0] *= -1L
    }
 
    return res
}
 
fun main() {
 
    val scan = Scanner(System.`in`)
 
    val r = scan.nextInt()
    val k = scan.nextInt()
 
    val pList = MutableList(k + 1) { 0L }
    for (i in 0 .. k) pList[i] = scan.nextLong()
 
    val rPow = MutableList(k + 1) { 1L }
    val fact = MutableList(k + 1) { 1L }
 
    rPow[1] = -r.toLong()
    for (i in 2..k) {
        rPow[i] = rPow[i-1] * -r
        fact[i] = fact[i - 1] * i
    }
 
    val abc = Array(k + 1) { arrayOf(1L, 1L) }
    for (i in 0 .. k) {
        var upperSum = arrayOf(pList[k - i], 1L)
 
        for (j in 0 until i) {
            val plus = abc[j].clone()
 
            plus[0] *= -fact[k - j] * rPow[k - i]
            plus[1] *= fact[k - i] * fact[k - j - k + i]
 
            val nodBefore = gcd(upperSum[1], plus[1])
            upperSum = final(arrayOf(plus[1] / nodBefore * upperSum[0] + upperSum[1] / nodBefore * plus[0], upperSum[1] / nodBefore * plus[1]))
        }
        upperSum[1] *= rPow[k - i]
        abc[i] = upperSum.clone()
    }
 
    val nFact = Array(12) { arrayOf<Long>() }
    nFact[0] = arrayOf(0L, 1L)
 
    var resultAdd: Array<Long>
 
    for (i in 1 until 12) {
        resultAdd = Array(nFact[i - 1].size + 1) { 0L }
        for (j in 0 until nFact[i - 1].size) for (l in 0 until 2) resultAdd[j + l] += nFact[i - 1][j] * (if (l == 0) i.toLong() else 1)
 
        nFact[i] = resultAdd.clone()
    }
 
    val result = Array(k + 1) { arrayOf(1L, 1L) }
    for (i in 0 .. k) {
        var sumNow = arrayOf(0L, 1L)
        for (j in i .. k) {
            val nowPlusB = abc[j].clone()
            nowPlusB[0] *= nFact[j][i + 1]
            nowPlusB[1] *= nFact[j][1]
            val nodBefore = gcd(sumNow[1], nowPlusB[1])
            sumNow = final(arrayOf(nowPlusB[1] / nodBefore * sumNow[0] + sumNow[1] / nodBefore * nowPlusB[0], sumNow[1] / nodBefore * nowPlusB[1]))
        }
 
        result[i] = sumNow.clone()
    }
 
    for (now in result) {
        val x = final(now)
 
        print("${x[0]}/${x[1]} ")
    }
}