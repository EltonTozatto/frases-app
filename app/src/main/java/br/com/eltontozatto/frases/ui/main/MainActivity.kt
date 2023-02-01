package br.com.eltontozatto.frases.ui.main

import android.content.Intent
import android.opengl.Visibility
import android.os.Build.VERSION
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import br.com.eltontozatto.frases.R
import br.com.eltontozatto.frases.data.Frase
import br.com.eltontozatto.frases.databinding.ActivityMainBinding
import br.com.eltontozatto.frases.ui.incluirfrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding
    private val retornoFrase = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { activityResult ->
        if (activityResult.resultCode == RESULT_OK) {
            activityResult.data?.let {
                if (it.hasExtra(RETORNO)) {
                    if (VERSION.SDK_INT >= 33) {
                        Log.i("XPEInfo", "Retorno: ${it.getParcelableExtra(RETORNO, Frase::class.java)}")
                        viewModel.salvarFrase(it.getParcelableExtra(RETORNO, Frase::class.java)!!)
                    } else {
                        Log.i("XPEInfo", "Retorno: ${it.getParcelableExtra<Frase>(RETORNO)}")
                        viewModel.salvarFrase(it.getParcelableExtra(RETORNO)!!)
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
        configurarObversers()
        iniciarDados()
    }

    private fun iniciarDados() {
        viewModel.inicializarLista()
    }

    private fun configurarObversers() {
        configurarRecyclerView()
    }

    private fun configurarRecyclerView() {
        viewModel.listaDeFrase.observe(this) { lista ->
            Log.i("XPEObserver", "Frases: ${lista}")
            atualizarLista(lista)
        }
    }

    private fun atualizarLista(lista: List<Frase>?) {
        if (lista.isNullOrEmpty()) {
            binding.apply {
                rvFrases.visibility = View.GONE
                tvListaFraseVazia.visibility = View.VISIBLE
            }
        } else {
            binding.apply {
                rvFrases.visibility = View.VISIBLE
                tvListaFraseVazia.visibility = View.GONE
                rvFrases.adapter = FraseAdapter(listaDeFrase = lista)
            }
        }
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

        binding.fabAdicionarFrase.setOnLongClickListener {
            viewModel.limparListaDeFrases()
            Toast.makeText(this, R.string.lista_limpa_sucesso, Toast.LENGTH_LONG).show()
            it.isLongClickable
        }
    }

    companion object {
        const val RETORNO = "retorno_frase"
    }
}