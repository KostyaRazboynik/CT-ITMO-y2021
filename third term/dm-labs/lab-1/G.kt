import java.util.*
import kotlin.collections.ArrayList
 
private val scan = Scanner(System.`in`)
 
lateinit var color: ArrayList<Int>
lateinit var graph: ArrayList<ArrayList<Int>>
 
 
fun dfs(v: Int, k: Int) {
    if (color[v] != 0)
        return
 
    color[v] = -1
    val usedColors = ArrayList<Int>()
    repeat(k) {
        usedColors.add(1)
    }
 
    for (i in graph[v]) {
        dfs(i, k)
        if (color[i] > 0)
            usedColors[color[i] - 1] = 0
    }
 
    for (i in 0 until k) {
        if (usedColors[i] > 0) {
            color[v] = i + 1
            return
        }
    }
}
 
fun main() {
 
    val n = scan.nextInt()
    val m = scan.nextInt()
 
    graph = ArrayList()
    color = ArrayList()
 
    repeat(n) {
        graph.add(ArrayList())
        color.add(0)
    }
 
    repeat (m) {
        val a = scan.nextInt()
        val b = scan.nextInt()
        graph[a - 1].add(b - 1)
        graph[b - 1].add(a - 1)
    }
 
//    for (i in g) {
//        for (j in i) {
//            print("$j ")
//        }
//        println()
//    }
 
   var k = 0
    for (i in graph) {
        if (i.size > k)
            k = i.size
    }
 
    if (k % 2 == 0)
        k++
 
 
    var first = 0
    for (i in 0 until n) {
        if (graph[i].size < k) {
            first = i
            break
        }
    }
 
    dfs(first, k)
 
 
    println(k)
 
    for (i in color)
        println(i)
}