package pt.ipg.projeto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import pt.ipg.projeto.databinding.FragmentEliminarModeloBinding


class EliminarModeloFragment : Fragment() {
    private var _binding: FragmentEliminarModeloBinding? = null

    //This property is only valid between onCreateView and
    //onDestroyView
    private val binding get() = _binding!!

    private lateinit var modelo: Modelo

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEliminarModeloBinding.inflate(inflater, container, false)
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

        modelo = EliminarModeloFragmentArgs.fromBundle(arguments!!).modelo

        binding.textViewModelo.text = modelo.modelo
        binding.textViewNomeMarcas.text = modelo.marca.nome
        binding.textViewPrecoModel.text = modelo.preco.toString()
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
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