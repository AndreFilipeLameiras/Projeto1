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
import pt.ipg.projeto.databinding.FragmentEditarCombustivelBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditarCombustivelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarCombustivelFragment : Fragment() {
    private var _binding: FragmentEditarCombustivelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var combustivel: Combustivel? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarCombustivelBinding.inflate(inflater, container, false)

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

        val combustivelGuardado =
            if (combustivel == null) {
                insereCombustivel(nomeCombustivel)
            } else {
                alteraCombustivel(nomeCombustivel)
            }

        if (combustivelGuardado) {
            Toast.makeText(requireContext(), R.string.combustivel_guardado_sucesso, Toast.LENGTH_LONG)
                .show()
            navegaListaCombustivel()
        } else {
            Snackbar.make(binding.editTextNomeCombustivel, R.string.erro_guardar_combustivel, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun alteraCombustivel(nomeCombustivel: String) : Boolean{
        val combustivel = Combustivel(nomeCombustivel)

        val enderecoCombustivel = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_COMBUSTIVEIS, "${this.combustivel!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoCombustivel, combustivel.toContentValues(), null, null)

        return registosAlterados == 1
    }


    private fun insereCombustivel(nomeCombustivel: String) : Boolean{
        val combustivel = Combustivel(nomeCombustivel)

        val enderecoCombustivelInserido = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_COMBUSTIVEIS, combustivel.toContentValues())




            return enderecoCombustivelInserido != null
        }




    private fun navegaListaCombustivel() {
        findNavController().navigate(R.id.action_editarCombustivelFragment_to_listaCombustivelFragment)
    }


}