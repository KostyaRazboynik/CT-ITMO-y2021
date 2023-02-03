import java.util.*
import kotlin.math.min
 
private val scan = Scanner(System.`in`)
 
private lateinit var dots: TreeSet<Int>
private lateinit var edges: ArrayList<ArrayList<Int>>
private lateinit var enter: ArrayList<Int>
private lateinit var used: ArrayList<Int>
private lateinit var ret: ArrayList<Int>
private var T = 0
 
private fun dfs(v: Int, p: Int) {
    used[v] = 1
    enter[v] = T
    ret[v] = T++
    var count = 0
 
    for (i in edges[v]) {
        if (i == p)
            continue
 
        if (used[i] == 0) {
            dfs(i, v)
            ret[v] = min(ret[v], ret[i])
            if (ret[i] >= enter[v] && p != -1)
                dots.add(v)
            count++
        } else
            ret[v] = min(ret[v], enter[i])
    }
 
    if (p == -1 && count > 1)
        dots.add(v)
}
 
 
fun main() {
 
    val n = scan.nextInt()
    val m = scan.nextInt()
 
    dots = TreeSet()
    edges = ArrayList()
    enter = ArrayList()
    used = ArrayList()
    ret = ArrayList()
 
    repeat(n) {
        edges.add(ArrayList())
        enter.add(20001)
        used.add(0)
        ret.add(20001)
    }
 
    var i = 0
    repeat(m) {
        val a = scan.nextInt()
        val b = scan.nextInt()
        edges[a - 1].add(b - 1)
        edges[b - 1].add(a - 1)
        i++
    }
 
    for (j in 0 until n) {
        if (used[j] == 0)
            dfs(j, -1)
    }
 
    println(dots.size)
    for (el in dots) {
        print("${el + 1} ")
    }
}