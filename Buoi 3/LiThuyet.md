# Các tính chất của OOP trong Kotlin
## 1. Bốn tính chất của OOP trong Kotlin
### 1.1 Encapsulation (Tính đóng gói)
- Tính đóng gói là các che dấu những tính chất xử lý bên trong của đối tượng, không thể tác động trực tiếp làm thay đổi trạng tháy, tính chất của những cái được che dấu
- Trong Kotlin mình có các access modifier để có thể che dấu các đối tượng như: private, public, protected, internal
```kotlin
class Student (name: String)
{
    private var name : String = name
        get() {
            return field
        }
        set(value) {
            this.name = value
        }
}
fun main(){
    val phuc = Student("Phuc")
}
```
- Ở ví dụ trên mình đã che dấu biến name bằng private, và khi sử dụng phúc.name hoặc phuc.name = "hai" thì nó sẽ gọi qua getter và setter
### 1.2 Inheritance (Tính kế thừa)
- Tính kế thừa cho phép người dùng tạo lớp dựa trên đặc điển và phương thức của lớp khác bằng cách thiếp lập quan hệ cha con
- Tính kế thừa hỗ trợ khả năng tái sử dụng. Làm giảm số lượng code cần phải viết
- Tất cả các class trong Kotlin đều có 1 SuperClass chung là `Any`. `Any` cung cấp các phương thức `equals()`, `hashcode()`, `toString()`
- Các class trong Kotlin khi được khai báo thì nó là `final`. Muốn kế thừa ta phải thêm `open` vào đầu class cha
- Nếu class có primary constructor, thì các subclass phải được khởi tạo bằng các tham số của primary constructor
```kotlin
open class Human(val name: String)

class Student(name: String, val id: String) : Human(name)
```
- Các biến mà con muốn dùng của cha thì phải truyền cho cha chứ không truyền cho con (không khai báo `val` hay `var` của biến name Student vì nó là dùng của cha)
- Nếu vẫn muốn Student có 1 biến name riêng thì thêm `override` vào biến con và `open` vào biến của cha
```kotlin
open class Human(open var name: String)

class Student(override var name: String, val id: String) : Human(name)
fun main(){

}
```
- Trong trường hợp không có `primary constructor`, mỗi `secondary constructor` phải khởi tạo cơ sở bằng từ khóa super
```kotlin
open class Student
{
    constructor(name: String)
    {
        println(name)
    }
    constructor(name: String, id: String)
    {
        println("$name $id")
    }
}

class Phuc : Student
{
    constructor(name : String) : super(name)
    {
        println(name)
    }
    constructor(name: String, id: String) : super(name, id)
    {
        println("$name $id")
    }
}
```
* Override:
- Các class con kế thừa class cha được phép ghi đè lại các phương thức, thuộc tính của lớp cha, với điều kiện là có tiền tố `open` trước phương thức, thuộc tính muốn ghi đè ở lớp cha và `override` ở lớp con ở phương thức, thuộc tính muốn ghi đè lại
```kotlin
open class Shape {
    open fun draw() { /*...*/ }
    fun fill() { /*...*/ }
}

class Circle() : Shape() {
    override fun draw() { /*...*/ }
}
fun main(){

}
```
- Ta có thể thêm `final` trước hàm override vì override cũng là open nên có thể kế thừa và override tiếp được
```kotlin
open class Shape {
    open fun draw() { /*...*/ }
    fun fill() { /*...*/ }
}

open class Circle() : Shape() {
    final override fun draw() { /*...*/ }
}
open class Elip() : Circle()
{
    override fun draw() {} // error vì đang cố override lại phương thức draw của Circle nhưng nó đã là final
}
fun main(){

}
```
- Bạn có thể override một thuộc tính `val` bằng thuộc tính `var` khác nhưng không thể ngược lại
```kotlin
open class Shape {
    open val vertexCount: Int = 0
}

class Rectangle(override val vertexCount: Int = 4) : Shape() // Always has 4 vertices

class Polygon : Shape() {
    override var vertexCount: Int = 0  // Can be set to any number later
}
fun main(){

}
```

* Thứ tự khởi tạo của Derived Class
- Trong quá trình tạo `instance` của class con, việc khởi tạo class cha sẽ được thực hiện đầu tiên. Nghĩa là những gì xảy ra trong lớp cha khi tạo `instance` sẽ được làm trước, rồi mới đến lớp con
```kotlin
open class Human(val name: String)
{
    init {
        println("Đây là class cha")
    }
}
open class Student(name : String, val id: String) : Human(name)
{
    init {
        println("Đây là class con")
    }
}


fun main(){
    val Phuc = Student("Phuc", "123")
}

//output:
Đây là class cha
Đây là class con
```

* Quy tắc Override
- Nếu một lớp kế thừa nhiều implementation của cùng một thành viên từ các superclass của nó, thì lớp đó phải override thành viên này và cung cấp triển khai riêng của mình (có thể sử dụng một trong những triển khai được kế thừa).

- Để gọi đến các thành phần thuộc các class, interface cha khác nhau thì ta sử dụng super<Base>
```kotlin
open class Rectangle {
  open fun draw() {
      println("Drawing a rectangle")
  }
}

interface Polygon {
  fun draw() {
      println("Drawing a polygon")
  }
}

class Square : Rectangle(), Polygon {
  override fun draw() {
      super<Rectangle>.draw()
      super<Polygon>.draw() 
  }
}

fun main() {
  val square = Square()
  square.draw()
}
```

* Gọi việc triển khai superclass:
- Trong một class con có thể gọi đến hàm và truy cập thuộc tính của lớp cha bằng cách sử dụng từ khóa super
```kotlin
open class Rectangle {
  open fun draw() {
      println("Drawing a rectangle")
  }
  val borderColor: String
      get() = "black"
}

class FilledRectangle : Rectangle() {
  override fun draw() {
      super.draw()
      println("Filling the rectangle")
  }

  val fillColor: String
      get() = super.borderColor
}

fun main() {
  val filledRectangle = FilledRectangle()
  filledRectangle.draw()
  //Drawing a rectangle
  //Filling the rectangle
  println("Fill color: ${filledRectangle.fillColor}")
  //Fill color: black
}
```

### 1.3 Polymorphism (Tính đa hình)
- Tính đa hình chủ yếu chia thành 2 loại:
    - Đa hình về thời gian biên dịch (Compile-time Polymorphism)
    - Đa hình thời gian chạy (Runtime Polymorphism)

* Conpile-time Polymorphism:
- Đa hình về thời gian biên dịch tức là tên hàm vẫn được giữ nguyên nhứng thâm số hoặc kiểu trả về đã thay đổi. Tại thời điểm biên dịch, tình biên dịch sẽ phân tích xem ta có gắng gọi hàm nào dựa trên kiểu tham số và các yếu tố khác
```kotlin
fun main (args: Array<String>) {
  println(doubleof(4))
  println(doubleof(4.3))
  println(doubleof(4.323))
}

fun doubleof(a: Int):Int {
  return 2*a
}

fun doubleOf(a:Float):Float {
  return 2*a
}

fun doubleof(a:Double):Double {
  return 2.00*a
}
```

* Runtime Polymorphism:
- Trong đa hình thời gian chạy, trình biên dịch sẽ giải quyết lệnh gọi đến các phương thức được ghi đè/nạp chồng tại thời điểm chạy
```kotlin
fun main(args: Array<string>){
  var a = Sup()
  a.method1()
  a.method2()
  
  var b = Sum()
  b.method1()
  b.method2()
}

open class Sup{
   open fun method1(){
       println("printing method 1 from inside Sup")
   }
   fun method2(){
       println("printing method 2 from inside Sup")
   }
}

class Sum:Sup(){
   override fun method1(){
       println("printing method 1 from inside Sum")
   }
}

output:
phương pháp in 1 từ bên trong Sup 
phương pháp in 2 từ bên trong Sup 
phương pháp in 1 từ bên trong Sum 
phương pháp in 2 từ bên trong Sup
```

### 1.4 Abstraction (Tính trừu tượng)
- Trong Kotlin, ta có thể sử dụng `abstract class` hoặc `interface` để biểu thị sự trừu tượng
#### 1.4.1 Abstract class
- Ta dùng tiền số `abstract` để khai báo lớp trừu tượng
- Lớp trừu tượng không thể tạo `instance` nhưng có thể kế thừa
- Các hàm và biến là non-abstract
- Không caàn sử dụng open để kế thừa
```kotlin
abstract class Polygon {
  abstract fun draw()
}

class Rectangle : Polygon() {
  override fun draw() {
      // draw the rectangle
  }
}
```
#### 1.4.2 Interface
- Một interface có thể chứa các phương thức trừu tượng cũng như các phương thức có nội dung.

- Một interface được định nghĩa bằng từ khóa interface
```kotlin
interface MyInterface {
  fun bar()
  fun foo() {
    // optional body
  }
}
class Child : MyInterface {
  override fun bar() {
      // body
  }
}
```

## 2. Backing field
- Trong Kotlin, field chỉ được sử dụng như một phần của thuộc tính để lưu giá trị của nó trong bộ nhớ. field không thể được khai báo trực tiếp. Tuy nhiên khi một thuộc tính cần backing fiels, Kotlin có thể tự động hỗ trợ.
```kotlin
class Student(name : String)
{
    var name: String = name
        get() {
            return field
        }
        set(value) {
            this.name = value
        }
}


fun main(){
    val Phuc = Student("Phuc")
}
```