package View

import Controller.ProductController
import Product.Book
import Product.Notebook
import Product.Pen
import Product.Pencil
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
            println("4. Xóa sản phẩm")
            println("0. Thoát")
            print("Chọn chức năng: ")

            when (readLine()?.toIntOrNull()) {
                1 -> timKiemSanPham()
                2 -> hienThiTatCaSanPham()
                3 -> themSanPhamMoi()
                4 -> xoaSanPham()
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
        println("\n=== THÊM SẢN PHẨM MỚI ===")

        val sanPhamMoi = nhapThongTinSanPhamMoi()

        val ketQua = store.addProduct(sanPhamMoi)

        if (ketQua) {
            println("Thêm sản phẩm thành công!")
        } else {
            println("Thêm sản phẩm thất bại!")
        }
    }
    private fun nhapThongTinSanPhamMoi() : Product? {
        println("Nhập sản phẩm muốn thêm:")
        println("1. Vở ghi")
        println("2. Bút chì")
        println("3. Bút mực")
        println("4. Sách")

        val choice = InputValidator.readPositiveInt("Chọn loại sản phẩm: ")

        try {
            when(choice) {
                1 -> {
                    val tenSanPham = InputValidator.readNonBlank("Nhập tên sản phẩm: ")
                    val giaBan = InputValidator.readPositiveDouble("Nhập giá bán: ")
                    val thuongHieu = InputValidator.readNonBlank("Nhập thương hiệu: ")
                    val soTrang = InputValidator.readPositiveInt("Nhập số trang: ")
                    val loaiVo = InputValidator.readValidatedString("Nhập loại vở", ConstantInput.LOAIVO)
                    val mauSacBia = InputValidator.readNonBlank("Nhập màu sắc bìa: ")
                    val chatLieuGiay = InputValidator.readValidatedString("Nhập chất liệu giấy", ConstantInput.CHATLIEUGIAY)
                    val kichThuoc = InputValidator.readValidatedString("Nhập kích thước", ConstantInput.KICKTHUOCVO)

                    return Notebook(tenSanPham, giaBan, thuongHieu, soTrang, loaiVo, mauSacBia, chatLieuGiay, kichThuoc)
                }

                2 -> {
                    val tenSanPham = InputValidator.readNonBlank("Nhập tên sản phẩm: ")
                    val giaBan = InputValidator.readPositiveDouble("Nhập giá bán: ")
                    val thuongHieu = InputValidator.readNonBlank("Nhập thương hiệu: ")
                    val mauSac = InputValidator.readNonBlank("Nhập màu sắc: ")
                    val chatLieu = InputValidator.readValidatedString("Nhập chất liệu", ConstantInput.CHATLIEUBUTCHI)
                    val doCung = InputValidator.readValidatedString("Nhập độ cứng", ConstantInput.DOCUNG)

                    return Pencil(tenSanPham, giaBan, thuongHieu, mauSac, chatLieu, doCung)
                }

                3 -> {
                    val tenSanPham = InputValidator.readNonBlank("Nhập tên sản phẩm: ")
                    val giaBan = InputValidator.readPositiveDouble("Nhập giá bán: ")
                    val thuongHieu = InputValidator.readNonBlank("Nhập thương hiệu: ")
                    val mauSac = InputValidator.readNonBlank("Nhập màu sắc: ")
                    val chatLieu = InputValidator.readValidatedString("Nhập chất liệu", ConstantInput.CHATLIEUBUTMUC)
                    val loaiMuc = InputValidator.readValidatedString("Nhập loại mực", ConstantInput.LOAIMUC)
                    val doMin = InputValidator.readValidatedString("Nhập độ mịn", ConstantInput.DOMIN)

                    return Pen(tenSanPham, giaBan, thuongHieu, mauSac, chatLieu, loaiMuc, doMin)
                }

                4 -> {
                    val tenSanPham = InputValidator.readNonBlank("Nhập tên sản phẩm: ")
                    val giaBan = InputValidator.readPositiveDouble("Nhập giá bán: ")
                    val theLoai = InputValidator.readNonBlank("Nhập thể loại: ") // Assuming no enum for this yet
                    val tacGia = InputValidator.readNonBlank("Nhập tác giả: ")
                    val nhaXuatBan = InputValidator.readNonBlank("Nhập nhà xuất bản: ")
                    val namXuatBan = InputValidator.readPositiveInt("Nhập năm xuất bản: ")
                    val ngonNgu = InputValidator.readNonBlank("Nhập ngôn ngữ: ") // Assuming no enum for this yet

                    return Book(tenSanPham, giaBan, nhaXuatBan, theLoai, tacGia, namXuatBan, ngonNgu)
                }

                else -> {
                    println("Lựa chọn không hợp lệ!")
                    return null
                }
            }
        } catch (e: IllegalArgumentException) {
            // Catch exceptions thrown by Product class's init blocks
            println("Lỗi tạo sản phẩm: ${e.message}")
            return null
        }
    }

    private fun xoaSanPham() {
        hienThiTatCaSanPham()
        println("Nhập số thứ tự muốn xóa: ")
        val index: Int = readln().toIntOrNull()!!
        if(store.removeProduct(index))
        {
            println("Đã xóa sản phẩm thành công")
        }
        else println("Không tìm thấy số thứ tự sản phẩm để xóa hoặc danh sách không tồn tại")
    }

    private fun chonKieuHienThi(danhSach: List<Product>) {
        println("Chọn kiểu hiển thị:")
        println("1. Dạng bảng")
        println("2. Dạng danh sách")
        print("Lựa chọn: ")

        when (readLine()?.toIntOrNull()) {
            1 -> showingProductTypeTable(danhSach)
            2 -> showingProductTypeList(danhSach)
            else -> {
                println("Lựa chọn không hợp lệ! Hiển thị dạng danh sách:")
                showingProductTypeList(danhSach)
            }
        }
    }

    fun showingProductTypeTable(list: List<Product>){
        if(list.isEmpty())
        {
            println("Danh sách sản phẩm đang trống....")
            return
        }

        println("\n" + "=".repeat(120))
        println("KẾT QUẢ TÌM KIẾM - DẠNG BẢNG")
        println("=".repeat(120))

        println(String.format("%-15s | %-15s | %-25s | %-30s |",
            "TÊN SẢN PHẨM", "GIÁ", "THƯƠNG HIỆU", "THÔNG TIN KHÁC"))
        println("-".repeat(120))

        list.forEachIndexed { index, product ->
            val type: String = when (product)
            {
                is Notebook -> "Vở ghi"
                is Pencil -> "Bút chì"
                is Pen -> "Bút mực"
                is Book -> product.nameProduct
                else -> ""
            }

            val thongTinKhacList = when (product) {
                is Notebook -> listOf(
                    "Trang: ${product.page}",
                    "Loại: ${product.type}",
                    "Màu: ${product.color}",
                    "Chất liệu: ${product.material}",
                    "Kích thước: ${product.size}"
                )
                is Pencil -> listOf(
                    "Màu: ${product.color}",
                    "Chất liệu: ${product.material}",
                    "Độ cứng: ${product.hardness}"
                )
                is Pen -> listOf(
                    "Màu: ${product.color}",
                    "Chất liệu: ${product.material}",
                    "Loại mực: ${product.typeOfInk}",
                    "Độ mượt: ${product.smoothness}"
                )
                is Book -> listOf(
                    "Thể loại: ${product.category}",
                    "Tác giả: ${product.author}",
                    "NXB: ${product.brand}",
                    "Năm XB: ${product.yearOfPublic}",
                    "Ngôn ngữ: ${product.language}"
                )
                else -> emptyList()
            }

            val firstInfo = if (thongTinKhacList.isNotEmpty()) thongTinKhacList[0] else ""
            println(String.format("%-15s | %-15.0f | %-25s | %-30s |",
                type.take(15),
                product.price,
                product.brand.take(25),
                firstInfo.take(30)
            ))

            for (i in 1 until thongTinKhacList.size) {
                println(String.format("%-15s | %-15s | %-25s | %-30s |",
                    "",
                    "",
                    "",
                    thongTinKhacList[i].take(30)
                ))
            }

            if (index < list.size - 1) {
                println("-".repeat(120))
            }
        }
        println("=".repeat(120))
        println("Tổng cộng: ${list.size} sản phẩm")
    }

    fun showingProductTypeList(list: List<Product>){
        if (list.isEmpty()) {
            println("Danh sách sản phẩm đang trống....")
            return
        }

        println("\n" + "=".repeat(80))
        println("DANH SÁCH SẢN PHẨM TÌM KIẾM ĐƯỢC")
        println("=".repeat(80))

        list.forEachIndexed { index, product ->
            val type: String = when (product)
            {
                is Notebook -> "Vở ghi"
                is Pencil -> "Bút chì"
                is Pen -> "Bút mực"
                is Book -> product.nameProduct
                else -> ""
            }

            val thongTinKhac = when (product) {
                is Notebook -> listOf(
                    "Số trang: ${product.page}",
                    "Loại vở: ${product.type}",
                    "Màu sắc: ${product.color}",
                    "Chất liệu: ${product.material}",
                    "Cỡ: ${product.size}"
                )
                is Pencil -> listOf(
                    "Màu sắc: ${product.color}",
                    "Chất liệu: ${product.material}",
                    "Độ cứng: ${product.hardness}"
                )
                is Pen -> listOf(
                    "Màu sắc: ${product.color}",
                    "Chất liệu: ${product.material}",
                    "Loại mực: ${product.typeOfInk}",
                    "Độ mượt: ${product.smoothness}"
                )
                is Book -> listOf(
                    "Thể loại: ${product.category}",
                    "Tác giả: ${product.author}",
                    "Thương hiệu: ${product.brand}",
                    "Năm XB: ${product.yearOfPublic}",
                    "Ngôn ngữ: ${product.language}"
                )
                else -> emptyList()
            }
            println("Tên sản phẩm: ${product.nameProduct}")
            println("Giá bán: ${String.format("%,.0f", product.price)}đ")
            println("Thương hiệu: ${product.brand}")
            println("Thông tin chi tiết:")

            thongTinKhac.forEachIndexed { detailIndex, detail ->
                val prefix = if (detailIndex == thongTinKhac.size - 1) "" else ""
                println("$prefix $detail")
            }

            if (index < list.size - 1) {
                println("-".repeat(80))
            }
        }

        println("=".repeat(80))
        println("Tổng cộng: ${list.size} sản phẩm")
        println("=".repeat(80))
    }
}