package pt.ipg.projeto.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Bem_vindo_ao_novo_configurador_de_carros"
    }
    val text: LiveData<String> = _text
}