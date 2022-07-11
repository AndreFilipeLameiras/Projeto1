package pt.ipg.projeto

import android.app.AlertDialog
import android.content.DialogInterface
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
import pt.ipg.projeto.databinding.FragmentEliminarEstofoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarEstofoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarEstofoFragment : Fragment() {
    private var _binding: FragmentEliminarEstofoBinding? = null

    //This property is only valid between onCreateView and
    //onDestroyView.
    private val binding get() = _binding!!

    private lateinit var estofos: Estofos

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarEstofoBinding.inflate(inflater, container, false)
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

        estofos = EliminarEstofoFragmentArgs.fromBundle(arguments!!).estofo

        binding.textViewNomeEstofos.text = estofos.nome
        binding.textViewPrecoEstofo.text = estofos.preco.toString()

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean=
        when(item.itemId){
            R.id.action_eliminar -> {
                eliminaEstofo()
                true
            }
            R.id.action_cancelar -> {
                navegaListaEstofo()
                true
            }
            else -> false
        }

    private fun eliminaEstofo() {
        var alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.eliminar_estofo_label)
            setMessage(R.string.confirma_eliminar_estof)
            setNegativeButton(
                android.R.string.cancel,
                DialogInterface.OnClickListener { dialogInterface, i -> })
            setPositiveButton(
                R.string.eliminar,
                DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarEstofo() })
            show()
        }
    }

        private fun confirmaEliminarEstofo(){
        val enderecoEstofo = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_ESTOFOS, "${estofos.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoEstofo, null, null)

        if(registosEliminados != 1){
            Snackbar.make(
                binding.textViewNomeEstofos,
                R.string.erro_eliminar_estofo,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.estofo_eliminado_sucesso, Toast.LENGTH_LONG).show()
        navegaListaEstofo()


    }

    private fun navegaListaEstofo() {
        val acao = EliminarEstofoFragmentDirections.actionEliminarEstofoFragmentToListaEstofosFragment()
        findNavController().navigate(acao)
    }

}
