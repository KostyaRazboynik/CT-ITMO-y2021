package itmo.dm
 
 
import java.util.Scanner
 
private val scan = Scanner(System.`in`)
 
private fun fill(size: Int, r: Int): MutableList<Long> {
    val result = MutableList(size) { mutableListOf<Long>() }
    result[0] = mutableListOf(0, 1)
 
    var resultAdd: MutableList<Long>
 
    for (i in 1 until size) {
        resultAdd = MutableList(result[i - 1].size + 1) { 0L }
        for (j in 0 until result[i - 1].size)
            for (l in 0 until 2)
                resultAdd[j + l] += result[i - 1][j] * (if (l == 0) -r.toLong() else 1)
 
        result[i] = resultAdd
    }
 
    return result[result.size - 1]
}
 
fun main() {
     
    val r = scan.nextInt()
    val k = scan.nextInt()
 
    val p = MutableList(k + 1) { 0L }
    for (i in 0 .. k) p[i] = scan.nextLong()
 
    val rPow = MutableList(k + 2) { 1L }
    val fact = MutableList(k + 2) { 1L }
 
    rPow[1] = -r.toLong()
    for (i in 2..k) {
        rPow[i] = rPow[i-1] * -r
        fact[i] = fact[i - 1] * i
    }
 
    val kCof = MutableList(12) { mutableListOf<Long>() }
    kCof[0] = mutableListOf(0L, 1L)
    var resultAdd: MutableList<Long>
 
    for (i in 1 until 12) {
        resultAdd = MutableList(kCof[i - 1].size + 1) { 0L }
        for (j in 0 until kCof[i - 1].size)
            for (l in 0 until 2)
                resultAdd[j + l] += kCof[i - 1][j] * (if (l == 0) i.toLong() else 1)
 
        kCof[i] = resultAdd
    }
 
    val cof = MutableList(k + 1) { 0L }
    for (i in k downTo 0) {
        var sum = p[i]
        for (j in k downTo i + 1)
            sum -= cof[j] * kCof[j][i + 1] / kCof[j][1]
 
        cof[i] = sum * kCof[i][1] / kCof[i ][i + 1]
    }
 
 
 
    for (i in 0 .. k) {
        var sum = 0L
        for (j in 0 .. i) {
            sum += cof[j] * fact[k - j] / (fact[k - i] * fact[i - j])
        }
        p[i] = sum * rPow[k - i]
    }
 
    val q = fill(k + 2, r)
 
    q.reverse()
    p.reverse()
 
    while (p.isNotEmpty() && p[p.size - 1] == 0L) p.removeLast()
 
    println(p.size - 1)
    for (i in p) print("$i ")
    println()
 
    println(q.size - 2)
    q.removeLast()
    for (i in q) print("$i ")
}