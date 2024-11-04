package android.moveis.dispositivo.medimarque.view


import android.content.ContentValues
import android.content.Intent
import android.moveis.dispositivo.medimarque.R
import android.moveis.dispositivo.medimarque.SQLite
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.DatePicker
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Date


class FormAgendamento:AppCompatActivity() {

    private lateinit var dbForm: SQLite

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agendamento)

        dbForm = SQLite(this)
        val agendar = findViewById<Button>(R.id.btAgendar)
        agendar.setOnClickListener {
            cadastrarAgendamento()
        }
    }

    private fun limparCampos() {
        findViewById<TimePicker>(R.id.timePicker)
        findViewById<CheckBox>(R.id.medico1)
        findViewById<DatePicker>(R.id.datePicker)
    }

    private fun retornar() {
        val intent = Intent(this, FormAgendamento::class.java)
        startActivity(intent)
    }

    private fun cadastrarAgendamento(){
        val db = dbForm.writableDatabase

        val name = findViewById<CheckBox>(R.id.medico1)
        val date = findViewById<DatePicker>(R.id.datePicker)
        val hour = findViewById<TimePicker>(R.id.timePicker)

        fun insertDados(name: String, date: String, hour: String) {

            val db = dbForm.writableDatabase
            val values = ContentValues().apply {
                put("name", name)
                put("date", date)
                put("hour", hour)
            }
            val newRowId = db.insert(SQLite.TABLE_NAME, null, values)

            if (newRowId != -1L) {
                // Form cadastrado com sucesso
                Toast.makeText(this, "Agendamento criado com sucesso!", Toast.LENGTH_SHORT).show()
                limparCampos()
                retornar()
            } else {
                // Erro ao criar cadastro
                Toast.makeText(this, "Erro ao cadastrar agendamento..", Toast.LENGTH_SHORT).show()
            }
        }

    }

}