package Product

data class Notebook (
    override val nameProduct: String,
    override val price: Double,
    override val brand: String,
    val page: Int,
    val type: String,
    val color: String,
    val material: String,
    val size: String) : Product(nameProduct,price,brand) {
    override fun searchInfo(): List<String> {
        return listOf(
            nameProduct.lowercase(),
            brand.lowercase(),
            type.lowercase(),
            color.lowercase(),
            material.lowercase(),
            size.lowercase(),
            page.toString(),
            price.toString()
        )
    }

    override fun showInfo(): String {
        return "Vở ghi: $nameProduct | Giá: ${price}đ | Thương hiệu: $brand | Số trang: $page | Loại: $type | Màu bìa: $color | Giấy: $material | Kích thước: $size"
    }
}