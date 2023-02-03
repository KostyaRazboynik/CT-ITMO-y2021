import java.util.*
import kotlin.collections.ArrayList
 
private val scan = Scanner(System.`in`)
 
lateinit var edges: ArrayList<ArrayList<Int>>
lateinit var used: ArrayList<Int>
lateinit var win: ArrayList<ArrayList<Int>>
 
fun dfs(v: Int) {
    used[v] = 1
    for (i in edges[v]) {
        if (used[i] == 0)
            dfs(i)
 
        win[0][v] = win[0][v] or win[1][i].inv()
        win[1][v] = win[1][v] or win[0][i].inv()
    }
}
 
fun main() {
    val n = scan.nextInt()
    val m = scan.nextInt()
    val s = scan.nextInt() - 1
 
    edges = ArrayList()
    win = ArrayList()
    used = ArrayList()
    val tmp: ArrayList<Int> = ArrayList()
 
    repeat(n) {
        tmp.add(0)
        used.add(0)
        edges.add(ArrayList())
    }
 
    win.add(tmp)
    win.add(tmp)
 
    repeat(m) {
        val a = scan.nextInt() - 1
        val b = scan.nextInt() - 1
        edges[a].add(b)
    }
 
    dfs(s)
 
    if (win[0][s] != 0)
        println("First player wins")
    else
        println("Second player wins")
}