package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentInserirTracaoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirTracaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirTracaoFragment : Fragment() {
    private var _binding: FragmentInserirTracaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirTracaoBinding.inflate(inflater, container, false)

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


    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                navegaListaTracao()
                true
            }
            else -> false

        }

    private fun guardar() {
        val nome = binding.editTextTracao.text.toString()
        if(nome.isBlank()){
            binding.editTextTracao.error = getString(R.string.nome_tracao_obrigatorio)
            binding.editTextTracao.requestFocus()
            return
        }

        insereTracao(nome)
    }

    private fun insereTracao(nome: String) {
        val tracao = Tracao(nome)

        val enderecoTracaoInserida = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_TRACOES, tracao.toContentValues())

        if(enderecoTracaoInserida == null) {
            Snackbar.make(
                binding.editTextTracao,
                R.string.erro_guardar_tracao,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.tracao_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaTracao()
    }

    private fun navegaListaTracao() {
        findNavController().navigate(R.id.action_inserirTracaoFragment_to_listaTracaoFragment)
    }


}