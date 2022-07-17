package pt.ipg.projeto

import android.content.DialogInterface
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentEliminarCarroBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarCarroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarCarroFragment : Fragment() {
   private var _binding: FragmentEliminarCarroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var carro: Carro

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarCarroBinding.inflate(inflater, container, false)
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

        carro = EliminarCarroFragmentArgs.fromBundle(arguments!!).carro


        binding.textViewModel.text = carro.modelo.nomeModelo
        binding.textViewMotorizacoes.text = carro.motorizacao.potencia.toString()
        binding.textViewCore.text = carro.cores.nome
        binding.textViewEstof.text = carro.estofos.nome
        binding.textViewJant.text = carro.jante.nome

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaCarro()
                true
            }
            R.id.action_cancelar -> {
                navegaListaCarro()
                true
            }
            else -> false
        }

    private fun eliminaCarro() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.eliminar_carro_label)
            setMessage(R.string.confirma_eliminar_carro)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarCarro() })
            show()
        }

    }

    private fun confirmaEliminarCarro(){
        val enderecoCarro = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_CARROS, "${carro.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoCarro, null, null)

        if(registosEliminados != 1){
            Snackbar.make(
                binding.textViewModel,
                R.string.erro_eliminar_carro,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.carro_guardado_sucesso, Toast.LENGTH_LONG).show()
        navegaListaCarro()



    }

    private fun navegaListaCarro() {
        val acao = EliminarCarroFragmentDirections.actionEliminarCarroFragmentToListaCarrosFragment()
        findNavController().navigate(acao)
    }


}