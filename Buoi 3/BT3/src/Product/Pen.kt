package Product

data class Pen(
    override val nameProduct: String,
    override val price: Double,
    override val brand: String,
    val color: String,
    val material: String,
    val typeOfInk: String,
    val smoothness: String
) : Product(nameProduct, price, brand) {
    override fun searchInfo(): List<String> {
        return  listOf(
            nameProduct.lowercase(),
            brand.lowercase(),
            color.lowercase(),
            material.lowercase(),
            typeOfInk.lowercase(),
            smoothness.lowercase(),
            price.toString()
        )
    }

    override fun showInfo(): String {
        return "Bút mực: $nameProduct | Giá: ${price}đ | Thương hiệu: $brand | Màu: $color | Chất liệu: $material | Loại mực: $typeOfInk | Độ mịn: $smoothness"
    }
}

