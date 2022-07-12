package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.loader.app.LoaderManager
import androidx.navigation.fragment.findNavController
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

        return inflater.inflate(R.layout.fragment_inserir_transmissao, container, false)
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
                true
            }
            R.id.action_cancelar -> {
                findNavController().navigate(R.id.action_inserirTransmissaoFragment_to_listaTransmissoesFragment)
                true
            }
            else -> false
        }


}