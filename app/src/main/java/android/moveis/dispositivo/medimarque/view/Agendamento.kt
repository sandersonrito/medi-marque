package android.moveis.dispositivo.medimarque.view

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.moveis.dispositivo.medimarque.databinding.ActivityAgendamentoBinding
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import java.util.Calendar

class Agendamento : AppCompatActivity() {

    private lateinit var binding: ActivityAgendamentoBinding
    private val calendar: Calendar = Calendar.getInstance()
    private var data: String = ""
    private var hora: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAgendamentoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()
        val nome = intent.extras?.getString("nome").toString()

        val datePicker = binding.datePicker
        datePicker.setOnDateChangedListener { _, year, monthOfyear, dayOfMonth ->

            calendar.set(Calendar.YEAR,year)
            calendar.set(Calendar.MONTH,monthOfyear)
            calendar.set(Calendar.DAY_OF_MONTH,dayOfMonth)

            var dia = dayOfMonth.toString()
            val mes: String

            if (dayOfMonth < 10){
                dia = "0$dayOfMonth"
            }
            if (monthOfyear < 10){
                mes = "" + (monthOfyear+1)
            }else{
                mes = (monthOfyear +1).toString()
            }

            data = "$dia / $mes / $year"
        }

        binding.timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->

            val minuto: String

            if (minute < 10){
                minuto = "0$minute"
            }else{
                minuto = minute.toString()
            }

            hora = "$hourOfDay:$minuto"
        }
        binding.timePicker.setIs24HourView(true) //formato de 24 horas

        binding.btAgendar.setOnClickListener {

            val medico1 = binding.medico1
            val medico2 = binding.medico2
            val medico3 = binding.medico3

            when{
                hora.isEmpty() -> {
                    mensagem(it, "Preencha o horário", "#FF0000")
                }
                hora < "8:00" && hora > "19:00" -> {
                    mensagem(it,"Medi Marque Esta Fechado - horário de atendimento das 08:00 as 19:00", "#FF0000")
                }
                data.isEmpty() -> {
                    mensagem(it, "Coloque uma data!", "#FF0000")
                }
                medico1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome,"Lucas Sampaio Leite", data, hora)
                }
                medico2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome,"Rosiberto Santos Goncalves", data, hora)
                }
                medico3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {
                    salvarAgendamento(it, nome,"Magno Felipe Holanda", data, hora)
                }
                else -> {
                    mensagem(it, "Escolha um médico !", "#FF0000")
                }
            }
        }
    }

    private fun mensagem(view: View, mensagem: String,cor: String){
        val snackbar = Snackbar.make(view,mensagem,Snackbar.LENGTH_SHORT)
        snackbar.setBackgroundTint(Color.parseColor(cor))
        snackbar.setTextColor(Color.parseColor("#FFFFFF"))
        snackbar.show()
    }

    //método de salvar os agendamentos.

    private fun salvarAgendamento(view: View, client: String, medico: String, data: String, hora: String){

        val db = FirebaseFirestore.getInstance()

        val dadosUsuario = hashMapOf(
            "cliente" to client,
            "medico" to medico,
            "data" to data,
            "hora" to hora
        )

        db.collection("agendamento").document(client).set(dadosUsuario).addOnCompleteListener {
            mensagem(view,"Agendamento realizado com sucesso!", "#FF03DAC5")
        }.addOnFailureListener {
            mensagem(view, "Erro no servidor!", "#FF0000")

        }

    }
}