package Controller

import Product.Book
import Product.Notebook
import Product.Pencil
import Product.Pen
import Product.Product
import View.View

class ProductController {
    private val listOfProduct = mutableListOf<Product>()

    fun getAllProduct() : List<Product> = listOfProduct.toList()

    fun addProduct(product: Product?): Boolean
    {
        if(product != null)
        {
            listOfProduct.add(product)
            return true
        }
        else{
            return false
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

    fun removeProduct(index: Int): Boolean{
        return if (index in 1..listOfProduct.size) {
            val removedProduct = listOfProduct.removeAt(index - 1)
            true
        } else {
            false
        }
    }

    fun editProduct(index: Int): Boolean{
        return if (index in 1..listOfProduct.size) {
            val getProduct = listOfProduct.get(index - 1)
            val updatedProduct = View.huongDanSua(getProduct)
            if (updatedProduct != null) {
                listOfProduct[index - 1] = updatedProduct
                true
            } else {
                false
            }
        } else {
            false
        }
    }
}