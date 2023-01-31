package br.com.eltontozatto.frases.data.repository

import br.com.eltontozatto.frases.data.Frase

class MemoryRepository(novaLista: MutableList<Frase>) {
    private val listDB: MutableList<Frase> = novaLista

    fun salvar(frase: Frase) {
        listDB.add(frase)
    }

    fun limparLista() {
        listDB.clear()
    }

    fun retornaLista() = listDB.toList()
}