import java.util.*
import kotlin.collections.ArrayList
 
private val scan = Scanner(System.`in`)
 
fun main() {
    val n = scan.nextInt()
 
    val P = ArrayList<Int>()
    val V = ArrayList<Int>()
    val queue = PriorityQueue<Int>()
    val used = ArrayList<Int>()
 
    for (i in 0 until n) {
        V.add(i + 1)
        used.add(0)
    }
 
    repeat(n - 2) {
        val a = scan.nextInt()
        used[a - 1]++
        P.add(a)
    }
 
    for (i in 0 until n) {
        if (used[i] == 0)
            queue.add(i + 1)
    }
 
//    for (i in queue)
//        print("$i ")
//    println()
 
    //while (P.size > 0) {
    for (j in 0 until n - 2) {
        val u = P[j]
//        var v = -1
 
//        for (i in V) {
//            if (used[i - 1] == 0) {
//                v = i
//                break
//            }
//        }
        val v = queue.first()
        queue.remove(v)
        println("$v $u")
        used[P[j] - 1]--
        if (used[P[j] - 1] == 0)
            queue.add(P[j])
 
//        for (i in queue)
//            print("$i ")
//        println()
 
        //V.remove(v)
    }
    val a = queue.first()
    queue.remove(a)
    val b = queue.first()
    println("${a} ${b}")
}