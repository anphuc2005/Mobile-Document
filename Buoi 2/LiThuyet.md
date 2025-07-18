# Class và Special Sles trong Kotlin


## 1. Class
### 1.1 Acces modifier
- Access Modifier là một từ khóa để xác định phạm vi có thể truy cập của một đối tượng
- Trong Java thì ta đã quen với các kiểu Access Modifier là private (trong class), public (tất cả mọi nơi), protected (lớp con được kế thừa bởi cha có thể dùng các biến, hàm được khai báo protected), default (Mặc định trong cùng 1 class, package)
- Trong kotlin thì cũng có 4 loại
    - private
    - protected
    - internal
    - public

- Private:
    - Khai báo `private` sẽ chỉ có thể truy cập bên trong class đó
    ```kotlin
    private class Human
    {
        private val name: String = "Phuc"
    }
    fun main() {
        val phuc = Human()
        print(phuc.name) // lỗi vì name và private
    }
    ```

    - Trong Kotlin, không giống như Java, mình không óc hàm sẵn getter/setter để truy cập biến private hoặc hàm private từ bên ngoài class — private thực sự là private, chỉ dùng được trong nội bộ class hoặc file, và không thể truy cập từ bên ngoài bằng bất kỳ cách nào thông thường. Nhưng mình vẫn có thể tự viết để lấy biến cũng như sửa đổi
    ```kotlin
    private class Human
    {
        private var name: String = "Phuc"

        fun getName() : String
        {
            return name
        }

        fun setName(otherName : String)
        {
            name = otherName
        }
    }
    fun main() {
        val phuc = Human()
        phuc.setName("Hai")
        println(phuc.getName())
    }
    ```
- Protected:
    - Khai báo `protected` có thể được truy cập bên trong class và ở trong các class con
    ```kotlin
    open class Human {
        protected var name: String = "Human"
        protected var age: Int = 0
    }

    private class Phuc : Human() {
        fun showName() {
            println(name + " ${age}")
        }
    }

    fun main() {
        val phuc = Phuc()
        phuc.showName()
    }
    ```

- Internal
    - Các khai báo được đánh dấu với chỉ định truy cập internal có thể được truy cập ("nhìn thấy") mọi nơi trong cùng 1 module. 1 module là 1 tập các file Kotlin được biên dịch cùng với nhau

    Human.kt
    ```kotlin
    internal open class Human {
        internal var name: String = ""

        internal fun sayHello() {
            println("Hello, my name is $name")
        }
    }
    ```

    Main.kt
    ```kotlin
    internal class Phuc : Human() {
        fun show() {
            name = "Phuc"
            sayHello()
        }
    }

    fun main() {
        val phuc = Phuc()
        phuc.show()
    }

    ```
- Public:
    - Các khai báo được đánh dấu với chỉ định truy cập public có thể được truy cập ("nhìn thấy") mọi nơi. public là chỉ định truy cập mặc định trong Kotlin (1 khai báo mà không có chỉ định truy cập thì mặc định là public)
    ```kotlin
    class Phuc()
    {
        val name = "Phuc"
    }

    fun main() {
        val phuc = Phuc()
        println(phuc.name)
    }
    ```

### 1.2 Constructor
- Constructor là hàm dùng đề tự động khởi tạo giá trị cho đối tượng, khi đối tượng được sinh ra
- Ở trong Kotlin có 2 kiểu constructor là: primary constructor (hàm tạo chính) và secondart constructor (hàm tạo thứ cấp)
- Primary Constructor:
    - Hàm tạo chính là một phần của tiêu đề lớp, được đặt hay sau lên lớp
    ```kotlin
    class Animal private constructor(val name: String) {}
    ```
    - Trong trường hợp không có access modifier thì có thể rút gọn
    ```kotlin
    class Animal (val name: String) {}
    ```
    - Các primary constructor không thể chứa code. Nếu muốn thực hiện các logic code thì khai báo trong `init{}`
    ```kotlin
    class Animal private constructor(val name: String) {
        init{
            //logic code
        }
    }
    ```
    - Lưu ý với initialization blocks ( init { }):

        - Initialization blocks có liên quan đến primary constructor
        - Cho dù bạn định nghĩa một cách rõ ràng một primary constructor hay không, mỗi initialization blocks được xác định sẽ chạy khi class của bạn được khởi tạo
        - Nếu có nhiều hơn một initialization blocks được xác định thì sau đó nó sẽ thực hiện theo thứ tự mà chúng xuất hiện trong body class. Chạy theo code từ trên xuống dưới.
        - Fields xuất hiện trong primary constructor có thể được tham chiếu từ các initialization blocks (trong ví dụ trên bạn có thể thấy rằng trường tên được tham chiếu trong initialization blocks)
        - Trong trường hợp bạn đã xác định bất kỳ secondary constructors sau đó lưu ý rằng các initialization blocks được xác định sẽ thực hiện trước thời điểm thực hiện body của bất kỳ secondary constructor nào.

- Secondary constructor:
    - Secondary constructor được đặt trong thân class
    - Có thể có nhiều secondary constructor cùng nằm trong 1 class
    - Phân biệt các Se_constructor bằng đối số truyền vào
    ```kotlin
    class Animal()
    {
        constructor()
        {
            println("Đây là constructor không có đối số")
        }
        constructor(name: String, breed: String)
        {
            println("Đây là constructor có 2 đối số)
        }
    }
    ```
    - Lưu ý về secondary constructors:

        - Chúng phải được bắt đầu bằng keyword constructor
        - Chúng phải gọi các primary constructor trực tiếp hoặc gián tiếp thông qua một secondary constructor. Gọi các primary constructor là thực hiện với các từ khóa this như trong ví dụ.
        ```kotlin
        class Animal (val name: String) {
            constructor(animal: Animal) : this(animal.name)
        }

        val animal01 = Animal(name = "Dog")
        val animal02 = Animal(animal01)

        ```
        - Initializer blocks sẽ luôn luôn được thực hiện trước thời điểm chạy của phần thân secondary constructors
        - Các parameters tại secondary constructors quy định sẽ không trở thành thuộc tính hoặc các fields của class. Chúng không thể được bắt đầu bằng var hoặc val. Nói cách khác, bạn sẽ cần phải gán những parameters thông qua fields hoặc làm điều gì đó với họ trong phần thân của secondary constructor.
        - Giải thích:
            - primary constructor cho phép bạn khai báo và khởi tạo fields (thuộc tính) một cách ngắn gọn bằng cách dùng val hoặc var ngay trong phần khai báo
            ```kotlin
            class Person(val name: String, var age: Int) // nó sẽ thành biến của lớp person
            ```
            - Không thể dùng val hay var trong danh sách tham số. Do đó, các tham số trong secondary constructor không tự động trở thành thuộc tính. Nếu bạn muốn dùng chúng để khởi tạo giá trị cho các thuộc tính, bạn phải tự gán trong phần thân constructor.
            ```kotlin
            class User {
                var name: String
                var age: Int

                constructor(name: String, age: Int) {
                    this.name = name
                    this.age = age
                }
            }
            ```

### 1.3 Init block
- Khối init (init block) là một khối mã được thực thi ngay sau khi hàm tạo chính (primary constructor) được thực thi. Nó được sử dụng để thực hiện các tác vụ khởi tạo chung cho lớp, đặc biệt khi không thể thực hiện trực tiếp trong hàm tạo chính. 
- Khối init luôn được thực hiện khi gọi bất kì một constructor nào, theo tứ tự là init block -> secondary constructor
- Khối lệnh init block được thực hiện tuần tự từ trên xuống dưới

### 1.4 Class Members
- Class trong Kotlin bao gồm:
    - Constructor
    - Function
    - Properties
    - Nested and Inner Classes (Lớp lồng nhau và lớp bên trong):
    Lớp ngoài
    ```kotlin
    class Outer {
        val outerValue = "Outer Class"

        class Nested {
            fun nestedFunction() = "Hello from Nested Class"
        }
    }

    fun main() {
        val nested = Outer.Nested()
        println(nested.nestedFunction())  // Output: Hello from Nested Class
    }

    ```
    Lớp trong
    ```kotlin
    class Outer {
        val outerValue = "Outer Class"

        inner class Inner {
            fun innerFunction() = "Accessing from Inner: $outerValue"
        }
    }

    fun main() {
        val outer = Outer()
        val inner = outer.Inner()
        println(inner.innerFunction())  // Output: Accessing from Inner: Outer Class
    }

    ```
    - Object Declarations (khâi báo đối tượng)

- Tìm hiểu về Object:
    - Singleton là một mẫu hướng đối tượng trong đó một class chỉ có thể có một instance (đối tượng).

    - Kotlin cung cấp một cách dễ dàng để tạo các singleton bằng cách sử dụng tính năng khai báo đối tượng. Để làm được điều đó, chúng ta sử dụng từ khóa object.
    ```kotlin
    object Dog{
        val age: Int = 10
        val name: String = "Bok-i"
        fun bark()
        {
            println("Gau Gau)
        }
    }
    fun main()
    {
        Dog.bark()
    }
    ```

### 1.5 Companion object
- Trong Kotlin, companion object là một cơ chế cho phép bạn định nghĩa các thành viên (properties, methods) trong phạm vi của một đối tượng đặc biệt gọi là companion object của lớp đó. Các thành viên trong companion object có thể được truy cập trực tiếp thông qua tên của lớp mà không cần tạo một thể hiện của lớp đó.

- Để khai báo một companion object trong Kotlin, bạn sử dụng từ khóa companion object sau định nghĩa lớp.

```kotlin
class MyClass {
    companion object {
        val appName = "Kotlin App"
        fun greet() = "Welcome to $appName"
    }
}

fun main() {
    // Không cần tạo đối tượng MyClass
    println(MyClass.appName)        // Kotlin App
    println(MyClass.greet())        // Welcome to Kotlin App
}

```

## 2. Special class
### 2.1 Data class
- Data class được định nghĩa là 1 lớp dùng để lưu trữ dữ liệu. Mỗi khi được khai báo, Data class sẽ biên dịch tự động ra các phương thức:
    - Getter/ Setter
    - equals()/hashcode()
    - toString()
    - componentN()
    - copy()
- Cú pháp : data class <Tênclass> (primary constructor)
```kotlin
data class Student(val name: String, val id: String)

fun main() {
    val phuc = Student("Phuc", "123")
    println(phuc.name)
    println(phuc.id)
    println(phuc.equals(Student("Phuc", "123")))
    println(phuc.hashCode())
    println(phuc.component1())
    val hai = phuc.copy(id = "345")
    println(hai.id)
}
```

output:
Phuc
123
true
77144140
Phuc
345

- Một số quy tắc tạo data class
    - Constructor chính phải được tạo ra với ít nhất 1 tham số
    - Tham số của constructor nên được đánh dấu là val hoặc var
    - Data class không thể là abstract, open, sealed hoặc inner
    - Data class không nên kế thừa từ lớp khác (nhưng vẫn có thể implement interfaces)

- Một số data class được xây dựng sẵn:
    - Kotlin cũng cung cấp các data classes Pair và Triple cho các xử lý chung.
    ```kotlin
    val pair: Pair<Int, String> = Pair(10, "Ten")
    val triple: Triple<Int, String, Boolean> = Triple(1, "One", true)
    ```
### 2.2 Enum class
- Enum class là một kiểu dữ liệu (data type) bao gồm một tập các giá trị được đặt tên.

- Mỗi hằng số enum là một object, được phân cách nhau bởi dấu phẩy.
```kotlin
enum class Direction {
   NORTH, SOUTH, WEST, EAST
}
```

```kotlin
enum class Color(val r: Int,val g: Int, val b:Int){
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    BLUE(0, 0, 255);

    fun grb()
    {
        println("{$r, $g, $b}")
    }
}
fun main(){
    val color = Color.BLUE
    color.grb()
}
```

### 2.3 Sealed class
- Một lớp niêm phong định nghĩa một tập hợp các lớp con bên trong nó. Lớp niêm phong được sử dụng khi biết trước rằng một kiểu sẽ tuân theo một trong các kiểu lớp con.

- Để khai báo sealed class sử dụng từ khóa sealed. Một sealed class được ngầm định là abstract do đó không thể được khởi tạo.
```kotlin
sealed class Demo {
    class A : Demo() {
        fun display() {
            println("Subclass A of Sealed class Demo")
        }
    }
    class B : Demo() {
        fun display() {
            println("Subclass B of Sealed class Demo")
        }
    }
}

fun main(args: Array<String>) {
    val obj = Demo.B()
    obj.display()
    val obj1 = Demo.A()
    obj1.display()
}
```
Kết quả:

Subclass B of Sealed class Demo
Subclass A of Sealed class Demo
- Lưu ý: Tất cả các lớp con của lớp niêm phong phải được định nghĩa trong cùng một tệp Kotlin. Tuy nhiên, không cần thiết phải định nghĩa chúng bên trong lớp niêm phong, chúng có thể được định nghĩa trong bất kỳ phạm vi nào mà lớp niêm phong có thể nhìn thấy.

- Sử dụng sealed class với biểu thức when:
```kotlin
sealed class Shape{
    class Circle(var radius: Float): Shape()
    class Square(var length: Int): Shape()
    object Rectangle: Shape()
    {
        var length: Int = 0
        var breadth : Int = 0
    }
}

fun eval(e: Shape) =
        when (e) {
            is Shape.Circle -> println("Circle area is ${3.14*e.radius*e.radius}")
            is Shape.Square -> println("Square area is ${e.length*e.length}")
            Shape.Rectangle -> println("Rectangle area is ${Shape.Rectangle.length*Shape.Rectangle.breadth}")
        }
```|   |   |
|---|---|
|   |   |

Trong ví dụ trên, không cần câu lệnh else vì trình biên dịch biết tất cả các trường hợp có thể của lớp Shape.