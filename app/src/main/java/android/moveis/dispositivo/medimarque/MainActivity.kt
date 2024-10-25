package android.moveis.dispositivo.medimarque

import android.content.Intent
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
import android.moveis.dispositivo.medimarque.view.Home
import android.view.View
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import android.graphics.Color


//classe para definir o usuário e a senha
class MainActivity : AppCompatActivity() {

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
                    mensagem(it,"Digite o seu nome!")
                }senha.isEmpty() ->{
                    mensagem(it,"Digite sua senha!")
                }senha.length <=5 -> {
                    mensagem(it, "A senha precisa ter pelo menos 6 caracteres!")
                }else -> {
                    navegarPraHome(nome)

                }

            }

        }
    }

    //pop-up de negatíva de menssagem.
    private fun mensagem(view: View, mensagem: String){
        val snackbar = Snackbar.make(view, mensagem, Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor("#FF0000"))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    private fun navegarPraHome(nome: String){
        val intent = Intent(this,Home::class.java)
        intent.putExtra("nome", nome)
        startActivity(intent)

    }




}
