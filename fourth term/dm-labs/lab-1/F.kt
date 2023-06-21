package itmo.dm
 
 
import java.util.Scanner
 
fun main() {
    val MOD = 1000000007L
    val scan = Scanner(System.`in`)
 
    val k = scan.nextInt()
    val m = scan.nextInt()
 
    val nodes = MutableList(m + 1) { false }
    for (i in 0 until k) nodes[scan.nextInt()] = true
 
    val c = MutableList(m + 1) { 0L }
    c[0] = 1
 
    val p = MutableList(m + 1) { 0L }
    p[0] = 1
 
    var sum: Long
    for (i in 1 until m + 1) {
        sum = 0L
        for (j in 1 .. i) if (nodes[j]) sum += p[i - j] % MOD
 
        c[i] = sum % MOD
 
        for (j in 0 .. i) p[i] = (p[i] + c[j] * c[i - j]) % MOD
 
        print("${c[i]} ")
    }
}