# Buổi 1: Basic Kotlin
## I. Cách nhập xuất trong kotlin
### 1. Sử dụng thư viện của Java Scanner

```kotlin
import java.utils.Scanner

fun main() {
    val reader = Scanner(System.`in`)

    val a = reader.nextInt()
    print(a)
}
```

- Cách nhập xuất này dùng thư viện của Java để nhập, có thể nhập được trên 1 dòng

### 2. Sử dụng readln() và readLine()
- Cả readln() và readLine() đều dùng để nhập xuất dữ liệu trên 1 dòng

|Đặc điểm|readLine()|readLine()|
|--------|----------|----------|
|Trả về|String(có thể nhận null)|String(không thể nhận null)|
|Xử lý|Lỗi -> null hoặc kết thúc đầu vào|Lỗi không trả về null, ném ra Exception|
|Yêu cầu kiểm tra Null Safety|Có|Không|

```kotlin
fun main() {
    print("Nhập tên: ")
    val name = readLine() ?: "Không xác định"
    println("Xin chào, $name")
}

fun main() {
    print("Nhập tên: ")
    val name = readln()
    println("Xin chào, $name")
}
```

## II. Biến và kiểu dữ liệu

### 1. Khai báo biến
- Trong kotlin, người ta dùng 2 từ khóa để khai báo biến
 + `var` (variable) : Biến có thể thay đổi
 + `val` (value) : Biến hằng không thể thay đổi

Ví dụ:
```kotlin
fun main() {
    val name = "Phuc" // Biến hằng
    var age = 20 // Biến có thể thay đổi
}
```

- Ta có thể đặc tả của kiểu dữ liệu bên cạnh tên biến bằng cách thêm kiểu dữ liệu bên cạnh tên biến:

```kotlin
fun main() {
    val name: String = "Phuc" // Biến hằng
    var age: Int = 20 // Biến có thể thay đổi
}
```

- Đối với biến `var` ta có thể gán giá trị cho biến với điều kiện biến phải được đặc tả kiểu dữ liệu

```kotlin
fun main() {
    var name
    name = "Phuc" // Compile error

    var name: String
    name = "Phuc" // Ok
}
```

- Biến `val` không thể gán lại dữ liệu cho nó vì nó là biến hằng, nhưng mình có thể khai báo trước rồi gán sau thì giá trị sau khi gán thành hằng số và không thể thay đổi

```kotlin
fun main() {
    val name: String
    name = "Phuc"
    print(name) // in ra Phuc

    val name: String = "Hai"
    name = "Phuc" // error
}
```

### 2. Kiểu dữ liệu

* Số nguyên:
  
    | Kiểu dữ liệu  | Khoảng giá trị  |
    |---|---|
    | Byte  |-128 -> 127 (-2^7^ -> 2^7^ - 1)|
    | Short  |-32768 -> 32767  (-2^15^ -> 2^15^ - 1) |
    | Int  | -2147483648 -> 2147483647  (-2^31^ -> 2^31^ - 1)|
    | Long  | -9223372036854775808 -> 9223372036854775807 (-2^63^ -> 2^63^ - 1) |

* Số thực:
    - Float: Lưu trữ 6 -> 7 chữ số sau dấu phẩy.
    - Double: Lưu trữ 15 -> 16 chữ số sau dấu phẩy

* Boolean:
    - Kiểu dữ liệu trả về true hoặc false
* Kí tự (char):
    - Trả về kí tự
* Mảng trong kotlin:
    - Mảng là một danh sách chức các dữ liệu cùng 1 kiểu dữ liệu
    - Khai báo : `var arr = IntArray(size của mảng)` tùy vào kiểu dữ liệu mà mình thay đổi
    - Các thao tác như truy cập vào index (arr[index]) hay arr.size() vẫn dữ nguyên từ Java
* String:
    - Là một chuỗi các kí tự

## III. Câu lệnh rẽ nhánh
### 1. If - else if - else
- VD:
```kotlin
fun main() {
    var a: Int = readln().toInt()
    if(a % 2 == 0) print("So chan")
    else print("So le")
}
```

- Ta có thể dung if - else để gán giá trị
```kotlin
fun main() {
    var a: Int = readln().toInt()
    val result = if(a % 2 == 0) print("So chan") else print("So le")
}
```

### 2. When
- `when` giống với `switch-case` trong Java
```kotlin
fun main() {
    var month = readln().toInt()
    val result = when (month) {
        1 -> "January"
        2 -> "February"
        3 -> "March"
        4 -> "April"
        5 -> "May"
        6 -> "June"
        7 -> "July"
        8 -> "August"
        9 -> "September"
        10 -> "October"
        11 -> "November"
        12 -> "December"
        else -> "Tháng không hợp lệ"
    }

    println("Tháng: $result")
}
```
## IV. Vòng lặp loop
### 1. While

VD:
```kotlin
fun main() {
    var index = 0
    while (index <= 5)
    {
        println(index)
        ++i
    }
}
```

### 2. For
- **Duyệt tuần tự các giá trị trong danh sách - Closed Range:**
  ```kotlin
  for (i in a..b) {
    //xu ly bien i
  }
  ```
  -> i thuộc [a, b] với bước nhảy tăng dần 1 đơn vị từ a -> b.

  - **Duyệt tuần tự gần hết giá trị trong danh sách - half-open range:**
  ```kotlin
  for (i in a until b) {
    //xu ly bien i
  }
  ```
  -> i thuộc [a, b) với bước nhảy tăng dần 1 đơn vị từ a -> (b-1).

  - **Bước nhảy step:**
  ```kotlin
  for (i in a..b step x) {
    //Xu ly bien i
  }
  ```
  -> i tự động tăng dần từ a -> b với bước nhảy x. `step` cũng có thể sử dụng chung với các kiểu duyệt khác

  - **downTo:**
  ```kotlin
  for (i in b downTo a) {
    //Xu ly bien i
  }
  ```
  -> Duyệt i giảm dần 1 đơn vị từ b về a

  - **Lặp tập đối tượng:**
  ```kotlin
  fun main() {
    val nameList = arrayOf("An", "Hai", "Hoa", "Phuc")
    for (name in nameList) {
        print("$name ")
    }
  }
  ```
  hoặc cũng có thể duyệt kèm chỉ số:
  ```kotlin
  fun main() {
    val nameList = arrayOf("An", "Hai", "Hoa", "Phuc")
    for (i in nameList.indices) {
        println("STT $i co ten: " + nameList[i])
    }
  }
  ```
  Kotlin cũng hộ trợ việc vừa lấy index và value theo cách sau:
  ```kotlin
  fun main() {
    val nameList = arrayOf("An", "Hai", "Hoa", "Phuc")
    for ((index, value) in nameList.withIndex()) {
        println("STT $index co ten: $value" )
    }
  }
  ```

## V. Colections trong Kotlin
- Colections trong Kotlin bao gồm List, Set, Map
- Gồm 2 loại:
    + Immutable: Các colection chỉ đọc, không thể tương tác thay đổi các phần tử trong colection
    + Mutable: Các colection có tương tác thay đổi các phần tử trong colection (được thêm các phương thức add, remove, ... để tương tác)\

### 1. Immutable Colection
- List:
```kotlin
fun main() {
    val list: List<Int> = listOf(1,2,3,4)
    for (num in list)
    {
        println(num)
    }
}
```

- Set:
```kotlin
fun main() {
    val set: Set<Int> = setOf(1,2,3,4)
    for (num in set)
    {
        println(num)
    }
    println("1 co o trong set khong: ${set.contains(1)}")
    println("7 co o trong set khong: ${set.contains(7)}")
}
```

- Map:
```kotlin
fun main() {
    val map: Map<Int, String> = mapOf(1 to "Hai", 2 to "Phuc")
    for (element in map)
    {
        println("key = ${element.key} - value = ${element.value}; ")
    }
}
```
### 2. Mutable Colection
- Thêm các phương thức add, remove, removeAt

- List:
```kotlin
fun main() {
    val list: List<Int> = listOf(1,2,3,4)
    for (num in list)
    {
        println(num)
    }
    list.add(7)
    list.add(8)
    for (element in sampleList) {
        print(element)
    }
    list.removeAt(0)
    for (element in sampleList) {
        print(element)
    }
}
```

- Set:
```kotlin
fun main() {
    val set: Set<Int> = setOf(1,2,3,4)
    for (num in set)
    {
        println(num)
    }
    println("1 co o trong set khong: ${set.contains(1)}")
    println("7 co o trong set khong: ${set.contains(7)}")
    set.remove(3)
    print("After remove 3: ")
    for (element in set) {
        print(element)
    }
}
```

- Map:
```kotlin
val sampleMap: MutableMap<Int, String> = mutableMapOf(1 to "A", 2 to "B")
    for (element in sampleMap) {
        print("key = ${element.key} - value = ${element.value}; ")
    }
    println()
    sampleMap.put(3, "C")
    print("After put {3, C}: ")
    for (element in sampleMap) {
        print("key = ${element.key} - value = ${element.value}; ")
    }
    println()

    sampleMap.remove(2)
    print("After remove key 2: ")
    for (element in sampleMap) {
        print("key = ${element.key} - value = ${element.value}; ")
    }
    println()
```

## VI. Null Safety
- Cơ chế null safety trong kotlin giúp ta đối phó với một biến có thể null để tránh trường hợp throw ra NullpointerException mà khó để tìm ra lỗi
- Ví dụ:
```kotlin
fun main() {
    var a: Int = null
    a.toString()

    var a = readln()
    a.toInt()

}

```
- Khi gán biến a là null hay là nhập biến a là null thì compile của kotlin đều báo lỗi
- Cách giải quyết:
    + `var a: Int? = null` thêm dấu hỏi vào sau đặc cả của biến gán để có thể bằng null mà không bị compile error
    + với TH nhập readln hay readLine thì sẽ có hàm toIntorNull hoặc toStringOrNull để biết mình nhập mà là kiểu DL hay là null rồi dùng if-else

- Safety call: 
```kotlin
fun main() {
    var a: String? = null
    var b = a?.length
    print(b)
}
```
- Khi gán b = a.length thì nếu a null thì a.length cũng là null nên nếu gán vào b thì sẽ vi phạm Safety call nên phải thêm dấu ? để compile biết a.length cũng có thể null

- Toán tử Elvis: Là toán tử `?:`, khá giống với toán tử ba ngồi, xem ví dụ để hiểu rõ hơn:
```kotlin
fun main() {
    var str: String? = null
    var size = str?.length ?: 0
    println(size)
}
```
-> Thay vì như lúc trước, khi `str` có giá trị null thì size sẽ được gán là null thì khi dùng toán tử Elvis, ta sẽ gán được giá trị của size = 0. Trong trường hợp `str` khác null thì cũng tương tự