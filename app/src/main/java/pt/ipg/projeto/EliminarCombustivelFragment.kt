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
import pt.ipg.projeto.databinding.FragmentEliminarCombustivelBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarCombustivelFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarCombustivelFragment : Fragment() {
    private var _binding: FragmentEliminarCombustivelBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var combustivel: Combustivel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarCombustivelBinding.inflate(inflater, container, false)
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

        combustivel = EliminarCombustivelFragmentArgs.fromBundle(arguments!!).combustivel

        binding.textViewNomeCombustivel.text = combustivel.nome


    }

    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.action_eliminar -> {
                eliminaCombustivel()
                true
            }
            R.id.action_cancelar -> {
                navegaListaCombustivel()
                true
            }
        }



    private fun eliminaCombustivel() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.eliminar_combustivel_label)
            setMessage(R.string.confirma_eliminar_combustivel)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarCombustivel() })
        }
    }

    private fun confirmaEliminarCombustivel(){
        val enderecoCombustivel = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_COMBUSTIVEIS, "${combustivel.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoCombustivel, null,null)

        if(registosEliminados != 1){
            Snackbar.make(
                binding.textViewNomeCombustivel,
                R.string.erro_eliminar_combustivel,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.combustivel_eliminado_sucesso, Toast.LENGTH_LONG).show()
        navegaListaCombustivel()
    }


    private fun navegaListaCombustivel() {
        val acao = EliminarCombustivelFragmentDirections.actionEliminarCombustivelFragmentToListaCombustivelFragment()
        findNavController().navigate(acao)
    }


}


