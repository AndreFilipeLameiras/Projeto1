package pt.ipg.projeto

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
import pt.ipg.projeto.databinding.FragmentInserirMarcaBinding



class InserirMarcaFragment : Fragment() {

    private var _binding: FragmentInserirMarcaBinding? = null
    private val binding get() = _binding!!

    private lateinit var editTextNomeMarca: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentInserirMarcaBinding.inflate(inflater, container, false)

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        editTextNomeMarca = view.findViewById(R.id.editTextNome)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }





    fun guardar() {
        val nomeMarca = editTextNomeMarca.text.toString()
        if (nomeMarca.isBlank()) {
            editTextNomeMarca.error = getString(R.string.nome_marca_obrigatorio)
            editTextNomeMarca.requestFocus()
            return

        }

        val marca = Marca(nome = nomeMarca)

        val enderecoMarcaInserida = activity?.contentResolver?.insert(ContentProviderCarros.ENDERECO_MARCAS, marca.toContentValues())

        /*if(enderecoMarcaInserida == null) {
            Snackbar.make(
                editTextNomeMarca,
                R.string.erro_guardar_marca,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }*/

        Toast.makeText(requireContext(), R.string.marca_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaMarcas()

    }
/*
    fun insereMarca(nomeMarca: String) {
        val marca = Marca(nomeMarca)

        val enderecoMarcaInserida = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_MARCAS, marca.toContentValues())

        if(enderecoMarcaInserida == null) {
            Snackbar.make(
                editTextNomeMarca,
                R.string.erro_guardar_marca,
                Snackbar.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.marca_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaMarcas()
    }
*/
    private fun navegaListaMarcas() {
        findNavController().navigate(R.id.action_inserirMarcaFragment_to_listaMarcasFragment)
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_guardar ->guardar()
            R.id.action_cancelar ->navegaListaMarcas()
            else -> return false
        }

        return true
    }
}