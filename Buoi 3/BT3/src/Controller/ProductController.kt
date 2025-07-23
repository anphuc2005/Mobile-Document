package Controller

import Product.Book
import Product.Notebook
import Product.Pencil
import  Product.Pen
import Product.Product

class ProductController {
    private val listOfProduct = mutableListOf<Product>()

    fun getAllProduct() : List<Product> = listOfProduct.toList()

    fun addProduct()
    {
        println("Nhập sản phẩm muốn add:")
        println("1. Vở ghi")
        println("2. Bút chì")
        println("3. Bút mực")
        println("4. Sách")
        print("Chọn loại sản phẩm: ")

        when(readln()?.toIntOrNull()) {
            1 -> {
                print("Nhập tên sản phẩm: ")
                val tenSanPham: String = readln() ?: ""
                print("Nhập giá bán: ")
                val giaBan: Double = readln()?.toDoubleOrNull() ?: 0.0
                print("Nhập thương hiệu: ")
                val thuongHieu: String = readln() ?: ""
                print("Nhập số trang: ")
                val soTrang: Int = readln()?.toIntOrNull() ?: 0
                print("Nhập loại vở (vở kẻ ô/vở kẻ ngang/vở kẻ caro): ")
                val loaiVo: String = readln() ?: ""
                print("Nhập màu sắc bìa: ")
                val mauSacBia: String = readln() ?: ""
                print("Nhập chất liệu giấy (giấy trắng/giấy màu): ")
                val chatLieuGiay: String = readln() ?: ""
                print("Nhập kích thước (A4/A5/A6): ")
                val kichThuoc: String = readln() ?: ""

                val voGhi = Notebook(tenSanPham, giaBan, thuongHieu, soTrang, loaiVo, mauSacBia, chatLieuGiay, kichThuoc)
                listOfProduct.add(voGhi)
                println("Đã thêm vở ghi: $tenSanPham thành công!")
            }

            2 -> {
                print("Nhập tên sản phẩm: ")
                val tenSanPham: String = readln() ?: ""
                print("Nhập giá bán: ")
                val giaBan: Double = readln()?.toDoubleOrNull() ?: 0.0
                print("Nhập thương hiệu: ")
                val thuongHieu: String = readln() ?: ""
                print("Nhập màu sắc: ")
                val mauSac: String = readln() ?: ""
                print("Nhập chất liệu (gỗ/nhựa): ")
                val chatLieu: String = readln() ?: ""
                print("Nhập độ cứng (HB/2B/3B/4B/5B/6B/7B/8B/9B/10B): ")
                val doCung: String = readln() ?: ""

                val butChi = Pencil(tenSanPham, giaBan, thuongHieu, mauSac, chatLieu, doCung)
                listOfProduct.add(butChi)
                println("Đã thêm bút chì: $tenSanPham thành công!")
            }

            3 -> {
                print("Nhập tên sản phẩm: ")
                val tenSanPham: String = readln() ?: ""
                print("Nhập giá bán: ")
                val giaBan: Double = readln()?.toDoubleOrNull() ?: 0.0
                print("Nhập thương hiệu: ")
                val thuongHieu: String = readln() ?: ""
                print("Nhập màu sắc: ")
                val mauSac: String = readln() ?: ""
                print("Nhập chất liệu (nhựa/kim loại): ")
                val chatLieu: String = readln() ?: ""
                print("Nhập loại mực (mực dầu/mực nước): ")
                val loaiMuc: String = readln() ?: ""
                print("Nhập độ mịn (0.5mm/0.7mm/1mm/1.5mm/2mm/2.5mm/3mm/3.5mm/4mm/4.5mm/5mm): ")
                val doMin: String = readln() ?: ""

                val butMuc = Pen(tenSanPham, giaBan, thuongHieu, mauSac, chatLieu, loaiMuc, doMin)
                listOfProduct.add(butMuc)
                println("Đã thêm bút mực: $tenSanPham thành công!")
            }

            4 -> {
                print("Nhập tên sản phẩm: ")
                val tenSanPham: String = readln() ?: ""
                print("Nhập giá bán: ")
                val giaBan: Double = readln()?.toDoubleOrNull() ?: 0.0
                print("Nhập thể loại: ")
                val theLoai: String = readln() ?: ""
                print("Nhập tác giả: ")
                val tacGia: String = readln() ?: ""
                print("Nhập nhà xuất bản: ")
                val nhaXuatBan: String = readln() ?: ""
                print("Nhập năm xuất bản: ")
                val namXuatBan: Int = readln()?.toIntOrNull() ?: 0
                print("Nhập ngôn ngữ: ")
                val ngonNgu: String = readln() ?: ""

                val sach = Book(tenSanPham, giaBan, nhaXuatBan, theLoai, tacGia, namXuatBan, ngonNgu)
                listOfProduct.add(sach)
                println("Đã thêm sách: $tenSanPham thành công!")
            }

            else -> println("Lựa chọn không hợp lệ!")
        }
    }

    fun searchingProduct(keyWord: String): List<Product> {
        val keyWord_Lowercase = keyWord.lowercase().trim()
        if(keyWord_Lowercase.isEmpty()) return emptyList()

        return listOfProduct.filter { sanPham ->
            sanPham.searchInfo().any { thongTin ->
                thongTin.contains(keyWord_Lowercase)
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