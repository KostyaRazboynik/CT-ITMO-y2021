import java.util.Scanner
 
val scan = Scanner(System.`in`)
 
lateinit var used: ArrayList<Int>
lateinit var ans: ArrayList<Int>
lateinit var graph: ArrayList<ArrayList<Int>>
var cycle: Boolean = false
 
fun dfs(v: Int) {
    used[v] = 1
    for (i in graph[v]) {
        if (used[i] == 0)
            dfs(i)
        else if (used[i] == 1)
            cycle = true
        if (cycle)
            return
    }
    ans.add(v + 1)
    used[v] = 2
}
 
fun main() {
 
    val n = scan.nextInt()
    val m = scan.nextInt()
 
    graph = ArrayList()
    used = ArrayList()
    ans = ArrayList()
 
    repeat(n) {
        graph.add(ArrayList())
        used.add(0)
    }
    
    repeat(m) {
        graph[scan.nextInt() - 1].add(scan.nextInt() - 1)
    }
 
    for (i in 0 until n) {
        if (used[i] == 0)
            dfs(i)
        if (cycle)
            break
    }
 
    if (!cycle) {
        for (i in ans.reversed())
            print("$i ")
    }
    else
        println(-1)
}