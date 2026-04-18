package islas.abril.tienda_musica.data

import android.content.Context
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import islas.abril.tienda_musica.data.dummies.ALBUMES
import islas.abril.tienda_musica.data.dummies.buscarPorId

class CarManager(context: Context) {
    private val sharedPreferences = context.getSharedPreferences("carrito_prefs", Context.MODE_PRIVATE)
    private val gson = Gson()

    fun guardarCarrito(productos: List<musicRecord>) {
        val ids = productos.map { it.id }
        val json = gson.toJson(ids)
        sharedPreferences.edit().putString("productos_ids", json).apply()
    }

    fun cargarCarrito(): List<musicRecord> {
        val json = sharedPreferences.getString("productos_ids", null) ?: return emptyList()
        val type = object : TypeToken<List<Int>>() {}.type
        val ids: List<Int> = gson.fromJson(json, type)
        return ids.mapNotNull { id -> buscarPorId(id) }
    }
}