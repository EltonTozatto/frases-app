package br.com.eltontozatto.frases.ui.incluirfrase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.eltontozatto.frases.R
import br.com.eltontozatto.frases.databinding.ActivityIncluirFraseBinding

class IncluirFraseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIncluirFraseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIncluirFraseBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configurarListeners()
    }

    private fun configurarListeners() {
        configurarListenerCancelarButton()
        configurarListenerSalvarButton()
    }

    private fun configurarListenerCancelarButton() {
        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }

    private fun configurarListenerSalvarButton() {
        salvarFrase()
    }

    private fun salvarFrase() {
        binding.apply {
            btnSalvar.setOnClickListener {
                val autor = autorFrasesET.text.toString()
                val frase = frasesET.text.toString()

                autorFrasesTIL.error = if (autor.isEmpty())
                    getString(R.string.erro_sem_autor)
                else
                    null

                frasesTIL.error = if (frase.isEmpty())
                    getString(R.string.erro_sem_frase)
                else
                    null

                if (autor.isNotEmpty() && frase.isNotEmpty()) {
                    Toast.makeText(applicationContext, "Autor: $autor Frase: $frase", Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }
}