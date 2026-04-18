package islas.abril.tienda_musica

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class AuthManager(context: Context) {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    fun isLoggedIn(): Boolean = prefs.getBoolean("is_logged_in", false)

    fun saveLoginStatus(status: Boolean) {
        prefs.edit().putBoolean("is_logged_in", status).apply()
    }

    fun logout() {
        prefs.edit().remove("is_logged_in").apply()
    }
}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val prefs = PreferenceManager(this)
        enableEdgeToEdge()
        setContent {
            MaterialTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    var screenState by remember {
                        mutableStateOf(if (prefs.isLoggedIn()) "HOME" else "LOGIN")
                    }

                    if (screenState == "LOGIN") {
                        LoginScreen(onLoginClick = {
                            prefs.saveLoginStatus(true)
                            screenState = "HOME"
                        })
                    } else {
                        HomeScreen(onLogoutClick = {
                            prefs.logout()
                            screenState = "LOGIN"
                        })
                    }
                }
            }
        }
    }
}

@Composable
fun LoginScreen(onLoginClick: () -> Unit) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var errorMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Inicio de Sesión", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (email == "abrilislas@gmail.com" && password == "titodoublep") {
                    onLoginClick()
                } else {
                    errorMessage = "Credenciales incorrectas"
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Iniciar Sesión")
        }
    }
}
@Composable
fun HomeScreen(onLogoutClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Bienvenido, usuario", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onLogoutClick() }) {
            Text("Cerrar Sesión")
        }
    }
}