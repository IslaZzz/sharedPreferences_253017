package islas.abril.tienda_musica.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonDefaults.shape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import islas.abril.tienda_musica.R
import islas.abril.tienda_musica.data.musicRecord
import islas.abril.tienda_musica.ui.theme.Tienda_musicaTheme

@Composable
fun ItemProducto(
    musicRecord: musicRecord,
    onAgregar: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xFF121212) ,
                contentColor = MaterialTheme.colorScheme.primary
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
        ){
            AsyncImage(
                model = musicRecord.imagen,
                contentDescription = "Portada del álbum",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp),
                contentScale = ContentScale.Crop
            )
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = musicRecord.nombre,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.Black
                    )
                    Spacer(modifier = Modifier.padding(5.dp))
                    Text(
                        text = musicRecord.author,
                        style = MaterialTheme.typography.bodySmall,
                        color=MaterialTheme.colorScheme.secondary
                    )
                    Spacer(modifier = Modifier.padding(5.dp))

                    Text(
                        text = "$${musicRecord.precio}",
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
                Button(
                    onClick = onAgregar,
                    shape = RoundedCornerShape(4.dp),
                    modifier = Modifier.padding(start = 8.dp, top=50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.secondary, // Fondo del botón
                        contentColor = MaterialTheme.colorScheme.primary     // Color del texto/icono
                    )
                ) {
                    Text("Añadir")
                }
            }
            Text(
                text = "$${musicRecord.descripcion}",
                style = MaterialTheme.typography.bodySmall,
                modifier = Modifier.padding(start = 15.dp, bottom = 10.dp ),

            )
        }
    }
}

@Preview(showBackground = false)
@Composable
fun ItemProductoPreview() {
    val dummyRecord = musicRecord(
    id = 1,
    nombre = "Good kid, m.A.A.d city",
    albumYear = 2012,
    author = "Kendrick Lamar",
    precio = 450.0,
    imagen = "https://upload.wikimedia.org/wikipedia/en/9/93/KendrickGKMC.jpg",
    descripcion = "Álbum conceptual"
    )
    Tienda_musicaTheme {
        ItemProducto(dummyRecord, {}, {})
    }
    Tienda_musicaTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            ItemProducto(
                musicRecord = dummyRecord,
                onAgregar = {},
                onClick = {}
            )
        }
    }
}