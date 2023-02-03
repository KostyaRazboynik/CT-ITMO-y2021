import java.util.*
import kotlin.random.Random
 
 
private val scan = Scanner(System.`in`)
 
lateinit var edges: MutableList<MutableList<Int>>
lateinit var cycle: MutableList<Int>
 
 
fun shuffle(list: MutableList<Int>)
{
    // start from the end of the list
    for (i in list.size - 1 downTo 1)
    {
        // get a random index `j` such that `0 <= j <= i`
        val j = Random.nextInt(i + 1)
 
        // swap element at i'th position in the list with the element at j'th position
        val temp = list[i]
        list[i] = list[j]
        list[j] = temp
    }
}
 
fun main() {
 
    val n = scan.nextLine().toInt()
    val order = MutableList(n) { 0 }
 
    edges = MutableList(n) { MutableList(n) { 0 } }
    cycle = MutableList(0) { 0 }
 
    scan.nextLine()
    for (i in 1 until n) {
        order[i] = i
        val s = scan.nextLine()
        for (j in s.indices) {
            if (s[j] == '1')
                edges[i][j] = 1
            else
                edges[j][i] = 1
        }
    }
 
    while (true) {
        cycle.clear()
        //ans.add(order[0])
        shuffle(order)
        for (i in 0 until n) {
            var added = 0
            for (j in 0 until i) {
                if (edges[cycle[j]][order[i]] == 0) {
                    cycle.add(j, order[i])
                    added = 1
                    break
                }
            }
 
            if (added == 0)
                cycle.add(order[i])
        }
 
        if (edges[cycle.last()][cycle[0]] == 1)
            break
    }
 
    for (i in cycle)
        print("${i + 1} ")
}