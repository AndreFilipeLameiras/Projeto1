package pt.ipg.projeto

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [EliminaJanteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EliminaJanteFragment : Fragment() {
    private lateinit var textViewNomeJante: TextView
    private lateinit var textViewLargura: TextView
    private lateinit var textViewAltura: TextView
    private lateinit var textViewRaio: TextView
    private lateinit var textViewPreco: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        return inflater.inflate(R.layout.fragment_elimina_jante, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textViewNomeJante = view.findViewById(R.id.textViewNomeMarca)
        textViewLargura = view.findViewById(R.id.textViewLargura)
        textViewAltura = view.findViewById(R.id.textViewAltura)
        textViewRaio = view.findViewById(R.id.textViewRaio)
        textViewPreco = view.findViewById(R.id.textViewPreco)

        val jante = (activity as MainActivity).janteSeleccionada!!
        textViewNomeJante.setText(jante.nome)
        textViewLargura.setText(jante.largura.toString())
        textViewAltura.setText(jante.altura.toString())
        textViewRaio.setText(jante.raio.toString())
        textViewPreco.setText(jante.preco.toString())


    }

    fun navegaListaJante() {
        findNavController().navigate(R.id.action_eliminaJanteFragment_to_listaJantesFragment)
    }

    fun elimina() {
        val uriJante = Uri.withAppendedPath(
            ContentProviderCarros.ENDERECO_JANTES,
            (activity as MainActivity).janteSeleccionada!!.id.toString()
        )

        val registos = activity?.contentResolver?.delete(
            uriJante,
            null,null
        )

        if(registos != 1){
            Toast.makeText(
                requireContext(),
                getString(R.string.erro_eliminar_jante),
                Toast.LENGTH_LONG
            ).show()
            return
        }

        Toast.makeText(
            requireContext(),
            getString(R.string.jante_eliminada_sucesso),
            Toast.LENGTH_LONG
        ).show()
        navegaListaJante()
    }



    fun processaOpcaoMenu(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.action_eliminar -> elimina()
            R.id.action_cancelar -> navegaListaJante()
            else -> return false
        }
        return true
    }




}