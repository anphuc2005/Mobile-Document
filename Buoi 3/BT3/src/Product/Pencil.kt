package Product

data class Pencil(
    override val nameProduct: String,
    override val price: Double,
    override val brand: String,
    val color: String,
    val material: String,
    val hardness: String
) : Product(nameProduct, price, brand) {
    override fun searchInfo(): List<String> {
        return  listOf(
            nameProduct.lowercase(),
            brand.lowercase(),
            color.lowercase(),
            material.lowercase(),
            hardness.lowercase(),
            price.toString()
        )
    }

    override fun showInfo(): String {
        return "Bút chì: $nameProduct | Giá: ${price}đ | Thương hiệu: $brand | Màu: $color | Chất liệu: $material | Độ cứng: $hardness"
    }
}
