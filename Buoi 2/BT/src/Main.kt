fun showMenu()
{
    println(
        """
        
        === QUẢN LÝ DANH BẠ ===
        1. Xem danh sách danh bạ
        2. Thêm danh bạ
        3. Sửa danh bạ
        4. Xoá danh bạ
        5. Thoát
        Nhập lựa chọn: 
    """
    )
}
fun main() {
    while (true) {
        showMenu()
        when (readlnOrNull()?.trim()) {
            "1" -> view()
            "2" -> add()
            "3" -> edit()
            "4" -> delete()
            "5" -> {
                println("Thoát")
                return
            }
            else -> println("Lựa chọn không hợp lệ. Vui lòng chọn lại.")
        }
    }
}