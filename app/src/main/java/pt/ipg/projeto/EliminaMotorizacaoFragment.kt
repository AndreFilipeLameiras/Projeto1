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
import pt.ipg.projeto.databinding.FragmentEliminaMotorizacaoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminaMotorizacaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaMotorizacaoFragment : Fragment() {
    private var _binding: FragmentEliminaMotorizacaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!


    private lateinit var motorizacao: Motorizacao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminaMotorizacaoBinding.inflate(inflater, container, false)
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


        motorizacao = EliminaMotorizacaoFragmentArgs.fromBundle(arguments!!).motorizacao

        binding.textViewPotencia.text = motorizacao.potencia.toString()
        binding.textViewConsumo.text = motorizacao.consumo.toString()
        binding.textViewEmissao.text = motorizacao.emissoes.toString()
        binding.textViewNomeTransmiss.text = motorizacao.transmissao.nome
        binding.textViewNomeTrac.text = motorizacao.tracao.nome
        binding.textViewNomeCombustive.text = motorizacao.combustivel.nome



    }


    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaMotorizacao()
                true
            }
            R.id.action_cancelar -> {
                navegalistaMotorizacao()
                true
            }
            else -> false
        }

    private fun eliminaMotorizacao() {
        val enderecoMotorizacao = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_MOTORIZACOES, "${motorizacao.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoMotorizacao, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewPotencia,
                R.string.erro_eliminar_motorizacao,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.motorizacao_eliminada_sucesso, Toast.LENGTH_LONG).show()
        navegalistaMotorizacao()



    }

    private fun navegalistaMotorizacao() {
        val acao = EliminaMotorizacaoFragmentDirections.actionEliminaMotorizacaoFragmentToListaMotorizacoesFragment()
        findNavController().navigate(acao)
    }


}