import java.util.*
 
private val scan = Scanner(System.`in`)
 
private var k = 0
 
private lateinit var a: MutableList<Long>
private lateinit var c: MutableList<Long>
private lateinit var p: MutableList<Long>
private lateinit var q: MutableList<Long>
 
 
fun main() {
 
    k = scan.nextInt()
 
    a = MutableList(k) { 0 }
    c = MutableList(k) { 0 }
    p = MutableList(k + 1) { 0 }
    q = MutableList(k + 1) { 0 }
 
    for (i in 0 until k) a[i] = scan.nextLong()
    q[0] = 1
 
    for (i in 0 until k) {
        c[i] = scan.nextLong()
        q[i + 1] = -c[i]
        var sum = 0L
        for (j in 0 until i)
            sum += c[j] * a[i - j - 1]
        p[i] = a[i] - sum
    }
 
 
    var degP = k
    for (i in k downTo 0) {
        if (p[i] != 0L) break
        degP--
    }
 
    println(degP)
    for (i in 0 .. degP) print("${p[i]} ")
    println()
 
    println(q.size - 1)
    for (i in q) print("$i ")
}