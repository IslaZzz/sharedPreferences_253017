package islas.abril.tienda_musica.data.dummies

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import islas.abril.tienda_musica.data.TiendaMusicaContract

class DatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("PRAGMA foregin_keys = ON")
        db?.execSQL(
            """
                    CREATE TABLE ${TiendaMusicaContract.RecordsEntry.TABLE_NAME}
                        (
                            ${TiendaMusicaContract.RecordsEntry.COLUMN_ID} INTEGER PRIMARY KEY AUTOINCREMENT,
                            ${TiendaMusicaContract.RecordsEntry.TABLE_NAME} TEXT NOT NULL,
                            ${TiendaMusicaContract.RecordsEntry.COLUMN_AUTHOR} TEXT NOT NULL,
                            ${TiendaMusicaContract.RecordsEntry.COLUMN_IMAGE} TEXT,
                            ${TiendaMusicaContract.RecordsEntry.COLUMN_PRICE} REAL NOT NULL,
                            ${TiendaMusicaContract.RecordsEntry.COLUMN_YEAR} TEXT NOT NULL,
                            ${TiendaMusicaContract.RecordsEntry.COLUMN_DESC} TEXT
                        )
                 """.trimIndent())
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        db?.execSQL(
            "DROP TABLE IF EXISTS ${TiendaMusicaContract.RecordsEntry.TABLE_NAME}"
        )
    }

    companion object{
        private const val DATABASE_NAME="recordsStore.db"
        private const val DATABASE_VERSION=1
    }
}
