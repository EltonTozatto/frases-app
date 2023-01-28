package br.com.eltontozatto.frases.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.com.eltontozatto.frases.databinding.ActivityMainBinding
import br.com.eltontozatto.frases.ui.incluirfrase.IncluirFraseActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
            startActivity(Intent(this, IncluirFraseActivity::class.java))
        }
    }
}