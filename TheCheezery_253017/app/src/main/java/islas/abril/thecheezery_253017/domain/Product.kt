package islas.abril.thecheezery_253017.domain

data class Product(
    val id: Int =0,
    val name: String,
    val price: Float,
    val image: String? = null,
    val description: String? = null

)