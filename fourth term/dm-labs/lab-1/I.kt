package itmo.dm
 
 
import java.util.Scanner
 
private const val MOD = 104857601L
private val scan = Scanner(System.`in`)
 
fun main() {
    val k = scan.nextInt()
    var n = scan.nextLong() - 1
 
    val a = Array(2 * k) { 0L }
    for (i in 0 until k) a[i] = scan.nextLong()
 
    var c = Array(k + 1) { 1L }
    for(i in 1 .. k) c[i] = (MOD - scan.nextLong()) % MOD
 
    val r = Array(k + 1) { 0L }
    val neg_q = Array(k + 1) { 0L }
 
    while (n >= k) {
        for (i in k until a.size) {
            a[i] = 0
            for (j in 1 until c.size) {
                a[i] = (a[i] - c[j] * a[i - j]) % MOD
                while (a[i] < 0)
                    a[i] += MOD
            }
        }
 
        for (i in 0 until c.size) neg_q[i] = if (i % 2 == 0) c[i] else (MOD - c[i]) % MOD
 
        for (i in 0..2 * k step 2) {
            var cr = 0L
            for (j in 0..i) {
 
                cr = (cr + (if (j > k) 0 else c[j]) * (if (i - j > k) 0 else neg_q[i - j]) + MOD) % MOD
            }
 
            r[i / 2] = cr
        }
        c = r.clone()
 
        var j = 0
        for (i in 0 until a.size) {
            if ((i and 1).toLong() == (n and 1L)) {
                a[j++] = a[i]
            }
        }
 
        n /= 2
    }
 
    println(a[n.toInt()])
}