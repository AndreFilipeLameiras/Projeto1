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
import pt.ipg.projeto.databinding.FragmentEliminarCorBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarCorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarCorFragment : Fragment() {
   private var _binding: FragmentEliminarCorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var cor: Cores

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarCorBinding.inflate(inflater, container,false)
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


        cor = EliminarCorFragmentArgs.fromBundle(arguments!!).cor

        binding.textViewNomeCor.text = cor.nome
        binding.textViewPrecoCor.text = cor.preco.toString()

    }

    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaCor()
                true
            }
            R.id.action_cancelar -> {
                navegaListaCor()
                true
            }
            else -> false
        }

    private fun eliminaCor() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.eliminar_cor_label)
            setMessage(R.string.confirma_eliminar_cor)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarCor() })
                .show()
        }
    }

    private fun confirmaEliminarCor() {
        val enderecoCor = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_CORES, "${cor.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoCor, null, null)


        if(registosEliminados != 1){
            Snackbar.make(
                binding.textViewNomeCor,
                R.string.erro_eliminar_cor,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.cor_eliminada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaCor()

    }

    private fun navegaListaCor() {
        val acao = EliminarCorFragmentDirections.actionEliminarCorFragmentToListaCoresFragment()
        findNavController().navigate(acao)
    }


}