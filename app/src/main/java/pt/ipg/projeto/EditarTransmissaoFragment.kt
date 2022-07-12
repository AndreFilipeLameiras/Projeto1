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
import pt.ipg.projeto.databinding.FragmentEditarTransmissaoBinding



/**
 * A simple [Fragment] subclass.
 * Use the [EditarTransmissaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarTransmissaoFragment : Fragment() {
    private var _binding: FragmentEditarTransmissaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var transmissao: Transmissao? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarTransmissaoBinding.inflate(inflater, container, false)

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
    }


    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                navegalistaTransmissao()
                true
            }
            else -> false
        }

    private fun guardar() {
        val nomeTransmissao = binding.editTextNomeTransmissao.text.toString()
        if(nomeTransmissao.isBlank()){
            binding.editTextNomeTransmissao.error = getString(R.string.nome_transmissao_obrigatorio)
            binding.editTextNomeTransmissao.requestFocus()
            return
        }

        val transmissaoGuardado =
            if (transmissao == null) {
                insereTransmissao(nomeTransmissao)
            } else {
                alteraTransmissao(nomeTransmissao)
            }

        if (transmissaoGuardado) {
            Toast.makeText(requireContext(), R.string.transmissao_guardada_sucesso, Toast.LENGTH_LONG)
                .show()
            navegalistaTransmissao()
        } else {
            Snackbar.make(binding.editTextNomeTransmissao, R.string.erro_guardar_transmissao, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun alteraTransmissao(nomeTransmissao: String) : Boolean{
        val transmissao = Transmissao(nomeTransmissao)

        val enderecoTransmissao = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_TRASNMISSOES, "${this.transmissao!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoTransmissao, transmissao.toContentValues(), null, null)

        return registosAlterados == 1
    }


    private fun insereTransmissao(nomeTransmissao: String) : Boolean{
        val transmissao = Transmissao(nomeTransmissao)

        val enderecoTransmissaoInserido = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_TRASNMISSOES, transmissao.toContentValues())




        return enderecoTransmissaoInserido != null
    }




    private fun navegalistaTransmissao() {
        findNavController().navigate(R.id.action_editarTransmissaoFragment_to_listaTransmissoesFragment)
    }


}