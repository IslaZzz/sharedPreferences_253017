package islas.abril.tienda_musica.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import islas.abril.tienda_musica.data.CarManager
import islas.abril.tienda_musica.data.dummies.filtrarPorNombre
import islas.abril.tienda_musica.data.musicRecord
import islas.abril.tienda_musica.ui.theme.Tienda_musicaTheme

@Composable
fun CatalogoScreen(
    carManager: CarManager,
    onVerDetalle: (Int) -> Unit,
    onVerCarrito: () -> Unit
) {
    var searchQuery by remember { mutableStateOf("") }

    val productosMostrados = remember(searchQuery) {
        filtrarPorNombre(searchQuery)
    }

    val carrito = remember {
        mutableStateListOf<musicRecord>().apply {
            addAll(carManager.cargarCarrito())
        }
    }
    Column(
        modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colorScheme.background)
        ){

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp)
        ) {
            TextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                modifier = Modifier.weight(1f),
                placeholder = { Text("Buscar álbum...") },
                singleLine = true,
                colors = TextFieldDefaults.colors(
                    // Color del texto que escribe el usuario
                    focusedTextColor = MaterialTheme.colorScheme.primary,
                    unfocusedTextColor = MaterialTheme.colorScheme.primary,
                )
            )
            IconButton(onClick = onVerCarrito) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Carrito",
                    tint = MaterialTheme.colorScheme.secondary)
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(productosMostrados) { musicRecord ->
                ItemProducto(
                    musicRecord = musicRecord,
                    onAgregar = {
                        carrito.add(musicRecord)
                        carManager.guardarCarrito(carrito)
                    },
                    onClick = { onVerDetalle(musicRecord.id) }
                )
            }
        }
    }
        }
}

//PREVIEW

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CatalogoScreenPreview() {
    val context = LocalContext.current
    // Nota: CarManager en Preview usará SharedPreferences reales del emulador/dispositivo de preview
    val carManager = CarManager(context)

    Tienda_musicaTheme {
        CatalogoScreen(
            carManager = carManager,
            onVerDetalle = {},
            onVerCarrito = {}
        )
    }
}

