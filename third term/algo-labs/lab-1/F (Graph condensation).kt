import java.util.*
 
private val scan = Scanner(System.`in`)
 
lateinit var used: ArrayList<Int>
lateinit var used2: ArrayList<Int>
lateinit var ResultEdges: MutableSet<Pair<Int, Int>>
lateinit var sl_edges: ArrayList<ArrayList<Int>>
lateinit var edges: ArrayList<ArrayList<Int>>
lateinit var order: ArrayList<Int>
lateinit var color: ArrayList<Int>
var C = 0
 
fun dfs1(v: Int) {
    used[v] = 1
    for (i in edges[v]) {
        if (used[i] == 0)
            dfs1(i)
    }
 
    order.add(v)
}
 
fun dfs2(v: Int) {
    used2[v] = 1
    color[v] = C
    for (i in sl_edges[v]) {
        if (used2[i] == 0)
            dfs2(i)
    }
}
 
fun main() {
 
    val n = scan.nextInt()
    val m = scan.nextInt()
 
    sl_edges = ArrayList()
    edges = ArrayList()
    used = ArrayList()
    used2 = ArrayList()
    ResultEdges = mutableSetOf()
    order = ArrayList()
    color = ArrayList()
    C = 1
 
    repeat(n) {
        edges.add(ArrayList())
        sl_edges.add(ArrayList())
        color.add(0)
        used.add(0)
        used2.add(0)
    }
 
    repeat(m) {
        val a = scan.nextInt()
        val b = scan.nextInt()
        edges[a - 1].add(b - 1)
        sl_edges[b - 1].add(a - 1)
    }
 
    for (i in 0 until n) {
        if (used[i] == 0)
            dfs1(i)
    }
 
    for (i in 0 until n) {
        val v = order[n - 1 - i]
        if (used2[v] == 0) {
            dfs2(v)
            C++
        }
    }
 
    for (i in 0 until n) {
        for (j in edges[i]) {
            if (color[i] != color[j])
                ResultEdges.add(Pair(color[i], color[j]));
        }
    }
 
    println(ResultEdges.size)
}