package android.moveis.dispositivo.medimarque.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.moveis.dispositivo.medimarque.R.*
import android.moveis.dispositivo.medimarque.adapter.EspecialidadesAdapter
import android.moveis.dispositivo.medimarque.databinding.ActivityHomeBinding
import android.moveis.dispositivo.medimarque.model.Especialidades
import androidx.recyclerview.widget.GridLayoutManager

class Home : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var especialidadesAdapter: EspecialidadesAdapter
    private val listaEspecialidades: MutableList<Especialidades> = mutableListOf()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome")

        binding.txtNomeUsuario.text = "Bem-vindo(a),$nome"
        val recyclerViewEspecialidades = binding.recyclerViewEspecialidades
        recyclerViewEspecialidades.layoutManager = GridLayoutManager(this, 2)
        especialidadesAdapter = especialidadesAdapter(this,listaEspecialidades)
        recyclerViewEspecialidades.setHasFixedSize(true)
        recyclerViewEspecialidades.adapter = especialidadesAdapter
    }

    private fun getEspecialidades(){

        val especialidade1 = Especialidades(R.drawable.img1, nome = "Cardiologista")
    }
}