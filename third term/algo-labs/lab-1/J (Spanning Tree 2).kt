import java.util.*
import kotlin.collections.ArrayList
 
private val scan = Scanner(System.`in`)
 
lateinit var parent: ArrayList<Int>
lateinit var size: ArrayList<Int>
lateinit var graph: ArrayList<Triple<Int, Int, Int>>
 
 
fun dsu(v: Int): Int {
    if (v != parent[v])
        parent[v] = dsu(parent[v])
    return parent[v]
}
 
fun uniteDsu(v1: Int, u1: Int) {
    var u = dsu(u1)
    var v = dsu(v1)
 
    if (size[u] < size[v]) {
        val tmp = u
        u = v
        v = tmp
    }
    parent[v] = u
    size[u] += size[v]
}
 
fun main() {
 
    val n = scan.nextInt()
    val m = scan.nextInt()
 
    parent = ArrayList()
    size = ArrayList()
    graph = ArrayList()
 
    for (i in 0 until n) {
        parent.add(i)
        size.add(i)
    }
 
    for (i in 0 until m) {
        graph.add(Triple( scan.nextInt() - 1, scan.nextInt() - 1, scan.nextInt()))
    }
 
    graph.sortBy { it.third }
 
    var res: Long = 0
    for (i in 0 until m) {
        if (dsu(graph[i].second) != dsu( graph[i].first)) {
            res += graph[i].third
            uniteDsu( graph[i].first, graph[i].second)
        }
    }
    println(res)
}