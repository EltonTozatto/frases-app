package br.com.eltontozatto.frases.ui.incluirfrase

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.eltontozatto.frases.R
import br.com.eltontozatto.frases.data.Frase
import br.com.eltontozatto.frases.databinding.ActivityIncluirFraseBinding
import br.com.eltontozatto.frases.ui.main.MainActivity

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
                    Intent().apply {
                        putExtra(MainActivity.RETORNO, Frase(autor = autor, frase = frase))
                        setResult(RESULT_OK, this)
                    }
                    finish()
                }
            }
        }
    }
}