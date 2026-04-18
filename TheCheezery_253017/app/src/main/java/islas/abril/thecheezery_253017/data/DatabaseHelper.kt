package islas.abril.thecheezery_253017.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import islas.abril.thecheezery_253017.data.CheezeryContract

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("PRAGMA foregin_keys = ON")
        db?.execSQL(
            """
                    CREATE TABLE ${CheezeryContract.ProductsEntry.TABLE_NAME}
                        (
                            ${CheezeryContract.ProductsEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                            ${CheezeryContract.ProductsEntry.COLUMN_NAME} TEXT NOT NULL,
                            ${CheezeryContract.ProductsEntry.COLUMN_IMAGE} TEXT,
                            ${CheezeryContract.ProductsEntry.COLUMN_PRICE} REAL NOT NULL,
                            ${CheezeryContract.ProductsEntry.COLUMN_DESC} TEXT
                        )
                 """.trimIndent())
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL(
            "DROP TABLE IF EXISTS ${CheezeryContract.ProductsEntry.TABLE_NAME}"
        )
    }

    companion object{
        private const val DATABASE_NAME="cheezery.db"
        private const val DATABASE_VERSION=1
    }
}