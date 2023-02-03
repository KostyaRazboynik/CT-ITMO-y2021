import java.util.*
import kotlin.math.sqrt
 
private val scan = Scanner(System.`in`)
 
lateinit var vertex: ArrayList<Pair<Int, Int>>
lateinit var edges: ArrayList<Int>
lateinit var minEdges: ArrayList<Double>
lateinit var used: ArrayList<Int>
 
private var n: Int = 0
 
 
fun distance(v: Int, u: Int): Double {
    if (v == u)
        return 1000000000.0
    val a = vertex[v]
    val b = vertex[u]
 
    return sqrt((b.first - a.first).toDouble() * (b.first - a.first).toDouble() + (b.second - a.second).toDouble() * (b.second - a.second).toDouble())
}
 
fun findResult(): Double {
    var result = 0.toDouble()
 
    for (i in 0 until n) {
        var v = -1
        for (j in 0 until n) {
            if ((v == -1 || minEdges[j] < minEdges[v]) && used[j] == 0) {
                v = j
            }
        }
 
        used[v] = 1
        if (edges[v] != -1)
            result += distance(v, edges[v])
 
        for (to in 0 until n) {
            val tmp = distance(v, to)
            if (tmp < minEdges[to]) {
                minEdges[to] = tmp
                edges[to] = v
            }
        }
    }
    return result
}
 
fun main() {
 
    n = scan.nextInt()
 
    used = ArrayList()
    minEdges = ArrayList()
    edges = ArrayList()
    vertex = ArrayList()
 
    repeat(n) {
        edges.add(-1)
        minEdges.add(1000000000.0)
        used.add(0)
    }
 
    minEdges[0] = -1.0
 
    for (i in 0 until n) {
        vertex.add(Pair(scan.nextInt(), scan.nextInt()))
    }
 
    println(findResult())
 
}