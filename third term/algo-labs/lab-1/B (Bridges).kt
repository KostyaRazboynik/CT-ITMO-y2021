import java.util.*
import kotlin.math.min
 
private val scan = Scanner(System.`in`)
 
private lateinit var bridges: ArrayList<Int>
private lateinit var edges: ArrayList<ArrayList<Pair<Int, Int>>>
private lateinit var enter: ArrayList<Int>
private lateinit var used: ArrayList<Int>
private lateinit var ret: ArrayList<Int>
private var T = 0
 
private fun dfs(v: Int, p: Int) {
    used[v] = 1
    enter[v] = T
    ret[v] = T++
 
    for (i in edges[v]) {
        if (used[i.first] == 0)
            dfs(i.first, i.second)
 
        if (i.second != p)
            ret[v] = min(ret[v], ret[i.first])
    }
 
    //println("${ret[v]} ${enter[v]} $p $T");
 
    if (ret[v] == enter[v] && p != -1)
        bridges.add(p)
}
 
 
fun main() {
 
    val n = scan.nextInt()
    val m = scan.nextInt()
 
    bridges = ArrayList()
    edges = ArrayList()
    enter = ArrayList()
    used = ArrayList()
    ret = ArrayList()
 
    repeat(n) {
        edges.add(ArrayList())
        enter.add(0)
        used.add(0)
        ret.add(200000)
    }
 
    var i = 0
    repeat(m) {
        val a = scan.nextInt()
        val b = scan.nextInt()
        edges[a - 1].add(Pair(b - 1, i))
        edges[b - 1].add(Pair(a - 1, i++))
    }
 
    for (j in 0 until n) {
        if (used[j] == 0)
            dfs(j, -1)
    }
 
 
    println(bridges.size)
    for (el in bridges.sorted()) {
        print("${el + 1} ")
    }
}