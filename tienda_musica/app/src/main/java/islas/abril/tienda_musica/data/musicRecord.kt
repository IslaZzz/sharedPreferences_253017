package islas.abril.tienda_musica.data

data class musicRecord(
    val id: Int,
    val nombre: String,
    val albumYear:Int,
    val author: String,
    val precio: Double,
    val imagen: String,
    val descripcion: String
)
