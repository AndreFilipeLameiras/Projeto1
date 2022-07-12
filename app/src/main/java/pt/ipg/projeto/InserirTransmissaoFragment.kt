package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentInserirTransmissaoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirTransmissaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirTransmissaoFragment : Fragment() {
    private var _binding: FragmentInserirTransmissaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirTransmissaoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
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
            binding.editTextNomeTransmissao.error = getString(R.string.nome_combustivel_obrigatorio)
            binding.editTextNomeTransmissao.requestFocus()
            return
        }

        insereTransmissao(nomeTransmissao)
    }

    private fun insereTransmissao(nomeTransmissao: String) {
        val transmissao = Transmissao(nomeTransmissao)

        val enderecoTransmissaoInserida = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_TRASNMISSOES, transmissao.toContentValues())

        if(enderecoTransmissaoInserida == null){
            Snackbar.make(binding.editTextNomeTransmissao, R.string.erro_guardar_transmissao, Snackbar.LENGTH_INDEFINITE).show()
            return
        }

        Toast.makeText(requireContext(), R.string.transmissao_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegalistaTransmissao()

    }


    private fun navegalistaTransmissao() {
        findNavController().navigate(R.id.action_inserirTransmissaoFragment_to_listaTransmissoesFragment)
    }


}