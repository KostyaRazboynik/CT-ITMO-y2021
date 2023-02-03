import java.util.*
import kotlin.collections.ArrayList
 
private val scan = Scanner(System.`in`)
 
fun main() {
    val n = scan.nextInt()
 
    val V = PriorityQueue<Int>()
    val edges = ArrayList<PriorityQueue<Int>>()
    val used = ArrayList<Int>()
 
    repeat(n) {
        edges.add(PriorityQueue<Int>())
        used.add(0)
    }
 
    for (i in 0 until n - 1) {
        val from = scan.nextInt()
        val to = scan.nextInt()
        used[from - 1]++
        used[to - 1]++
        edges[from - 1].add(to)
        edges[to - 1].add(from)
    }
 
    for (i in 0 until n) {
        if (used[i] == 1) {
            V.add(i + 1)
        }
    }
 
    for (j in 0 until n - 2) {
 
        val v = V.first()
        V.remove(v)
        used[v - 1]--
        val p = edges[v - 1].first()
        used[p - 1]--
        edges[p - 1].remove(v)
        if (used[p - 1] == 1)
            V.add(p)
        print("$p ")
    }
}