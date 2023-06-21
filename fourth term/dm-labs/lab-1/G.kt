package itmo.dm
 
 
import java.util.Scanner
import kotlin.math.max
 
private val scan = Scanner(System.`in`)
 
private var pos = 0
private var line = ""
 
private fun gcd(a: Int, b: Int): Int = if (b != 0) gcd(b, a % b) else a
 
private fun oneMoreParser(): MutableList<Long> {
 
    var result = MutableList(7) { 0L }
 
    when(line[pos]) {
        'B' -> {
            result = MutableList(7) { 0L }
            result[1] = 1
            pos++
        }
        'L' -> {
            pos += 2
            val l = oneMoreParser()
            pos++
            result[0] = 1
 
            for (i in 1 until 7) {
                var sum = 0L
                for (j in 1.. i) sum += l[j] * result[i - j]
 
                result[i] = sum
            }
        }
        'S' -> {
            pos += 2
            val l = oneMoreParser()
            pos++
 
            val a = MutableList(7) {MutableList(7) { 0L } }
            a[0][0] = 1
 
            for (i in 1 until 7) {
                a[0][i] = 1
                a[i][0] = 0
            }
 
            result[0] = 1
            for (i in 1 until 7) {
                for (j in 1 until 7) {
                    var sum = 0L
                    for (k in 0 .. i / j) {
                        var cur = 1L
                        val n = max(l[j] + k - 1, 0L)
                        for (w in n - k + 1 .. n) cur *= w
                        for (w in 2 .. k) cur /= w
 
                        sum += cur * a[i - k * j][j - 1]
                    }
 
                    a[i][j] = sum
                }
 
                result[i] = a[i][i]
            }
        }
        'C' -> {
            pos += 2
            val l = oneMoreParser()
            pos++
 
            val c = MutableList(7) {MutableList(7) { 0L } }
 
            for (i in 0 until 7) {
                c[i][1] = l[i]
                c[0][i] = 0
                c[i][0] = 0
            }
 
            for (i in 1 until 7) {
                for (j in 2 until 7) {
                    var sum = 0L
                    for (k in 1 until i ) sum += l[k] * c[i - k][j - 1]
 
                    c[i][j] = sum
                }
            }
 
            result[0] = 0
 
            for (i in 1 until 7) {
                var sum = 0L
                for (j in 1 .. i) {
                    var push = 0L
                    for (k in 0 until j) {
                        val g = gcd(j, k)
                        push += if (i % (j / g) != 0) 0L else c[i * g / j][g]
                    }
 
                    sum += push / j
                }
 
                result[i] = sum
            }
 
        }
        'P' -> {
            pos += 2
            val l = oneMoreParser()
            pos++
            val r = oneMoreParser()
            pos++
 
            for (i in 0 until 7) {
                var sum = 0L
                for (j in 0 .. i) sum += l[j] * r[i - j]
 
                result[i] = sum
            }
        }
    }
 
    return result
}
 
 
fun main() {
    line = scan.nextLine()
    for (i in oneMoreParser()) print("$i ")
}