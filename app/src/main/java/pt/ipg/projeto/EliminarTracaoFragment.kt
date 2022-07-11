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
                eliminaTracao()
                true
            }
            R.id.action_cancelar -> {
                navegaListaTracao()
                true
            }
            else -> false
        }

    private fun eliminaTracao() {
        val enderecoTracao = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_TRACOES, "${tracao.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoTracao, null, null)

        if(registosEliminados != 1){
            Snackbar.make(
                binding.textViewNomeTracao,
                R.string.erro_eliminar_tracao,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(),R.string.tracao_eliminada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaTracao()
    }

    private fun navegaListaTracao() {
        val acao = EliminarTracaoFragmentDirections.actionEliminarTracaoFragmentToListaTracaoFragment()
        findNavController().navigate(acao)
    }


}