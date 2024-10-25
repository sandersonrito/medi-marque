package android.moveis.dispositivo.medimarque

import android.moveis.dispositivo.medimarque.databinding.ActivityMainBinding
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import android.moveis.dispositivo.medimarque.ui.theme.MediMarqueTheme
import android.view.View
import androidx.compose.ui.graphics.Color
import com.google.android.material.snackbar.Snackbar

class MainActivity : ComponentActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btLogin.setOnClickListener{

            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()

            when{
                nome.isEmpty() -> {
                    mensagem(it,"Coloque o seu nome!")
                }senha.isEmpty() ->{
                    mensagem(it,"Preencha a senha!")
                }senha.length <=5 -> {
                    mensagem(it, menssagem "A senha precisa de pelo menos 6 caracteres!");
                }else -> {
                    navegarPraHome(nome)

                }

            }

        }
    }

    private fun menssagem(view: View, mensagem: String ){
        val snackbar = snackbar.make(view,mensagem,Snackbar.LENGHT_SHORT)
        snackbar.setBackgroundTint(color.parseColor(colorString: "#FF0000"))
        snackbar.setTextColor(Color.parseColor(colorString: "#FFFFFF"))
        snackbar.show()

    }

    private fun  navegarPraHome(nome: String){
        val intent = Intent(this,Home::class.java)
        intent.puExtra("nome",nome)
        StartActivity(intent)

    }
}
