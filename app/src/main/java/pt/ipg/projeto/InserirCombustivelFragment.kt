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
import pt.ipg.projeto.databinding.FragmentInserirCombustivelBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirCombustivelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirCombustivelFragment : Fragment() {
    private var _binding: FragmentInserirCombustivelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirCombustivelBinding.inflate(inflater, container, false)

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
                navegaListaCombustivel()
                true
            }
            else -> false
        }

    private fun guardar() {
        val nomeCombustivel = binding.editTextNomeCombustivel.text.toString()
        if(nomeCombustivel.isBlank()){
            binding.editTextNomeCombustivel.error = getString(R.string.nome_combustivel_obrigatorio)
            binding.editTextNomeCombustivel.requestFocus()
            return
        }

        insereCombustivel(nomeCombustivel)
    }

    private fun insereCombustivel(nomeCombustivel: String) {
        val combustivel = Combustivel(nomeCombustivel)

        val enderecoCombustivelInserido = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_COMBUSTIVEIS, combustivel.toContentValues())

        if (enderecoCombustivelInserido == null){
            Snackbar.make(binding.editTextNomeCombustivel, R.string.erro_guardar_combustivel, Snackbar.LENGTH_INDEFINITE).show()
            return
        }

        Toast.makeText(requireContext(), R.string.combustivel_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaCombustivel()
    }

    private fun navegaListaCombustivel() {
        findNavController().navigate(R.id.action_inserirCombustivelFragment_to_listaCombustivelFragment)
    }


}