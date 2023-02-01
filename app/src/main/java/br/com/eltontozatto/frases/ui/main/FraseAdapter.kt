package br.com.eltontozatto.frases.ui.main

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import br.com.eltontozatto.frases.data.Frase
import br.com.eltontozatto.frases.databinding.ListItemFrasesBinding

class FraseAdapter(private val listaDeFrase: List<Frase>): Adapter<FraseAdapter.ViewHolder>() {
    private lateinit var binding: ListItemFrasesBinding

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        fun bind(item: Frase) {
            binding.apply {
                tvAutor.text = item.autor
                tvFrase.text = item.frase
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ListItemFrasesBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(item = listaDeFrase[position])
    }

    override fun getItemCount(): Int = listaDeFrase.size
}