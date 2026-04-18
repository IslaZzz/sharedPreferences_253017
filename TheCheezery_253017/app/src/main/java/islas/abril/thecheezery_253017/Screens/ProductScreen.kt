package islas.abril.thecheezery_253017.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import islas.abril.thecheezery_253017.R
import islas.abril.thecheezery_253017.domain.Product

@Composable
fun ProductsScreen(products: List<Product>) {
    Column(
        Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Products",
            textAlign = TextAlign.Center,
            fontSize = 30.sp
        )
        LazyColumn() {
            items(products) { product ->
                Row(){
                    Image(painter=painterResource(R.drawable.ic_launcher_foreground), contentDescription = "")
                }
                Column() {
                    Text("${product.name}")
                    Text("${product.price}")
                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun previewProductsScreen(){
  //  ProductsScreen()
}