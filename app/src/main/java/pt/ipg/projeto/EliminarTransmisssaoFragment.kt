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
import pt.ipg.projeto.databinding.FragmentEliminarTransmisssaoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarTransmisssaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarTransmisssaoFragment : Fragment() {
    private var _binding: FragmentEliminarTransmisssaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var transmissao: Transmissao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarTransmisssaoBinding.inflate(inflater, container, false)
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

        transmissao = EliminarTransmisssaoFragmentArgs.fromBundle(arguments!!).transmissao

        binding.textViewNomeTransmissao.text = transmissao.nome



    }


    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.action_eliminar -> {
                eliminaTransmissao()
                true
            }
            R.id.action_cancelar ->{
                navegaListaTransmissao()
                true
            }
            else -> false
        }

    private fun eliminaTransmissao() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.eliminar_transmissao_label)
            setMessage(R.string.confirma_eliminar_transmissao)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarTransmissao() })
        }

    }
    private fun confirmaEliminarTransmissao(){
        val enderecoTransmissao = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_TRASNMISSOES, "${transmissao.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoTransmissao, null, null)

        if(registosEliminados != 1){
            Snackbar.make(
                binding.textViewNomeTransmissao,
                R.string.erro_eliminar_transmissao,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.transmissao_eliminada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaTransmissao()
    }

    private fun navegaListaTransmissao() {
        val acao = EliminarTransmisssaoFragmentDirections.actionEliminarTransmisssaoFragmentToListaTransmissoesFragment()
        findNavController().navigate(acao)
    }

}