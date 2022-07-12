package pt.ipg.projeto

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentEditarJantesBinding



class EditarJantesFragment : Fragment() {
    private var _binding: FragmentEditarJantesBinding? = null
    private val binding get() = _binding!!

    private var jante: Jante? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditarJantesBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        if (arguments != null) {
            jante = EditarJantesFragmentArgs.fromBundle(arguments!!).jante

            if (jante != null) {
                binding.editTextNomeJantes.setText(jante!!.nome)
                binding.editTextLargura.setText(jante!!.largura.toString())
                binding.editTextAltura.setText(jante!!.altura.toString())
                binding.editTextRaio.setText(jante!!.raio.toString())
                binding.editTextPreco.setText(jante!!.preco.toString())
            }
        }

    }




    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar ->
                guardar()
            R.id.action_cancelar -> navegaListaJante()

            else -> return false
        }
        return true
    }

    fun navegaListaJante() {
        findNavController().navigate(R.id.action_editarJantesFragment_to_listaJantesFragment)
    }

    fun guardar() {
        val nomeJante = binding.editTextNomeJantes.text.toString()
        if (nomeJante.isBlank()) {
            binding.editTextNomeJantes.setError(getString(R.string.nome_jante_obrigatorio))
            binding.editTextNomeJantes.requestFocus()
            return

        }

        val largura = binding.editTextLargura.text.toString().toLong()
        if (largura.toString().isEmpty()) {
            binding.editTextLargura.setError(getString(R.string.Introduza_largura))
            binding.editTextLargura.requestFocus()
            return

        }

        val altura = binding.editTextAltura.text.toString().toLong()
        if (altura.toString().isEmpty()) {
            binding.editTextAltura.setError(getString(R.string.Introduza_altura))
            binding.editTextAltura.requestFocus()
            return

        }

        val raio = binding.editTextRaio.text.toString().toLong()
        if (raio.toString().isEmpty()) {
            binding.editTextRaio.setError(getString(R.string.Introduza_raio))
            binding.editTextRaio.requestFocus()
            return

        }


        val preco = binding.editTextPreco.text.toString().toDouble()
        if (preco.toString().isEmpty()) {
            binding.editTextPreco.setError(getString(R.string.Introduza_preco))
            binding.editTextPreco.requestFocus()
            return

        }

        val janteGuardada =
            if (jante == null) {
                insereJante(nomeJante, largura, altura, raio, preco)
            } else {
                alteraJante(nomeJante, largura, altura, raio, preco)
            }

        if (janteGuardada) {
            Toast.makeText(requireContext(), R.string.jante_guardada_sucesso, Toast.LENGTH_LONG)
                .show()
            navegaListaJante()
        } else {
            Snackbar.make(binding.editTextNomeJantes, R.string.erro_guardar_jante, Snackbar.LENGTH_INDEFINITE).show()
            return
        }


    }

    private fun alteraJante(nomeJante: String, largura: Long, altura: Long, raio: Long, preco: Double): Boolean {
        val jante = Jante(nomeJante, largura, altura, raio, preco)

        val enderecoJante = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_JANTES, "${this.jante!!.id}")

        val registoAlterados = requireActivity().contentResolver.update(enderecoJante, jante.toContentValues(), null, null)

        return registoAlterados == 1
    }

    private fun insereJante(nomeJante: String, largura: Long, altura: Long, raio: Long, preco: Double): Boolean {
        val jante = Jante(nomeJante, largura, altura, raio, preco)

        val enderecoJanteInserida = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_JANTES, jante.toContentValues())

        return enderecoJanteInserida != null

    }


}