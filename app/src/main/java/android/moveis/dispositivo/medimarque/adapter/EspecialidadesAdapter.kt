package android.moveis.dispositivo.medimarque.adapter

import android.content.Context
import android.moveis.dispositivo.medimarque.databinding.EspecialidadesItemBinding
import android.moveis.dispositivo.medimarque.model.Especialidades
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class EspecialidadesAdapter(private val context: Context, private val listaEspecialidades: MutableList<Especialidades>):
    RecyclerView.Adapter<EspecialidadesAdapter.EspecialidadesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EspecialidadesViewHolder {
        val itemLista = EspecialidadesItemBinding.inflate(LayoutInflater.from(context),parent,false)
        return EspecialidadesViewHolder(itemLista)
    }

    override fun getItemCount() = listaEspecialidades.size

    override fun onBindViewHolder(holder: EspecialidadesViewHolder, position: Int) {
        holder.imgEspecialidade.setImageResource(listaEspecialidades[position].img!!)
        holder.txtEspecialidade.text = listaEspecialidades[position].nome
    }

    inner class EspecialidadesViewHolder(binding: EspecialidadesItemBinding): RecyclerView.ViewHolder(binding.root){
        val imgEspecialidade = binding.imgEspecialidade
        val txtEspecialidade = binding.txtEspecialidade
    }
}