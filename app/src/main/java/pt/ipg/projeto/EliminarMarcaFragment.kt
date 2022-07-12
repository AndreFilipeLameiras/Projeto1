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
import pt.ipg.projeto.databinding.FragmentEliminarMarcaBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EliminarMarcaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminarMarcaFragment : Fragment() {
    private var _binding: FragmentEliminarMarcaBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var marca: Marca

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEliminarMarcaBinding.inflate(inflater, container, false)
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
        activity.idMenuAtual = R.menu.menu_eliminar

        marca = EliminarMarcaFragmentArgs.fromBundle(arguments!!).marca

        binding.textViewMarca.text = marca.nome
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_eliminar -> {
                eliminaMarca()
                true
            }
            R.id.action_cancelar -> {
                navegaListaMarca()
                true
            }
            else -> false
        }

    private fun eliminaMarca() {
        val alertDialog = AlertDialog.Builder(requireContext())

        alertDialog.apply {
            setTitle(R.string.eliminar_marca_label)
            setMessage(R.string.confirma_eliminar_marca)
            setNegativeButton(android.R.string.cancel, DialogInterface.OnClickListener { dialogInterface, i ->  })
            setPositiveButton(R.string.eliminar, DialogInterface.OnClickListener { dialogInterface, i -> confirmaEliminarMarca() })
        }

    }

    private fun confirmaEliminarMarca() {
        val enderecoMarca = Uri.withAppendedPath(ContentProviderCarros.ENDERECO_MARCAS, "${marca.id}")
        val registosEliminados = requireActivity().contentResolver.delete(enderecoMarca, null, null)

        if (registosEliminados != 1) {
            Snackbar.make(
                binding.textViewMarca,
                R.string.erro_eliminar_marca,
                Snackbar.LENGTH_INDEFINITE
            ).show()
            return
        }

        Toast.makeText(requireContext(), R.string.marca_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaMarca()
    }

    private fun navegaListaMarca() {
        val acao = EliminarMarcaFragmentDirections.actionEliminarMarcaFragmentToListaMarcasFragment()
        findNavController().navigate(acao)
    }


}