package pt.ipg.projeto

import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController


/**
 * A simple [Fragment] subclass.
 * Use the [EditaJanteFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditaJanteFragment : Fragment() {
    var janteSeleccionada : Jante? = null
        get() = field
        set(value) {
            field = value
            (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
        }


    private lateinit var editTextNomeJante: EditText
    private lateinit var editTextLargura: EditText
    private lateinit var editTextAltura: EditText
    private lateinit var editTextRaio: EditText
    private lateinit var editTextPreco: EditText





    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as MainActivity).fragment = this
        (activity as MainActivity).idMenuAtual = R.menu.menu_edicao


        return inflater.inflate(R.layout.fragment_edita_jante, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        editTextNomeJante = view.findViewById(R.id.editTextNomeJante)
        editTextLargura = view.findViewById(R.id.editTextLargura)
        editTextAltura = view.findViewById(R.id.editTextAltura)
        editTextRaio = view.findViewById(R.id.editeTextRaio)
        editTextPreco = view.findViewById(R.id.editTextPreco)

        /*val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao
*/
        //editTextNomeJante.setText((activity as MainActivity).janteSeleccionada!!.nome)
    }



        fun navegaListaJante() {
            findNavController().navigate(R.id.action_editaJanteFragment_to_listaJantesFragment)
        }

        fun guardar() {
            val nome = editTextNomeJante.text.toString()
            if (nome.isEmpty()){
                editTextNomeJante.setText(getString(R.string.nome_jante_obrigatorio))
                editTextNomeJante.requestFocus()
                return
            }

            val largura = editTextLargura.text.toString().toLong()
            if (largura.toString().isBlank()){
                editTextLargura.setText(getString(R.string.Introduza_largura))
                editTextLargura.requestFocus()
                return
            }

            val altura = editTextAltura.text.toString().toLong()
            if (altura.toString().isBlank()){
                editTextAltura.setText(getString(R.string.Introduza_altura))
                editTextAltura.requestFocus()
                return
            }
            val raio = editTextRaio.text.toString().toLong()
            if (raio.toString().isBlank()){
                editTextRaio.setText(getString(R.string.Introduza_raio))
                editTextRaio.requestFocus()
                return
            }

            val preco = editTextPreco.text.toString().toDouble()
            if (preco.toString().isBlank()){
                editTextPreco.setText(getString(R.string.Introduza_preco))
                editTextPreco.requestFocus()
                return
            }


            val jante = (activity as EditaJanteFragment).janteSeleccionada!!
            jante.nome = nome


            val uriJante = Uri.withAppendedPath(
                ContentProviderCarros.ENDERECO_JANTES,
                jante.id.toString()
            )

            val registos = activity?.contentResolver?.update(
                uriJante,
                jante.toContentValues(),
                null,
                null
            )

            if(registos != 1){
                Toast.makeText(
                    requireContext(),
                    getString(R.string.erro_alterar_jante),
                    Toast.LENGTH_LONG
                ).show()
                return
            }
            Toast.makeText(
                requireContext(),
                getString(R.string.jante_guardada_sucesso),
                Toast.LENGTH_LONG
            ).show()
        navegaListaJante()

        }



        fun processaOpcaoMenu(item: MenuItem): Boolean{
            when(item.itemId){
                R.id.action_guardar -> guardar()
                R.id.action_cancelar -> navegaListaJante()
                else -> return false
            }

            return true
        }



    }




