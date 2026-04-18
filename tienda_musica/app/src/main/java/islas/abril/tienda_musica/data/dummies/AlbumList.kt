package islas.abril.tienda_musica.data.dummies

import islas.abril.tienda_musica.data.musicRecord

val ALBUMES = listOf(
    musicRecord( id = 1,
        nombre = "Good kid, m.A.A.d city",
        albumYear = 2012,
        author = "Kendrick Lamar",
        precio = 450.0,
        imagen = "https://upload.wikimedia.org/wikipedia/en/9/93/KendrickGKMC.jpg",
        descripcion = "Un álbum conceptual que narra las experiencias de Lamar en su natal Compton."
            ),
    musicRecord(
        id = 2,
        nombre = "Because the Internet",
        albumYear = 2013,
        author = "Childish Gambino",
        precio = 380.0,
        imagen = "https://cdn-images.dzcdn.net/images/cover/a07c38caadefae99abe4047dbcb0c778/0x1900-000000-80-0-0.jpg",
        descripcion = "Un proyecto ambicioso que explora la desconexión en la era digital."
        ),
    musicRecord(
        id = 3,
        nombre = "The Car",
        albumYear = 2022,
        author = "Arctic Monkeys",
        precio = 520.0,
        imagen = "https://i.scdn.co/image/ab67616d0000b27307823ee6237208c835802663",
        descripcion = "Séptimo álbum de la banda con un sonido refinado y arreglos orquestales."
        ),
    musicRecord(
        id = 4,
        nombre = "PAPOTA",
        albumYear = 2024,
        author = "CA7RIEL & Paco Amoroso",
        precio = 300.0,
        imagen = "https://m.media-amazon.com/images/I/61E53H08aiL._UF1000,1000_QL80_.jpg",
        descripcion = "EP urbano con crítica satíricia a la industria musical. Partiendo del éxito del Tiny Desk de CA7RIEL & Paco y su salto a la fama internacional."
        ),
    musicRecord(
    id = 5,
    nombre = "Ctrl",
    albumYear = 2017,
    author = "SZA",
    precio = 410.0,
    imagen = "https://m.media-amazon.com/images/I/91AbZ7RgrEL._UF1000,1000_QL80_.jpg",
    descripcion = "Álbum debut aclamado por la crítica que redefine el R&B moderno."
    ),
    musicRecord(
    id = 6,
    nombre = "Gemini Rights",
    albumYear = 2022,
    author = "Steve Lacy",
    precio = 350.0,
    imagen = "https://m.media-amazon.com/images/I/A1lNu1TECiL._UF1000,1000_QL80_.jpg",
    descripcion = "Una mezcla de funk, rock y soul con un enfoque lo-fi."
    ),
    musicRecord(
    id = 7,
    nombre = "Red Moon in Venus",
    albumYear = 2023,
    author = "Kali Uchis",
    precio = 390.0,
    imagen = "https://i.scdn.co/image/ab67616d0000b27381fccd758776d16b87721b17",
    descripcion = "Un álbum atemporal y ardiente que explora las muchas dimensiones del amor y la feminidad."
    )
)

    fun filtrarPorNombre(query: String): List<musicRecord> {
        return if (query.isEmpty()) ALBUMES
        else ALBUMES.filter { it.nombre.contains(query, ignoreCase = true) }
    }

    fun buscarPorId(id: Int): musicRecord? {
        return ALBUMES.find { it.id == id }
    }