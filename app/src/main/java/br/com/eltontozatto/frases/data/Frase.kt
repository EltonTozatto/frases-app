package br.com.eltontozatto.frases.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Frase(var autor: String, var frase: String): Parcelable
