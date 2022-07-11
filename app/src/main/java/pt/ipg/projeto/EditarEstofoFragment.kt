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
import pt.ipg.projeto.databinding.FragmentEditarEstofoBinding

/**
 * A simple [Fragment] subclass.
 * Use the [EditarEstofoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarEstofoFragment : Fragment() {
    private var _binding: FragmentEditarEstofoBinding?= null

    //This property is only valid between onCreateView and
    //onDestroyView
    private val binding get() = _binding!!

    private var estofo: Estofos? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarEstofoBinding.inflate(inflater, container, false)

        // Inflate the layout for this fragment
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
            estofo = EditarEstofoFragmentArgs.fromBundle(arguments!!).estofo

            if( estofo != null){
                binding.editTextNomeEstofo.setText(estofo!!.nome)
                binding.editTextPrecoEstofo.setText(estofo!!.preco.toString())
            }
        }

    }

    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when(item.itemId){
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                navegaListaEstofo()
                true
            }
            else -> false
        }

    private fun guardar() {
        val nome = binding.editTextNomeEstofo.text.toString()
        if(nome.isBlank()){
            binding.editTextNomeEstofo.error = getString(R.string.nome_estofo_obrigatorio)
            binding.editTextNomeEstofo.requestFocus()
            return
        }

        val preco = binding.editTextPrecoEstofo.text.toString().toDouble()
        if(preco.toString().isBlank()){
            binding.editTextPrecoEstofo.error = getString(R.string.preco_obrigatorio)
            binding.editTextPrecoEstofo.requestFocus()
            return
        }

        val estofoGuardado =
            if(estofo == null){
                insereEstofo(nome, preco)
            }else{
                alteraEstofo(nome, preco)
            }


        if(estofoGuardado){
            Toast.makeText(requireContext(), R.string.estofo_guardado_sucesso, Toast.LENGTH_LONG).show()
            navegaListaEstofo()
        }else {
            Snackbar.make(binding.editTextNomeEstofo, R.string.erro_guardar_estofo, Snackbar.LENGTH_INDEFINITE).show()
            return
        }
    }

    private fun alteraEstofo(nome: String, preco: Double): Boolean {
        val estofo = Estofos(nome, preco)

        val enderecoEstofo = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_ESTOFOS, "${this.estofo!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoEstofo, estofo.toContentValues(),null, null)

        return registosAlterados == 1
    }


    private fun insereEstofo(nome: String, preco: Double) : Boolean{
        val estofo = Estofos(nome, preco)

        val enderecoEstofoInserido = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_ESTOFOS, estofo.toContentValues())


        return enderecoEstofoInserido != null




    }

    private fun navegaListaEstofo() {
        findNavController().navigate(R.id.action_inserirEstofoFragment_to_listaEstofosFragment)
    }


}