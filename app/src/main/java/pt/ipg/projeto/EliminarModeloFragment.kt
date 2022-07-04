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
                eliminaModelo()
                true
            }
            R.id.action_cancelar -> {
                voltaListaModelos()
                true
            }
            else -> false
        }

    private fun eliminaModelo() {
        val enderecoModelo = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_MODELOS, "${modelo.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoModelo, null, null)

        if(registosEliminados != 1){
            Snackbar.make(
                binding,textViewModelo,
                R.string.erro_eliminar_modelo,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.modelo_eliminado_sucesso, Toast.LENGTH_LONG).show()
        voltaListaModelos()


    }


    private fun voltaListaModelos() {
        val acao = EliminarModeloFragmentDirections.actionEliminarModeloFragmentToListaModelosfragment()
        findNavController().navigate(acao)
    }




}