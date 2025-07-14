import kotlin.random.Random

const val max_element = 1000005;
fun solve(n : Int){
    if (n == 1 || n == 2)
    {
        println("N0 Solution")
    }
    else if(n == 3)
    {
        println("2, 4, 1, 3")
    }
    else
    {
        var chan: MutableList<Int> = mutableListOf()
        var le: MutableList<Int> = mutableListOf()
        for (num in 1..n)
        {
            if(num % 2 == 0) chan.add(num)
            else le.add(num)
        }
        print(chan.joinToString())
        print(", ")
        print(le.joinToString())
    }
}

fun main() {
    val n = Random.nextInt(1, max_element)
    //val n = 7
    solve(n)
}