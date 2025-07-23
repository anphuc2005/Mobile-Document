package Product

abstract class Product(
    open val nameProduct: String,
    open val price: Double,
    open val brand: String
) {
    abstract fun searchInfo(): List<String>
    abstract fun showInfo(): String
}