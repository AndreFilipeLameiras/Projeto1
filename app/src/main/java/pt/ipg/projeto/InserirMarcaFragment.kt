package pt.ipg.projeto

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import pt.ipg.projeto.databinding.FragmentInserirMarcaBinding

/**
 * A simple [Fragment] subclass.
 * Use the [InserirMarcaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */

class InserirMarcaFragment : Fragment() {

    private var _binding: FragmentInserirMarcaBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirMarcaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                true
            }
            R.id.action_cancelar -> {
                findNavController().navigate(R.id.action_inserirMarcaFragment_to_listaMarcasFragment)
                true
            }
            else -> false
        }

}