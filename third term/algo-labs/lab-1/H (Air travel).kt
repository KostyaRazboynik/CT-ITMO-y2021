import java.util.*
import kotlin.collections.ArrayList
 
private val scan = Scanner(System.`in`)
 
lateinit var edges: ArrayList<ArrayList<Int>>
lateinit var used: ArrayList<Int>
lateinit var color: ArrayList<Int>
lateinit var tout: ArrayList<ArrayList<Int>>
private var T = 0
private var C = 0
private var n = 0
 
fun dfs1(v: Int, k: Int) {
    used[v] = 1
    for (i in 0 until n) {
        if (edges[v][i] <= k && used[i] == 0)
            dfs1(i, k)
    }
    val tmp = ArrayList<Int>()
    tmp.add(T++)
    tmp.add(v)
    tout.add(tmp)
}
 
fun dfs2(v: Int, k: Int) {
    color[v] = C
    for (i in 0 until n) {
        if (edges[i][v] <= k && color[i] == 0)
            dfs2(i, k)
    }
}
 
fun init() {
    T = 0
    C = 0
    used = ArrayList()
    tout = ArrayList()
    color = ArrayList()
 
    repeat(n) {
        used.add(0)
        color.add(0)
    }
}
 
fun comp(m: Int): Boolean {
    init()
 
    for (i in 0 until n) {
        if (used[i] == 0) {
            dfs1(i, m)
        }
    }
 
    tout.sortBy { it[0] }
 
    for (i in tout.reversed()) {
        if (color[i[1]] == 0) {
            if (C > 0)
                return false
            C++
            dfs2(i[1], m)
        }
    }
    return true
}
 
fun main() {
    n = scan.nextInt()
 
    edges = ArrayList()
 
    repeat(n) {
        val tmp = ArrayList<Int>()
 
        repeat(n) {
            tmp.add(0)
        }
        edges.add(tmp)
    }
 
    for (i in 0 until n) {
        for (j in 0 until n)
            edges[i][j] = scan.nextInt()
    }
 
    var l = -1
    var r =  1 shl 30
 
    while(r - l > 1) {
        val m = (l + r) / 2
        if (comp(m))
            r = m
        else
            l = m
    }
 
    println(r)
}