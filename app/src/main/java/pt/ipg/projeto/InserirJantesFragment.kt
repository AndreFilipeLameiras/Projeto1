package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentListaJantesBinding


class InserirJantesFragment : Fragment() {
    private var _binding: FragmentListaJantesBinding? = null
    private val binding get() = _binding!!

    private lateinit var editTextNomeJante: EditText
    private lateinit var editTextLargura: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var editTextRaio: EditText
    private lateinit var editTextPreco: EditText



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        /*(activity as MainActivity).fragment = this
        (activity as MainActivity).idMenuAtual = R.menu.menu_edicao*/
        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        _binding = FragmentListaJantesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        editTextNomeJante = view.findViewById(R.id.editTextNomeJantes)
        editTextLargura = view.findViewById(R.id.editTextLargura)
        editTextAltura = view.findViewById(R.id.editTextAltura)
        editTextRaio = view.findViewById(R.id.editTextRaio)
        editTextPreco = view.findViewById(R.id.editTextPreco)

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar -> guardar()
            R.id.action_cancelar -> navegaListaJante()

            else -> return false
        }
        return true
    }

    fun navegaListaJante() {
        findNavController().navigate(R.id.action_inserirJantesFragment_to_listaJantesFragment)
    }

    fun guardar() {
        val nomeJante = editTextNomeJante.text.toString()
        if (nomeJante.isBlank()) {
            editTextNomeJante.setError(getString(R.string.nome_jante_obrigatorio))
            editTextNomeJante.requestFocus()
            return

        }

        val largura = editTextLargura.text.toString().toLong()
        if (largura.toString().isEmpty()) {
            editTextLargura.setError(getString(R.string.Introduza_largura))
            editTextLargura.requestFocus()
            return

        }

        val altura = editTextAltura.text.toString().toLong()
        if (altura.toString().isEmpty()) {
            editTextAltura.setError(getString(R.string.Introduza_altura))
            editTextAltura.requestFocus()
            return

        }

        val raio = editTextRaio.text.toString().toLong()
        if (raio.toString().isEmpty()) {
            editTextRaio.setError(getString(R.string.Introduza_raio))
            editTextRaio.requestFocus()
            return

        }


        val preco = editTextPreco.text.toString().toDouble()
        if (preco.toString().isEmpty()) {
            editTextPreco.setError(getString(R.string.Introduza_preco))
            editTextPreco.requestFocus()
            return

        }


        val jante = Jante(nome = nomeJante, largura =  largura, altura = altura, raio = raio, preco = preco)

        val enderecoJanteInserida = activity?.contentResolver?.insert(ContentProviderCarros.ENDERECO_JANTES, jante.toContentValues())

        if(enderecoJanteInserida == null) {
            Snackbar.make(
                editTextNomeJante,
                R.string.erro_guardar_jante,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.jante_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaJante()
    }


}