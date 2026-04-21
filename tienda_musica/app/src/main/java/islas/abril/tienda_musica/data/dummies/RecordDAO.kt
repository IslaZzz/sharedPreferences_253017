package islas.abril.tienda_musica.data.dummies

import android.content.ContentValues
import islas.abril.tienda_musica.data.TiendaMusicaContract.RecordsEntry
import islas.abril.tienda_musica.data.musicRecord

class RecordDAO(private val dbHelper: DatabaseHelper) {

    fun insertRecord(record: musicRecord): Long {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put(RecordsEntry.COLUMN_NAME, record.nombre)
            put(RecordsEntry.COLUMN_YEAR, record.albumYear)
            put(RecordsEntry.COLUMN_AUTHOR, record.author)
            put(RecordsEntry.COLUMN_PRICE, record.precio)
            put(RecordsEntry.COLUMN_IMAGE, record.imagen)
            put(RecordsEntry.COLUMN_DESC, record.descripcion)

        }
        return db.insert(RecordsEntry.TABLE_NAME, null, values)
    }

    fun returnRecords(): List<musicRecord> {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            RecordsEntry.TABLE_NAME,
            arrayOf(
                RecordsEntry.COLUMN_ID,
                RecordsEntry.COLUMN_NAME,
                RecordsEntry.COLUMN_YEAR,
                RecordsEntry.COLUMN_AUTHOR,
                RecordsEntry.COLUMN_PRICE,
                RecordsEntry.COLUMN_IMAGE,
                RecordsEntry.COLUMN_DESC,

                ),
            null,
            null,
            null,
            null,
            null
        )
        val recordList = mutableListOf<musicRecord>()

            with(cursor) {
                while (moveToNext()) {
                    val id = getInt(getColumnIndexOrThrow(RecordsEntry.COLUMN_ID))
                    val name = getString(getColumnIndexOrThrow(RecordsEntry.COLUMN_NAME))
                    val year = getInt(getColumnIndexOrThrow(RecordsEntry.COLUMN_YEAR))
                    val author = getString(getColumnIndexOrThrow(RecordsEntry.COLUMN_AUTHOR))
                    val price = getDouble(getColumnIndexOrThrow(RecordsEntry.COLUMN_PRICE))
                    val imagen = getString(getColumnIndexOrThrow(RecordsEntry.COLUMN_IMAGE))
                    val description = getString(getColumnIndexOrThrow(RecordsEntry.COLUMN_DESC))

                    recordList.add(musicRecord(id, name, year, author, price, imagen, description))
                }

            return recordList
        }
    }

    fun getRecordByID(recordId: Int): musicRecord? {

        val db = dbHelper.readableDatabase
        var recordList =
        val cursor = db.query(
            RecordsEntry.TABLE_NAME,
            arrayOf(
                RecordsEntry.COLUMN_ID,
                RecordsEntry.COLUMN_NAME,
                RecordsEntry.COLUMN_YEAR,
                RecordsEntry.COLUMN_AUTHOR,
                RecordsEntry.COLUMN_PRICE,
                RecordsEntry.COLUMN_IMAGE,
                RecordsEntry.COLUMN_DESC,
            ),
            "${RecordsEntry.COLUMN_ID} =?",
            arrayOf(recordId.toString()),
            null,
            null,
            null
        )
        cursor.use {
            return if (it.moveToFirst()) {
                val id = it.getInt(it.getColumnIndexOrThrow(RecordsEntry.COLUMN_ID))
                val name = it.getString(it.getColumnIndexOrThrow(RecordsEntry.COLUMN_NAME))
                val year = it.getInt(it.getColumnIndexOrThrow(RecordsEntry.COLUMN_YEAR))
                val author = it.getString(it.getColumnIndexOrThrow(RecordsEntry.COLUMN_AUTHOR))
                val price = it.getDouble(it.getColumnIndexOrThrow(RecordsEntry.COLUMN_PRICE))
                val imagen = it.getString(it.getColumnIndexOrThrow(RecordsEntry.COLUMN_IMAGE))
                val description = it.getString(it.getColumnIndexOrThrow(RecordsEntry.COLUMN_DESC))

                return musicRecord(id, name, year, author, price, imagen, description)
            } else {
                null
            }
        }
       // return musicRecord
}
