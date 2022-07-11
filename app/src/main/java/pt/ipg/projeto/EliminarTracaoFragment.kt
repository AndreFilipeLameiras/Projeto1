package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import pt.ipg.projeto.databinding.FragmentEliminarTracaoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarTracaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarTracaoFragment : Fragment() {
    private var _binding: FragmentEliminarTracaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var tracao: Tracao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarTracaoBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_eliminar

        tracao = EliminarTracaoFragmentArgs.fromBundle(arguments!!).tracao


        binding.textViewNomeTracao.text = tracao.nome
    }


    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.action_eliminar -> {

                true
            }
            R.id.action_cancelar -> {

                true
            }
            else -> false
        }


}