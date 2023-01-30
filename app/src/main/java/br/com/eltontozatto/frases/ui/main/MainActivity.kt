package br.com.eltontozatto.frases.ui.main

import android.content.Intent
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import br.com.eltontozatto.frases.data.Frase
import br.com.eltontozatto.frases.databinding.ActivityMainBinding
import br.com.eltontozatto.frases.ui.incluirfrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val retornoFrase = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(RETORNO)) {
                    if (VERSION.SDK_INT >= 33) {
                        Log.i("XPEInfo", "Retorno: ${it.getParcelableExtra(RETORNO, Frase::class.java)}")
                    } else {
                        Log.i("XPEInfo", "Retorno: ${it.getParcelableExtra<Frase>(RETORNO)}")
                    }
                }
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        configurarListeners()
    }

    private fun configurarListeners() {
        configurarFabListeners()
    }

    private fun configurarFabListeners() {
        binding.fabAdicionarFrase.setOnClickListener {
            Intent(this, IncluirFraseActivity::class.java).let {
                retornoFrase.launch(it)
            }
        }
    }

    companion object {
        const val RETORNO = "retorno_frase"
    }
}