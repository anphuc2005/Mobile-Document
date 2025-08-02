package Product

data class Book(
    override val nameProduct: String,
    override val price: Double,
    override val brand: String,
    val category: String,
    val author: String,
    val yearOfPublic: Int,
    val language: String
): Product(nameProduct, price, brand) {
    override fun searchInfo(): List<String> {
        return listOf(
            nameProduct.lowercase(),
            category.lowercase(),
            author.lowercase(),
            brand.lowercase(),
            language.lowercase(),
            yearOfPublic.toString(),
            price.toString()

        )
    }

    override fun showInfo(): String {
        return "Sách: $nameProduct | Giá: ${price}đ | Thương hiệu: $brand | Thể loại: $category | Tác giả: $author | NXB: $brand | Năm: $yearOfPublic | Ngôn ngữ: $language"
    }
}

