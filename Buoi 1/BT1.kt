import kotlin.random.Random

const val max_element = 1000005;
fun solve(n: Int) {
    val totalSum = n.toLong() * (n + 1) / 2

    if (totalSum % 2 != 0.toLong()) {
        println("NO")
        return
    }

    println("YES")

    val halfSum = totalSum / 2
    val arr1 = mutableListOf<Long>()
    val arr2 = mutableListOf<Long>()

    var currentSum = 0

    for (i in n downTo 1) {
        if (currentSum + i <= halfSum) {
            arr1.add(i.toLong())
            currentSum += i
        } else {
            arr2.add(i.toLong())
        }
    }
    for (num in arr1) {
        print("${num} ")
    }
    println()
    for (num in arr2) {
        print("${num} ")
    }
}

fun main() {
    val n = Random.nextInt(1, max_element)
    //val n = 7
    solve(n)
}