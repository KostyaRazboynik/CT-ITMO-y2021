import java.lang.Integer.min
import java.util.Scanner
 
private val scan = Scanner(System.`in`)
 
lateinit var bridges: ArrayList<Int>
lateinit var color: ArrayList<Int>
lateinit var edges: ArrayList<ArrayList<Pair<Int, Int>>>
lateinit var enter: ArrayList<Int>
lateinit var used: ArrayList<Int>
lateinit var up: ArrayList<Int>
 
var T = 0
var C = 0
 
fun dfs(v: Int, p: Int) {
    used[v] = 1
    enter[v] = T
    up[v] = T
    T++
 
    for (i in edges[v]) {
        if (used[i.first] == 0)
            dfs(i.first, i.second)
 
        if (i.second != p)
            up[v] = min(up[v], up[i.first])
    }
 
    if (up[v] == enter[v] && p != -1)
        bridges[p] = 1
}
 
fun dfs2(v: Int, c: Int) {
    color[v] = c
    for (i in edges[v]) {
        if (color[i.first] == 0)
            if (bridges[i.second] == 1) {
                C++
                dfs2(i.first, C)
            } else
                dfs2(i.first, c)
    }
}
 
fun main() {
 
    val n = scan.nextInt()
    val m = scan.nextInt()
 
    bridges = ArrayList()
    color = ArrayList()
    edges = ArrayList()
    enter = ArrayList()
    used = ArrayList()
    up = ArrayList()
 
    repeat(n) {
        color.add(0)
        edges.add(ArrayList())
        enter.add(1000000)
        used.add(0)
        up.add(1000000)
    }
 
    for(i in 0 until m) {
        bridges.add(0)
        val a = scan.nextInt()
        val b = scan.nextInt()
        edges[a - 1].add(Pair(b - 1, i))
        edges[b - 1].add(Pair(a - 1, i))
    }
 
    for (i in 0 until n) {
        if (used[i] == 0)
            dfs(i, -1)
    }
 
    for (i in 0 until n) {
        if (color[i] == 0) {
            C++
            dfs2(i, C)
        }
    }
 
    //val set: MutableSet<Int> = mutableSetOf()
    val mp: MutableMap<Int, Int> = mutableMapOf()
    var count = 0
    println(C)
    for (i in color) {
//        if (!set.contains(i)) {
//            set.add(i)
//            count++
//        }
        if (mp[i] == null) {
            ++count
            mp[i] = count
        }
        print("${mp[i]} ")
 
    }
 
}