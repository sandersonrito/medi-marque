package android.moveis.dispositivo.medimarque.view

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.moveis.dispositivo.medimarque.R
import android.moveis.dispositivo.medimarque.databinding.ActivityAgendamentoBinding
import android.os.Build
import android.view.View
import androidx.annotation.RequiresApi
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

                }
                hora < "8:00" && hora > "19:00" -> {

                }
                data.isEmpty() -> {

                }
                medico1.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                }
                medico2.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                }
                medico3.isChecked && data.isNotEmpty() && hora.isNotEmpty() -> {

                }
                else -> {

                }
            }
        }
    }
}