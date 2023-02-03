import java.util.*
 
private val scan = Scanner(System.`in`)
 
private var win = MutableList(101) { 0 }
private var stones = 0
 
private fun setM(i: Int) {
    if (stones > 0)
        win[i] = 0
    else
        win[i] = 1
}
 
 
fun main() {
 
    val n = scan.nextInt()
 
    for (i in 3..n) {
        if (i % 3 == 0) {
            stones = win[i - 1] + win[i - 2]
            setM(i)
        } else if (i % 3 == 1) {
            stones = win[i - 1] + win[i - 3]
            setM(i)
        } else {
            stones = win[i - 1] + win[i - 2] + win[i - 3]
            setM(i)
        }
    }
 
    if (win[n] != 1)
        println("1")
    else
        println("2")
}