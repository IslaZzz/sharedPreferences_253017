package islas.abril.thecheezery_253017.data

import android.content.ContentValues
import androidx.room.util.getColumnIndexOrThrow
import islas.abril.thecheezery_253017.domain.Product
import islas.abril.thecheezery_253017.data.CheezeryContract.ProductsEntry



class ProductDAO(private val dbHelper: DatabaseHelper) {

    fun insertProduct(product: Product): Long{
        val db = dbHelper.writableDatabase
        val values= ContentValues().apply {
            put(ProductsEntry.COLUMN_NAME,product.name)
            put(ProductsEntry.COLUMN_IMAGE,product.image)
            put(ProductsEntry.COLUMN_PRICE,product.price)
            put(ProductsEntry.COLUMN_DESC,product.description)
        }
        return db.insert(ProductsEntry.TABLE_NAME,null,values)
    }

    fun returnProducts():List<Product> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            ProductsEntry.TABLE_NAME,
            arrayOf(
                ProductsEntry.COLUMN_NAME,
                ProductsEntry.COLUMN_IMAGE,
                ProductsEntry.COLUMN_PRICE,
                ProductsEntry.COLUMN_DESC
            ),
            null,
            null,
            null,
            null,
            null
        )
        val productList = mutableListOf<Product>()

        fun getAllProducts(): List<Product> {
            with(cursor) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow(ProductsEntry.COLUMN_ID))
                    val name = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_NAME))
                    val price = getFloat(getColumnIndexOrThrow(ProductsEntry.COLUMN_PRICE))
                    val image = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_IMAGE))
                    val desc = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_DESC))
                    productList.add(Product(id,name,price,image,desc))
                }
            }
            return productList
        }
        return getAllProducts()
    }

    fun getProductById(productId:Int){

        val db = dbHelper.readableDatabase
        val cursor = db.query(
            ProductsEntry.TABLE_NAME,
            arrayOf(
                ProductsEntry.COLUMN_NAME,
                ProductsEntry.COLUMN_IMAGE,
                ProductsEntry.COLUMN_PRICE,
                ProductsEntry.COLUMN_DESC
            ),
            "${ProductsEntry.COLUMN_ID} =?",
            arrayOf(productId.toString()),
            null,
            null,
            null
        )
        cursor.use{
            if(it.moveToFirst()) {

                val id = getInt(getColumnIndexOrThrow(ProductsEntry.COLUMN_ID))
                val name = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_NAME))
                val price = getFloat(getColumnIndexOrThrow(ProductsEntry.COLUMN_PRICE))
                val image = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_IMAGE))
                val desc = getString(getColumnIndexOrThrow(ProductsEntry.COLUMN_DESC))
                val product = Product(id,name,price,image,desc)

            }else{
                null
            }
        }
    }
}