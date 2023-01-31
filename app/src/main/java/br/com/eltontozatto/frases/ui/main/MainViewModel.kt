package br.com.eltontozatto.frases.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.eltontozatto.frases.data.Frase
import br.com.eltontozatto.frases.data.repository.MemoryRepository

class MainViewModel: ViewModel() {
    private var memoryRepository: MemoryRepository = MemoryRepository(mutableListOf())
    private val _listaDeFrases = MutableLiveData<List<Frase>>()

    fun inicializarLista() {
        _listaDeFrases.value = memoryRepository.retornaLista()
    }

    fun salvarFrase(frase: Frase) {
        Log.i("XPEInfo", "Frase recebida: ${frase}")

        memoryRepository.salvar(frase)
        atualizarListaDeFrases()
    }

    fun limparListaDeFrases() {
        Log.i("XPEInfo", "Iniciando processo de limpeza dos dados.")

        memoryRepository.limparLista()
        atualizarListaDeFrases()
    }

    private fun atualizarListaDeFrases() {
        _listaDeFrases.value = memoryRepository.retornaLista()
    }
}