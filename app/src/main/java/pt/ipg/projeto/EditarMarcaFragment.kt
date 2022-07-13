package pt.ipg.projeto

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentEditarMarcaBinding



class EditarMarcaFragment : Fragment() {

    private var _binding: FragmentEditarMarcaBinding? = null
    private val binding get() = _binding!!

    private var marca: Marca? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditarMarcaBinding.inflate(inflater, container, false)

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
            marca = EditarMarcaFragmentArgs.fromBundle(arguments!!).marca

            if(marca != null){
                binding.editTextNomeMarca.setText(marca!!.nome)
            }
        }

    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                navegaListaMarcas()
                true
            }
            else -> false
        }





    private fun guardar() {
        val nome = binding.editTextNomeMarca.text.toString()
        if (nome.isBlank()) {
            binding.editTextNomeMarca.error = getString(R.string.nome_marca_obrigatorio)
            binding.editTextNomeMarca.requestFocus()
            return

        }

        val marcaGuardada =
            if(marca == null){
                insereMarca(nome)
            }else{
                alteraMarca(nome)
            }

        if(marcaGuardada){
            Toast.makeText(requireContext(), R.string.marca_guardada_sucesso, Toast.LENGTH_LONG)
                .show()
            navegaListaMarcas()
        }else{
            Snackbar.make(binding.editTextNomeMarca, R.string.erro_guardar_marca, Snackbar.LENGTH_INDEFINITE).show()
            return
        }

    }

    private fun alteraMarca(nomeMarca: String): Boolean {
        val marca = Marca(nomeMarca)

        val enderecoMarca = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_MARCAS, "${this.marca!!.id}")

        val registosAlterados = requireActivity().contentResolver.update(enderecoMarca, marca.toContentValues(), null, null)

        return registosAlterados == 1


    }



    private fun insereMarca(nomeMarca: String): Boolean {
        val marca = Marca(nomeMarca)

        val enderecoMarcaInserida = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_MARCAS, marca.toContentValues())

        return enderecoMarcaInserida != null
    }


    private fun navegaListaMarcas() {
        findNavController().navigate(R.id.action_editarMarcaFragment_to_listaMarcasFragment)
    }


}