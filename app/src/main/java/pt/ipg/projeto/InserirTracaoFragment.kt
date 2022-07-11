package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
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

        return inflater.inflate(R.layout.fragment_inserir_tracao, container, false)
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
                true
            }
            R.id.action_cancelar -> {
                findNavController().navigate(R.id.action_inserirTracaoFragment_to_listaTracaoFragment)

                true
            }
            else -> false

        }


}