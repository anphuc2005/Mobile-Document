package View

import Controller.ProductController
import Product.Product

class View {
    private val store = ProductController()

    fun run() {
        println("=== HỆ THỐNG QUẢN LÝ CỬA HÀNG VĂN PHÒNG PHẨM ===")
        while(true)
        {
            println("\n--- MENU CHÍNH ---")
            println("1. Tìm kiếm sản phẩm")
            println("2. Hiển thị tất cả sản phẩm")
            println("3. Thêm sản phẩm mới")
            println("0. Thoát")
            print("Chọn chức năng: ")

            when (readLine()?.toIntOrNull()) {
                1 -> timKiemSanPham()
                2 -> hienThiTatCaSanPham()
                3 -> themSanPhamMoi()
                0 -> {
                    println("Cảm ơn bạn đã sử dụng hệ thống!")
                    break
                }
                else -> println("Lựa chọn không hợp lệ! Vui lòng chọn lại.")
            }
        }
    }
    private fun timKiemSanPham() {
        println("Nhập từ khóa tìm kiếm: ")
        val word = readLine() ?:""
        if(word.isNotEmpty())
        {
            val ketqua = store.searchingProduct(word)
            if (ketqua.isNotEmpty()) {
                println("\nTìm thấy ${ketqua.size} sản phẩm!")
                chonKieuHienThi(ketqua)
            } else {
                println("Không tìm thấy sản phẩm nào với từ khóa '$word'")
            }
        }
        else {
            println("Vui lòng nhập từ khóa tìm kiếm!")
        }
    }
    private fun hienThiTatCaSanPham() {
        val danhSach = store.getAllProduct()
        chonKieuHienThi(danhSach)
    }

    private fun themSanPhamMoi() {
        store.addProduct()
    }
    private fun chonKieuHienThi(danhSach: List<Product>) {
        println("Chọn kiểu hiển thị:")
        println("1. Dạng bảng")
        println("2. Dạng danh sách")
        print("Lựa chọn: ")

        when (readLine()?.toIntOrNull()) {
            1 -> store.showingProductTypeTable(danhSach)
            2 -> store.showingProductTypeList(danhSach)
            else -> {
                println("Lựa chọn không hợp lệ! Hiển thị dạng danh sách:")
                store.showingProductTypeList(danhSach)
            }
        }
    }
}