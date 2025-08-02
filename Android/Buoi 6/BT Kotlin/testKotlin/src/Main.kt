fun pullData(callback: (String) -> Unit) {
    println("Đang lấy dữ liệu...")
    Thread.sleep(2000)
    val data = "Dữ liệu từ server"
    callback(data)
}


fun main() {
    pullData { result ->
        println("Kết quả trả về: $result")
    }
}
