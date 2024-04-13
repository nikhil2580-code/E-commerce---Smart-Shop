package com.nikhilkhairnar.shopsmart

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CartDatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "cart_database"
        private const val TABLE_NAME = "cart_products"
        private const val COLUMN_ID = "id"
        private const val COLUMN_PRODUCT_ID = "product_id"
        private const val COLUMN_QUANTITY = "quantity"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME " +
                "($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_PRODUCT_ID INTEGER, " +
                "$COLUMN_QUANTITY INTEGER)"

        db.execSQL(createTableQuery)
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun insertCartProduct(cartProduct: CartProduct): Long {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_PRODUCT_ID, cartProduct.product.id)
        contentValues.put(COLUMN_QUANTITY, cartProduct.quantity)

        return db.insert(TABLE_NAME, null, contentValues)
    }

    fun getAllCartProducts(): List<CartProduct> {
        val db = readableDatabase
        val cartProducts = mutableListOf<CartProduct>()

        val cursor = db.query(
            TABLE_NAME,
            null,
            null,
            null,
            null,
            null,
            null
        )

        with(cursor) {
            while (moveToNext()) {
                val productId = getInt(getColumnIndexOrThrow(COLUMN_PRODUCT_ID))
                val quantity = getInt(getColumnIndexOrThrow(COLUMN_QUANTITY))

                val product = getProductById(productId)
                if (product != null) {
                    val cartProduct = CartProduct(product, quantity)
                    cartProducts.add(cartProduct)
                }
            }
        }

        db.close()
        return cartProducts
    }

    fun updateCartProductQuantity(productId: Int, newQuantity: Int): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COLUMN_QUANTITY, newQuantity)

        return db.update(
            TABLE_NAME,
            contentValues,
            "$COLUMN_PRODUCT_ID = ?",
            arrayOf(productId.toString())
        )
    }

    fun deleteCartProduct(productId: Int): Int {
        val db = this.writableDatabase
        return db.delete(TABLE_NAME, "$COLUMN_PRODUCT_ID = ?", arrayOf(productId.toString()))
    }

    private fun getProductById(productId: Int): Product? {
        return ProductDataSource.getProductById(productId)
    }
}
