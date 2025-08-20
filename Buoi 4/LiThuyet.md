# Generic và Extension Function

## 1. Generic
### 1.1 Generic là gì
- Generic là một tính năng cho phép chúng ta có thể định nghĩa và truy cập các classes, methods, propreties bằng cách sử dụng các kiểu dữ liệu khác nhau mà vẫn sẽ hoạt động giống nhau.
- Một ví dụ và Generic mà chúng ta thường gặp là `Arraylist`, ta có thể truyền nhiều kiểu dữ liệu khác nhau mà các hoạt động của Arraylist vẫn không bị thay đổi:
  - Arraylist<T> list

### 1.2 Tại sao nên sử dụng Generic
- Sử dụng generic giúp việc tái sử dụng code cũng như là cải thiện hiệu năng vì Generic được kiểm tra tại thời gian biên dịch để tránh lỗi trong thười gian chạy

### 1.3 Cách sử dụng của generic và dùng khi nào
- Cách tự tạo 1 generic:
```kotlin
class Student<T>(val data : T)
{
    fun showData()
    {
        println(data)
    }
}
fun main () {
    val student1: Student<Int> = Student(10)
    println(student1.showData)
    val student2 = Student("Phuc")
    println(student2.showData)
}
```
* Một vài quy ước về generic:
    - Tên tham số chung là một chữ cái hoa, để phân biệt các kiểu Generic truyền vào thì phân biệt bằng các chữ cái:
      - T : Type - Kiểu dữ liệu bất kỳ thuộc wrapper class: String, Interger, Long, Float, ...
      - E : Element - Phần tử được sử dụng phổ biến trong Collection Framework
      - K : Key
      - V : Value
      - N : Number - Kiểu số: Interger, Double, Float

* Từ khóa out và in trong Generics
- Khi chúng ta muốn gán Generic type cho bất kì lớp cha vào thì ta cần sử dụng từ khóa `out` và khi chúng ta muốn gán generic type cho bất kì lớp con nào thì chúng ta sử dụng từ khóa `in`
  * Từ khóa `out`:
    ```kotlin
    open class Father()
    class Son() : Father()

    class Person <out T> (val value: T)
    {
        fun get() :T
        {
            return value
        }
    }

    fun main () {
        val sonObject = Person(Son())
        val fatherObject : Person<Father>

        fatherObject = sonObject
    }
    ```

    - Ở ví dụ trên thì 2 đối tượng `sonObject` và `fatherObject` đều tạo từ class Person bằng Generics. Vì ta đã sử dụng từ khóa `out` ở class Person nên việc gán giá trị của `sonObject` cho `fatherObject` có thể làm được
  
  * Từ khóa `in`:
    ```kotlin
    open class Father()
    class Son() : Father()

    class Person <in T> ()
    {
        fun say(value : T){
        println("${value.hashCode()}")
    }
    }

    fun main () {
        val fatherObject : Person<Father> = Person()
        val sonObject : Person<Son>
        sonObject = fatherObject    
    }
    ```

    - Khởi tạo 2 đối tượng fatherObject và sonObject từ class Person, ta thấy sonObject là 1 sub-type của fatherObject và vì đã sử dụng từ khóa in ở class Person nên việc gán giá trị của fatherObject cho sonObject là có thể được

* Type Projections
    - Phương sai loại dự báo
      - Ví dụ:
      ```kotlin
      fun copy(from: Array<Any>, to: Array<Any>) {
          assert(from.size == to.size)
          for (i in from.indices)
              to[i] = from[i]
      }
      fun main() {
          val ints: Array<Int> = arrayOf(1, 2, 3)
          val any = Array<Any>(3) { "" }
          copy(ints, any) // Lỗi
      }
      ```
      - Đây là một ví dụ về việc cố gắng sao chép một đối tượng Interger (là lớp con của Any) sang Any
      - Như ở ví dụ trên 3 class: Son-Father-Person thì muốn gán sonOb vào fatherOb thì khi con tạo đối tượng từ Person qua Generic thì phải thêm `out` vào lớp con hoặc `in` vào lớp cha
      - fun copy(from: Array<out Any>, to: Array<Any>)
      - fun copy(from: Array<Any>, to: Array<in Any>)

    - Dự báo sao:
      - Trong trường hợp ta không biết kiểu dữ liệu chính xác nhưng ta muốn in các phần tử trong Collection thì là dùng từ khóa `*`
      - Ví dụ :
      ```kotlin
      val list : MutableList<*> = mutableListOf(1,2,3,4)
      ```
      -> Khi có keyword là `*` thì collection này chỉ có thể đọc được, không ghi được


* Generics Funtion
    - Các hàm cũng có thể để ở dạng Generics funtion với type argument sau thê hàm.
    
    ```kotlin
    fun <T> toString (input: T) : String
    {
        return input.toString()
    }

    fun main() {
        val first = toString(123)
        print(first)
        val second = toString<Float>(1.23)
        print(second)
    }
    ```
* Generic Constraints
    - Tập hợp tất cả các kiểu có thể thay thế cho 1 tham số nhưng bị hạn chế bởi ràng buộc
    - Kiểu ràng buộc phổ biến nhất là giới hạn trên
    - Ràng buộc hàm
    ```kotlin
    fun <T : Comparable<T>> sort(list: List<T>){}
    ```
    - Kiểu được chỉ định sau dấu hai chấm là giới hạn trên, cho biết rằng chỉ ó kiểu con của Comparable<T> mới có thể thay thế vào
    ```kotlin
    sort(listOf(1,2,3)) // ok
    sort(listOf(Hashmap<Int,String>)()) // Error
    ```

    - Ràng buộc kiểu:
        - Giới hạn trên mặc định của KDL là `Any`. Ta có thể giới hạn kiểu tham số có thể truyền vào bằng `where`
        ```kotlin
        fun <T> copyWhenGreater(list: List<T>, threshold: T): List<String>
            where T : CharSequence,
                T : Comparable<T> {
            return list.filter { it > threshold }.map { it.toString() }
        }
        ```
        - Kiểu dữ liệu được truyền vào phải thỏa mãn `where` đồng thời tất cả các điều kiện của mệnh đề. Ở ví dụ trên thì T phải thỏa mãn cả 2 là CharSequence ở para 1 và Comparable ở para 2

## 2. Extension function
- Extension function trong Kotlin là function có thể được khai báo trong class/file hiện tại mà không sửa đổi các class tùy chỉnh xác định trước khác

```kotlin
class CheckNumber {
    fun greaterThan10 (X: Int) : Boolean{
        return x > 10
    }
}
fun main () {
    val x = 13
    var checkNumber = CheckNumber()
    println(checkNumber.greaterThan10(x))
}
```

- Nếu chúng ta định tạo 1 checkNumberLessThan10, ta sẽ tạo ra function kiểm tra number < 10. Ta sẽ không sửa class CheckNumber mà sẽ viết thêm 1 Extension Function cho nó
```kotlin
class CheckNumber {
    fun greaterThan10 (X: Int) : Boolean{
        return x > 10
    }
}
fun CheckNumber.lessThan10(x: Int): Boolean{
    return x<10
}

fun main () {
    val x = 13
    var checkNumber = CheckNumber()
    println(checkNumber.greaterThan10(x))
    println(checkNumber.lessThan10(x))
}
```
- Extension Function được xử lý tĩnh:
  - Extension Function thực chất không sửa đổi các lớp chúng mở rộng. Bằng cách định nghĩa phần mở rộng, bạn không thể chèn, thêm thành viên mới vào lớp, mà chỉ có các hàm mới có thể gọi bằng ký hiệu .biến
  - Nếu class có hàm thành phần và một extension function có cùng 1 kiểu nhận, cùng tên và áp dụng được cho các đối số đã cho thì hàm trong class luôn thắng
  ```kotlin
  class Example {
    fun printFunctionType() { println("Class method") }
    }

    fun Example.printFunctionType() { println("Extension function") }

    Example().printFunctionType()
  ```

## 3. Scope Function
- Thư viện Kotlin cung cấp một số hàm có mục đích duy nhất là thực thu như vậy trên một đối tượng với một Biểu thứ Lamb*-*da có điều kiện. Trong phạm vi này, bạn có thể truy cập đối tượng mà không cần tên của nó. Các hàm như vậy gọi là Scope Function. Có 5 hàm là : `let`, `run`, `with`, `apply`, `also`
- Tất cả các hàm này đều thực hiện cùng một hành động: thực thi một khối mã trên một đối tượng
```kotlin
Person("Alice", 20, "Amsterdam").let {
    println(it)
    it.moveTo("London")
    it.incrementAge()
    println(it)
}
```
- Nếu không có Scope Function thì ta sẽ phải truy cập qua tên đối tượng
```kotlin
val alice = Person("Alice", 20, "Amsterdam")
println(alice)
alice.moveTo("London")
alice.incrementAge()
println(alice)
```
- Việc lựa chọn một Scope Function phù hợp với trường hợp sử dụng rất quan trọng. Ta sẽ phải phân biệt chúng thông qua Context Object và Return value
  - Context object: các để đề cập đến đối tượng (this - it)
  - Return value: giá trị trả về (context object - lambda result)
- Ta có thể phân loại chúng dựa trên 2 đặc điểm trên:

|Function|Object Reference|Return value|Is extension function|
|----------------|------------|---------------------|------|
|let|it|lambda result|yes|
|run|this|lambda result|yes|
|run|-|lambda result|No: Không gọi mà không có đối tượng|
|with|this|lambda result|No: Lấy đối tượng là đối số|
|apply|this|context object|yes|
|also|it|context object|yes|

- Một vài ví dụ về việc chọn lambda function:
    - Thực thi lambda trên các đối tượng không thể rỗng: let
    - Giới thiệu một biểu thức dưới dạng biến trong phạm vi cục bộ: let

    - Cấu hình đối tượng: apply

    - Cấu hình đối tượng và tính toán kết quả: run

    - Chạy các câu lệnh trong đó một biểu thức được yêu cầu: không mở rộng run

    - Hiệu ứng bổ sung: also

    - Nhóm các lệnh gọi hàm trên một đối tượng: with

* Context Object (this - it)
- Trong Scope Function, mỗi context object có thể tham chiếu ngắn gọn đến đối tượng bằng từ khóa this-it thay vì đến tên thực của nó
  - This:
    - Các hàm run, apply, with tham chiếu đến context object thông qua `this`. Sử dụng `this` để truy cập đến các hàm trong class
    - Ta có thể lược bớt `this` khi truy cập đến các biến, hàm trong class
    ```kotlin
    class Person(val name: String)
    {
        var age: Int = 0
        var city: String = ""
    }

    fun main (){
        val phuc = Person("Phuc").apply{
            age = 20
            city = "Hai Phong"
        }
    }
    ```
    - It:
      - let và also tham chiếu tới context object và lambda argument. Nếu tên đối số không xác định, đối tượng được truy cập bằng tên mặc định it. it ngắn hơn this và các biểu thức với it thường dễ đọc hơn
      - Khi truy cập đối tượng ngữ cảnh thông qua it tốt hơn khi đối tượng chủ yếu sử dụng làm đối số trong các lệnh gọi hàm
      ```kotlin
        fun getRandomInt(): Int {
            return Random.nextInt(100).also {
                writeToLog("getRandomInt() generated value $it")
            }
        }

        val i = getRandomInt()
        println(i)
      ```
* Return value
    - Các giá trị trả về của từng Scope Fuction là khác nhau
        - `apply` , `also` : Context Object
        - `let` , `run` , `with` : Lambda Result
    - Context Object :
        - `apply` và `also` trả về Context Object. Do đó, chúng có thể được đưa vào chuỗi cuộc gọi như các bước phụ: bạn có thể tiếp tục chuỗi các lệnh gọi hàm trên cùng 1 đối tượng
        ```kotlin
        val numberList = mutableListOf<Double>()
        numberList.also { println("Populating the list") }
            .apply {
                add(2.71)
                add(3.14)
                add(1.0)
            }
            .also { println("Sorting the list") }
            .sort()
        ```
    - Lambda Result:
        - `let`, `run`, và `with` Trả về lambda result. Vì vậy, bạn có thể sử dụng chúng khi gán kết quả cho một biến, chuỗi các hoạt động trên kết quả,
        ```kotlin
        val numbers = mutableListOf("one", "two", "three")
        val countEndsWithE = numbers.run { 
            add("four")
            add("five")
            count { it.endsWith("e") }
        }
        println("There are $countEndsWithE elements that end with e.")
        ```
### 3.1 Các scope function
- let:
    - Hàm `let` thường được sử dụng cung cấp các null safety, thực thi một khối mã chứa giá trị không null. 
    ```kotlin
    fun main() {
        var name: String? = null
        name?.let {
            println(name) // Output rỗng
        }
    }
    ```
- with:
    - Hàm `with` thường được sử dụng để gọi các hàm trên đối tượng khi bạn không cần sử dụng kết quả được trả về. Trong phần hàm `with` có thể hiểu là "Với đối tượng này, hãy thực hiện như sau"
    ```kotlin
    val numbers = mutableListOf("one", "two", "three")
    with(numbers) {
        println("'with' is called with argument $this")
        println("It contains $size elements")
    }
    ```
    - Có thể sử dụng `with` để giới thiệu một đối tượng trợ giúp có các thuộc tính hoặc hàm được sử dụng để tính toán một giá trị
    ```kotlin
    val numbers = mutableListOf("one", "two", "three")
    val firstAndLast = with(numbers) {
        "The first element is ${first()}," +
        " the last element is ${last()}"
    }
    println(firstAndLast)
    ```

- run
    - Tương tự như `with` nhưng nó được thực hiện như một Extention fuction. Có thể gọi nó trên Context Object bằng dấu .
    - `run` hữu ích khi Lambda của bạn vừa khởi tạo các đối tượng vừa tính toán giá trị trả về. Cách dùng tương tự như let chỉ khác let dùng it thay vì this.
    ```kotlin
    class Person(var name: String, var age : Int){
    }
    fun main() {
        val phuc = Person("Phuc", 20)
        val runResult = phuc.run {
            name = "Hai"
            age = 19
            "Name is: ${name}, age: ${age} "
        }
        println(runResult)
    }
    ```
    
- apply:
    - Hàm `apply` có thể được sử dụng hầu hết để khởi tạo các thành phần của receiver object.
    - Nó nhận object làm **input** và thực thi khối lệnh, khối lệnh sau đó sẽ thay đổi giá trị của các thuộc tính object.
    ```kotlin
    class Person(var name: String, var age : Int){
    }
    fun main() {
        val phuc = Person("Phuc", 20)
        val runResult = phuc.apply {
            name = "Hai"
            age = 19
        }
        println(runResult.name)
    }
    ```
- also:
    - Nó được sử dụng khi chúng ta phải thực hiện các thao tác bổ sung khi chúng ta đã khởi tạo các thành viên đối tượng.
    ```kotlin
    val numbers = mutableListOf("one", "two", "three")
    numbers
        .also { println("The list elements before adding new one: $it") }
        .add("four")
    ```