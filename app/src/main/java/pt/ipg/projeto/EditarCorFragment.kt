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
import pt.ipg.projeto.databinding.FragmentEditarCorBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditarCorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarCorFragment : Fragment() {
    private var _binding : FragmentEditarCorBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var cor: Cores? = null



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarCorBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_edicao

        if(arguments != null){
            cor = EditarCorFragmentArgs.fromBundle(arguments!!).cor
            if(cor != null){
                binding.editTextNomeCor.setText(cor!!.nome)
                binding.editTextPrecoCor.setText(cor!!.preco.toString())
            }
        }

    }



    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when (item.itemId){
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                navegaListaCores()
                true
            }
            else -> false
        }

    private fun guardar() {
        val nome = binding.editTextNomeCor.text.toString()
        if (nome.isBlank()) {
            binding.editTextNomeCor.error = getString(R.string.nome_cor_obrigatoria)
            binding.editTextPrecoCor.requestFocus()
            return
        }

        val preco = binding.editTextPrecoCor.text.toString().toDouble()
        if (preco.toString().isEmpty()) {
            binding.editTextPrecoCor.setError(getString(R.string.preco_obrigatorio))
            binding.editTextPrecoCor.requestFocus()
            return
        }

        val corGuardada =
            if(cor == null){
                insereCor(nome, preco)
            }else{
                alteraCor(nome, preco)
            }

        if(corGuardada){
            Toast.makeText(requireContext(), R.string.cor_guardada_sucesso, Toast.LENGTH_LONG).show()
            navegaListaCores()
        }else{
            Snackbar.make(binding.editTextNomeCor, R.string.erro_guardar_cor, Snackbar.LENGTH_INDEFINITE).show()
            return
        }

    }

    private fun alteraCor(nome: String, preco: Double): Boolean{
        val cor = Cores(nome, preco)

        val enderecoCor = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_CORES, "${this.cor!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoCor, cor.toContentValues(), null,null)

        return registosAlterados == 1

    }

    private fun insereCor(nome: String, preco: Double): Boolean {
        val cor = Cores(nome, preco)

        val enderecoCorInserida = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_CORES, cor.toContentValues())

        return enderecoCorInserida != null
    }

    private fun navegaListaCores() {
        findNavController().navigate(R.id.action_editarCorFragment_to_listaCoresFragment)
    }


}