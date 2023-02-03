import java.util.*
 
private val scan = Scanner(System.`in`)
 
//private lateinit var result: MutableList<Int>
 
private var n = 0
 
fun find(a: MutableList<MutableList<Int>>): MutableList<Int> {
 
    //var bRes = 0
    val aSize = a.size
    //val bSize = b.size
 
//    var count = 0
//    for (i in a) {
//        if (i.contains(1)) {
//            count++
//            break
//        }
//    }
//
//    if (count == 0) {
//
//    }
 
 
    var u = -1
    var v = -1
    for (i in 0 until aSize) {
        for (j in 0 until aSize) {
            if (a[i][j] == 1) {
                u = j
                v = i
                a[i][j] = 0
                a[j][i] = 0
                break
            }
        }
        if (u != -1)
            break
    }
    if (u == -1) {
        val res = MutableList(n) { 0 }
        res[aSize - 1] = 1
        return res
    }
    val aRes = find(a)
    a[u][v] = 1
    a[v][u] = 1
 
    // Стягивание
 
    val b = MutableList(a.size) {MutableList(a.size) {0} }
 
    for (i in 0 until a.size) {
        for (j in 0 until a[i].size) {
            b[i][j] = a[i][j]
        }
    }
 
 
    for (i in 0 until b.size) {
        if (v != i) {
            b[v][i] = b[v][i] or b[u][i]
            b[i][v] = b[i][v] or b[i][u]
        }
    }
 
    //val c = MutableList(aSize - 1) { MutableList(aSize - 1) { 0 } }
//
//    var ic = 0
//    var jc = 0
//    for (i in 0 until aSize) {
//        if (i == v)
//            continue
//
//        for (j in 0 until aSize) {
//            if (j == v)
//                continue
//            c[ic][jc++] = b[i][j]
//        }
//        jc = 0
//        ic++
//    }
 
    for (i in 0 until b.size) {
        b[i].removeAt(u)
    }
    b.removeAt(u)
 
 
    val bRes = find(b)
 
    val result = MutableList(n) {0}
 
    for (i in 0 until n) {
        result[i] = aRes[i] - bRes[i]
    }
    return result
}
 
fun main() {
 
    n = scan.nextInt()
    val m = scan.nextInt()
 
    val matrix = MutableList(n) { MutableList(n) { 0 } }
    //result = MutableList(n) {0}
 
    for (i in 0 until m) {
        val a = scan.nextInt()
        val b = scan.nextInt()
        matrix[a - 1][b - 1] = 1
        matrix[b - 1][a - 1] = 1
    }
 
//    for (i in matrix) {
//        for (j in i) {
//            print("$j ")
//        }
//        println()
//    }
//
//    println()
 
    val result = find(matrix)
 
    println(n)
 
    for (i in result.reversed()) {
        print("$i ")
    }
    println(0)
 
 
}