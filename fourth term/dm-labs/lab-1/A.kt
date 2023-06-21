import java.util.*
import kotlin.math.max
 
private val scan = Scanner(System.`in`)
 
private val mod = 998244353
 
private var n = 0
private var m = 0
 
private lateinit var p: MutableList<Long>
private lateinit var q: MutableList<Long>
private lateinit var a: MutableList<Long>
private lateinit var pq: MutableList<Long>
 
private fun sumPoly() {
 
    println(max(n, m))
 
    for (i in 0..max(n, m)) {
        if (i < p.size && i < q.size) print("${(p[i] + q[i]) % mod} ")
        else if (i < p.size) print("${p[i] % mod} ")
        else if (i < q.size) print("${q[i] % mod} ")
    }
    println()
}
 
fun multiplyPoly() {
 
    println(n + m)
 
    for (i in 0 until p.size) {
        for (j in 0 until m + 1) pq[i + j] = (pq[i + j] + (p[i] * q[j] + mod) % mod + mod) % mod
    }
 
    for (i in pq) print("${i % mod} ")
    println()
}
 
fun dividePoly() {
 
    for (i in 0 until 1000) {
        var sum = 0L
        for (j in 1..i) sum = (sum + (q[j] * a[i - j] + mod) % mod + mod) % mod
        val curr = if (i < p.size) p[i] else 0
        a[i] = (((-sum + curr + mod) % mod) / q[0] + mod) % mod
    }
 
    for (i in a) print("${i % mod} ")
 
}
 
 
fun main() {
 
    n = scan.nextInt()
    m = scan.nextInt()
 
    p = MutableList(n + 1) { 0 }
    q = MutableList(1001) { 0 }
    a = MutableList(1000) { 0 }
    pq = MutableList(n + m + 1) { 0 }
 
    for (i in 0..n) p[i] = scan.nextLong()
    for (i in 0..m) q[i] = scan.nextLong()
 
    sumPoly()
    multiplyPoly()
    dividePoly()
}