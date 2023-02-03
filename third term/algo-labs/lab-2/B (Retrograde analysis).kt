import java.util.*
 
private val scan = Scanner(System.`in`)
 
 
fun main() {
 
    val t = scan.nextInt()
    var n: Int
    var m: Int
    var out: MutableList<Int>
    var q: Queue<Int>
    var d: MutableList<Int>
    var backEdges: MutableList<MutableList<Int>>
 
    val answer = java.lang.StringBuilder("")
 
    repeat(t) {
        n = scan.nextInt()
        m = scan.nextInt()
        out = MutableList(n) { 0 }
        d = MutableList(n) { -1 }
        q = LinkedList()
        backEdges = MutableList(n) { mutableListOf() }
 
 
        repeat(m) {
            val u = scan.nextInt() - 1
            val v = scan.nextInt() - 1
            backEdges[v].add(u)
            out[u]++
        }
 
        for (i in 0 until n) {
            if (out[i] == 0) {
                d[i] = 0
                q.add(i)
            }
        }
 
        while (q.isNotEmpty()) {
            val v = q.remove()
 
            if (d[v] == 0) {
                for (u in backEdges[v]) {
                    if (d[u] == -1) {
                        d[u] = 1
                        q.add(u)
                    }
                }
            } else {
                for (u in backEdges[v]) {
                    out[u]--
                    if (out[u] == 0) {
                        d[u] = 0
                        q.add(u)
                    }
                }
            }
        }
 
        for (i in d) {
            when (i) {
                0 -> answer.append("SECOND\n")
                1 -> answer.append("FIRST\n")
                else -> answer.append("DRAW\n")
            }
        }
        answer.append("\n")
    }
 
    print(answer)
 
}