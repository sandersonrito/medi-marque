package android.moveis.dispositivo.medimarque.view

import android.annotation.SuppressLint
import android.content.Intent
import android.moveis.dispositivo.medimarque.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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

        binding.txtNomeUsuario.text = "Bem-vindo,$nome"
        val recyclerViewEspecialidades = binding.recyclerViewEspecialidades
        recyclerViewEspecialidades.layoutManager = GridLayoutManager(this, 2)
        especialidadesAdapter = EspecialidadesAdapter(this,listaEspecialidades)
        recyclerViewEspecialidades.setHasFixedSize(true)
        recyclerViewEspecialidades.adapter = especialidadesAdapter
        getEspecialidades()

        binding.btAgendar.setOnClickListener {
            val intent = Intent(this, Agendamento::class.java)
            intent.putExtra("nome",nome)
            startActivity(intent)
        }
    }

    private fun getEspecialidades(){

        val especialidade1 = Especialidades(R.drawable.cardiologia,"Cardiologia")
        listaEspecialidades.add(especialidade1)

        val especialidade2 = Especialidades(R.drawable.gastroenterologia,"Gastroenterologia")
        listaEspecialidades.add(especialidade2)

        val especialidade3 = Especialidades(R.drawable.neurologia,"Neurologia")
        listaEspecialidades.add(especialidade3)

        val especialidade4 = Especialidades(R.drawable.oftalmologia,"Oftalmologia")
        listaEspecialidades.add(especialidade4)
    }
}