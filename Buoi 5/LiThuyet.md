# Object và Callback
## 1.Object
- Trong Kotlin, chúng ta có thể tạo một đối tượng mà không cần phải khai báo lớp con mới cho nó. Kotlin có thể xử lý điều này bằng Object Declaration và Object Expressions
### 1.1 Object declaration (Khai báo đối tượng)
- Ta có thẻ tạo một singleton của một đối tượng trong Kotlin bằng các khai báo đối tượng bằng `object`
```kotlin
object DataProviderManager {
    private val providers = mutableListOf<DataProvider>()

    // Registers a new data provider
    fun registerDataProvider(provider: DataProvider) {
        providers.add(provider)
    }

    // Retrieves all registered data providers
    val allDataProviders: Collection<DataProvider> 
        get() = providers
}
```
- Để gọi đến phương thức thì ta gọi thẳng qua tên của đối tượng
```kotlin
DataProviderManager.registerDataProvider(exampleProvider)
```
- Object cũng có thể kế thừa từ một class khác và có thể `override` lại các phương thức kế thừa
```kotlin
open class Human() {
    open fun hello() {
        println("Something")
    }
}
object Vietnamese : Human()
{
    override fun hello() {
        println("Xin Chao")
    }
}


fun main(){
    Vietnamese.hello()
}
```

#### 1.1.1 Data objects
- Giống như data class, data object cũng có sẵn 1 số hàm:
    - toString() -> tên của data object
    - equals(): đảm bảo rằng tất cả các đối tượng có kiểu data object được coi là bằng nhau
    - hashcode()

- So sanh giữa data class và data object:
    - Không có hàm `copy()` vì data object là 1 singleton nên không thể tạo ra bản sao của nó
    - Không có hàm `componentN()`. Không giống như data class, data object không có bất kỳ thuộc tính nào nên việc lấy ra componentN là không có

#### 1.1.2 Companion objects
- Companion object cho phép định nghĩa các hàm và thuộc tính thuộc về lớp, giúp dễ dàng truy cập mà không phải khởi tạo bất kì instance nào
```kotlin
class User(val name: String) {
    companion object Factory {
        fun create(name: String): User = User(name)
    }
}

fun main(){
    val userInstance = User.create("John Doe")
    println(userInstance.name)

}
```
- Tên của companion object có thể được bỏ qua, trong trường hợp đó sẽ sửa dụng từ khóa `Companion`
```kotlin
class User(val name: String) {
    // Defines a companion object without a name
    companion object { }
}

// Accesses the companion object
val companionUser = User.Companion
```

- Class member có thể truy cập các private member của các companion object tương ứng
```kotlin
class User(val name: String) {
    companion object {
        private val defaultGreeting = "Hello"
    }

    fun sayHi() {
        println(defaultGreeting)
    }
}
User("Nick").sayHi()
// Hello
```
- Tên của class được dùng bởi chính nó sẽ hoạt động như 1 tham chiếu tớ companion object của class
```kotlin
class User1 {
    companion object Named {
        fun show(): String = "User1's Named Companion Object"
    }
}
val reference1 = User1

class User2 {
    companion object {
        fun show(): String = "User2's Companion Object"
    }
}
class name
val reference2 = User2
```

### 1.2 Object expressions (Biểu thức đối tượng)
- Biểu thức đối tượng khai báo một lớp và tạo một thể hiện của lớp đói, nhưng không đặt tên cho bất kỳ một lớp nào
#### 1.2.1 Tạo các đối tượng ẩn danh từ đầu
- Biểu thức đối tượng bắt đầu bằng từ khóa `object`
- Ta có thể định nghĩa các thành viên của đối tượng trực tiếp bên trong dấu ngoặc nhọn
```kotlin
val helloWorld = object {
    val hello = "Hello"
    val world = "World"
    override fun toString() = "$hello $world"
}

print(helloWorld)
```
#### 1.2.2 Kế thưuà object ẩn danh từ supertypes
- Ta có thể tạo một object của 1 lớp ẩn danh mà kế thừa từ 1 lớp khác bằng dấu `:`. Sau đó, việc triển khai hay ghi đè ở trong dấu ngoặc nhọn
```kotlin
open class Human() {
    open fun say(){
        println("Something")
    }
}

fun main () {
    val vietnamese = object: Human(){
        override fun say() {
            println("Xin chao")
        }
    }
}
// Xin chao
```
- Nếu là class kế thừa có constructor thì phải thêm constructor vào. Nếu kế thừa nhiều lớp cha thì dùng dấu phẩy giữa các lớp cha
```kotlin
open class Human(val name: String) {
    open fun say(){
        println("Something")
    }
}
interface A

fun main () {
    val vietnamese = object: Human("Phuc"), A{
        override fun say() {
            println("Xin chao")
        }
    }
}
// Xin chao
```
#### 1.2.3 Sử dụng các đối tượng ẩn danh làm kiểu trả về và giá trị
- Khi bạn lấy object làm kiểu trả về của hàm private thì tất cả các thành viển của nó đều có thể truy cập được thông qua hàm hoặc thuộc tính:
```kotlin
class UserPreferences {
    private fun getPreferences() = object {
        val theme: String = "Dark"
        val fontSize: Int = 14
    }

    fun printPreferences() {
        val preferences = getPreferences()
        println("Theme: ${preferences.theme}, Font Size: ${preferences.fontSize}")
    }
}
```
- Nếu một hàm hoặc thuộc tính trả về một đối tượng ẩn danh có public, protected, hoặc internal thì kiểu thực tế của nó là:
    - Any nếu đối tượng ẩn danh không có loại cha nào được khai báo.
    - Loại cha được khai báo của đối tượng ẩn danh, nếu chỉ có một loại cha duy nhất.
    - Kiểu được khai báo rõ ràng nếu có nhiều hơn một loại cha được khai báo.
    
- Trong tất cả các trường hợp này, các thành viên được thêm vào đối tượng ẩn danh đều không thể truy cập được. Các thành viên bị ghi đè có thể truy cập được nếu chúng được khai báo theo đúng kiểu của hàm hoặc thuộc tính. Ví dụ:
```kotlin
interface Notification {
    // Declares notifyUser() in the Notification interface
    fun notifyUser()
}

interface DetailedNotification

class NotificationManager {
    // The return type is Any. The message property is not accessible.
    // When the return type is Any, only members of the Any class are accessible.
    fun getNotification() = object {
        val message: String = "General notification"
    }

    // The return type is Notification because the anonymous object implements only one interface
    // The notifyUser() function is accessible because it is part of the Notification interface
    // The message property is not accessible because it is not declared in the Notification interface
    fun getEmailNotification() = object : Notification {
        override fun notifyUser() {
            println("Sending email notification")
        }
        val message: String = "You've got mail!"
    }

    // The return type is DetailedNotification. The notifyUser() function and the message property are not accessible
    // Only members declared in the DetailedNotification interface are accessible
    fun getDetailedNotification(): DetailedNotification = object : Notification, DetailedNotification {
        override fun notifyUser() {
            println("Sending detailed notification")
        }
        val message: String = "Detailed message content"
    }
}
```

#### 1.2.4 Truy cập các biến từ các object ẩn danh
- Code trong các object expression có thể truy cập các biến từ phạm vi bao quanh nó.
```kotlin
open class Human() {
    fun say()
    {
        println("Something)
    }
}

fun main () {
    var name = "Phuc"
    val person = object: Human() {
        fun sayHello() {
            say()
            name = "Hai"
        }
    }
    person.sayHello()
}
```

### 1.3 Hình thái tương đương của object declaration, object expression trong Java.
- Với object declaration là tạo một singleton thì trong Java ta có thể xử lí bằng static final
```kotlin
object Singleton {
    fun doSomething() {
        println("Doing something")
    }
}
```
```java
public class Singleton {
    private static final Singleton Instance = new Singleton()

    private Singleton() {

    }

    public static Singleton getInstance() {
        return Instance;
    }

    public void doSometing() {
        System.out.println("Doing something");
    }
}
```
- Với object expression thì java có thể tạo Anonymous class
```kotlin
interface Printer {
    fun printMessage(message: String)
}

fun main() {
    val printer = object : Printer {
        override fun printMessage(message: String) {
            println(message)
        }
    }

    printer.printMessage("Hello, Kotlin!")
}
```

```java
interface Printer {
    void printMessage(String message);
}

public class Main {
    public static void main(String[] args) {
        Printer printer = new Printer() {
            @Override
            public void printMessage(String message) {
                System.out.println(message);
            }
        };

        printer.printMessage("Hello, Java!");
    }
}
```

## 2. Call back
### 2.1 Higher order function 
- Các hàm thông thường chỉ nhận vào các parameter là các tham số dữ liệu, nhưng với Higher Order Function lại có thể nhận một function khác như một param hoặc trả về 1 function
- Ví dụ về dùng hàm làm param
```kotlin
fun doSomeThing(num: String, respon: (String?) -> Unit) {
    val result: String? = num
    respon(result)
}

fun respon(result: String?) {
    if (result != null) {
        for (ch in result) {
            if (ch < '0' || ch > '9') {
                println("No digit")
                return
            }
        }
        println("Digit")
    } else {
        println("No digit")
    }
}

fun main() {
    doSomeThing("12x", ::respon)
}
```

- Ví dụ về trả về 1 hàm:
```kotlin
// Hàm bậc cao trả về một hàm (Int) -> Int
fun operation(type: String): (Int) -> Int {
    return when (type) {
        "double" -> { x -> x * 2 }
        "square" -> { x -> x * x }
        else -> { x -> x }
    }
}

fun main() {
    val doubleFunc = operation("double")
    val squareFunc = operation("square")

    println(doubleFunc(4))  // Output: 8
    println(squareFunc(4))  // Output: 16
}

```

## 2.2 Lambda function
- Cú pháp của Lambda function:
    - Biểu thức của lambda luôn được bao bởi {}
    - Nếu lambda function có bất kì param nào nó phải ở trước toán tử ->
    - Body của lambda funtion phải ở sau toán tử ->

- Ví dụ về cú pháp của lambda function:
```kotlin
// Cú pháp 1:
doSomething(1000, {result ->
    //logic code
})
// Cú pháp 2:
doSomething(1000, {result: String? ->
    //logic code
})
// Cú pháp 3:
doSomething(1000) {result ->
    //logic code
}
```

- It: tên ngầm định của một tham số duy nhất:
    - Biểu thức lambda thường chỉ có 1 tham số, tham số đó không cần được khai báo và toán tử -> có thể đc bỏ qua. Tham số sẽ được khai báo ngầm định dưới tên `it`
    ```kotlin
    ints.filter { it > 0 } // this literal is of type '(it: Int) -> Boolean'
    ``` 

- Trả về giá trị từ biểu thức lambda:
    - Bạn có thể trả về một giá trị rõ ràng từ lambda bằng cú pháp return. Nếu không, giá trị của biểu thức cuối cùng sẽ được trả về nhầm định
    ```kotlin
        ints.filter {
        val shouldFilter = it > 0
        shouldFilter // Trả về ngầm định
    }

        ints.filter {
        val shouldFilter = it > 0
        return@filter shouldFilter // Trả về rõ ràng từ lambda filter
    }
    ```
    - filter là một hàm cao cấp (higher-order function) nhận lambda it -> Boolean.

    - Biến shouldFilter được tính toán.

    - Dòng cuối cùng `shouldFilter` chính là giá trị trả về cho lambda này. `return@filter` nghĩa là "trả về từ lambda của hàm filter", với giá trị là `shouldFilter`.

- Gạch chân cho các biến không sử dụng:
    - Nếu tham số lambda không được sử dụng, bạn có thể đặt dấu gạch dưới thay cho tên của tham số đó:
    ```kotlin
    map.forEach { (_, value) -> println("$value!") }
    ```
- Closures:
    - Lambda, anonymous function, local function và object expression đều có thể truy cập closure của nó (nôm na như là vùng bên ngoài khai báo nó). Nó có thể truy cập các function, biến và param được khởi tạo ở bên ngoài, không giống Java, ta chỉ sử dụng được các biến và param ở vùng bên ngoài nếu như chúng được khai báo là final
    ```kotlin
    var sum = 0
    ints.filter { it > 0 }.forEach {
        sum += it //sum không cần phải là val (final)
    }
    print(sum)

    ```

## 2.3 Call back là gì? Tại sao phải dùng callback
- Trong Kotlin, một hàm callback là một hàm được truyền dưới dạng đối số cho một hàm khác, sau đó hàm nhận sẽ gọi lại (thực thi) hàm đó vào một thời điểm thích hợp trong quá trình thực thi của nó
- Tại sao lại phải dùng callback:
    - Lập trình bất đồng bộ: Callback là cốt lõi của việc quản lý các kỹ thuật bất đồng bộ trong các chương trình Kotlin. Chúng cho phép chia nhỏ quá trình thực thi chương trình thành nhiều luồng chạy độc lập, ngăn chặn luồng chính bị chặn (blocking) khi chờ các thao tác dài hạn hoàn tất, chẳng hạn như tìm nạp dữ liệu từ máy chủ từ xa. Điều này giúp ứng dụng phản hồi nhanh hơn và hiệu quả hơn.
    - Xử lý sự kiện: Callback rất tốt cho việc xử lý sự kiện, ví dụ như xử lý đầu vào của người dùng hoặc lên lịch hẹn giờ. Khi một sự kiện xảy ra, hàm callback sẽ phản hồi hành động đó, cho phép ứng dụng phản ứng trong thời gian thực.
    - Thành phần hàm (Function Composition): Callback cho phép các hàm bậc cao hơn (higher-order functions) được sử dụng để kết hợp nhiều hàm nhỏ thành các hàm lớn hơn. Ví dụ, hàm filter() trên một mảng chấp nhận một hàm callback để trả về một phiên bản mảng đã lọc.
    - Tái sử dụng và khả năng mở rộng: Bằng cách định nghĩa các hàm callback, bạn có thể tạo mã có thể được gọi nhiều lần trong các trường hợp khác nhau, làm cho chương trình trở nên mô-đun hơn và dễ mở rộng hơn.

## 2.4 Ưu nhược điểm của callback
- Ưu điểm:
    - Đơn giản và dễ hiểu: Callbacks thường rất đơn giản và dễ hiểu, đặc biệt đối với các lập trình viên mới.
    - Trực quan cho các thao tác bất đồng bộ: Chúng giúp bạn xử lý các thao tác bất đồng bộ một cách rõ ràng và dễ theo dõi.
- Nhược điểm:
    - Callback Hell (Địa ngục callback): Khi có quá nhiều callback lồng nhau, mã nguồn của bạn có thể trở nên rất phức tạp và khó đọc. Đây thường được gọi là "callback hell".
    - Để khắc phục các nhược điểm của callback hell, các giải pháp như Coroutines với hàm suspend, Futures/Promises và Reactive Extensions (Rx) đã được phát triển để cung cấp cách tiếp cận lập trình bất đồng bộ hiệu quả hơn

