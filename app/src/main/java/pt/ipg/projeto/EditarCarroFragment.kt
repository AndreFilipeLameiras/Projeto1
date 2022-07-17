package pt.ipg.projeto

import android.database.Cursor
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.SimpleCursorAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentEditarCarroBinding


/**
 * A simple [Fragment] subclass.
 * Use the [EditarCarroFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class EditarCarroFragment : Fragment() , LoaderManager.LoaderCallbacks<Cursor>{
    private var _binding: FragmentEditarCarroBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var carro: Carro? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentEditarCarroBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MODELOS, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_MOTORIZACAO, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_COR, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_ESTOFOS, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_JANTES, null, this)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao

        if(arguments != null){
            carro = EditarCarroFragmentArgs.fromBundle(arguments!!).carro


        }

        LoaderManager.getInstance(this).initLoader(ID_LOADER_MODELOS, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_MOTORIZACAO, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_COR, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_ESTOFOS, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_JANTES, null, this)


    }

    companion object{
        const val ID_LOADER_MODELOS = 0
        const val ID_LOADER_MOTORIZACAO = 0
        const val ID_LOADER_COR = 0
        const val ID_LOADER_ESTOFOS = 0
        const val ID_LOADER_JANTES = 0
    }


    /**
     * Instantiate and return a new Loader for the given ID.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param id The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> =
        CursorLoader(
            requireContext(),
            ContentProviderCarros.ENDERECO_MODELOS,
            TabelaBDModelo.TODAS_COLUNAS,
            null,
            null,
            "${TabelaBDModelo.CAMPO_MODELO}"

        )


    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is *not* allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See [ FragmentManager.openTransaction()][androidx.fragment.app.FragmentManager.beginTransaction] for further discussion on this.
     *
     *
     * This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     *
     *  *
     *
     *The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a [android.database.Cursor]
     * and you place it in a [android.widget.CursorAdapter], use
     * the [android.widget.CursorAdapter.CursorAdapter] constructor *without* passing
     * in either [android.widget.CursorAdapter.FLAG_AUTO_REQUERY]
     * or [android.widget.CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER]
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     *  *  The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a [android.database.Cursor] from a [android.content.CursorLoader],
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * [android.widget.CursorAdapter], you should use the
     * [android.widget.CursorAdapter.swapCursor]
     * method so that the old Cursor is not closed.
     *
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data The data generated by the Loader.
     */
    override fun onLoadFinished(loader: Loader<Cursor>, data: Cursor?) {
        val adapterModelos = SimpleCursorAdapter(
            requireContext(), android.R.layout.simple_list_item_1, data, arrayOf(TabelaBDModelo.CAMPO_MODELO), intArrayOf(android.R.id.text1), 0
        )

        val adapterMotorizacao = SimpleCursorAdapter(
            requireContext(), android.R.layout.simple_list_item_1, data, arrayOf(TabelaBDMotorizacoes.CAMPO_POTENCIA), intArrayOf(android.R.id.text1), 0
        )
        val adapterCores = SimpleCursorAdapter(
            requireContext(), android.R.layout.simple_list_item_1, data, arrayOf(TabelaBDCores.CAMPO_COR), intArrayOf(android.R.id.text1), 0
        )
        val adapterEstofos = SimpleCursorAdapter(
            requireContext(), android.R.layout.simple_list_item_1, data, arrayOf(TabelaBDEstofos.ESTOFOS), intArrayOf(android.R.id.text1), 0
        )
        val adapterJante = SimpleCursorAdapter(
            requireContext(), android.R.layout.simple_list_item_1, data, arrayOf(TabelaBDJantes.CAMPO_NOME), intArrayOf(android.R.id.text1), 0
        )
        
        binding.spinnerModelos.adapter = adapterModelos
        binding.spinnerMotorizacao.adapter = adapterMotorizacao
        binding.spinnerCores.adapter = adapterCores
        binding.spinnerEstofos.adapter = adapterEstofos
        binding.spinnerJantes.adapter = adapterJante

        atualizaModeloSelecionado()
        atualizaMotorizacaoSelecionado()
        atualizaCoresSelecionado()
        atualizaEstofosSelecionado()
        atualizaJantesSelecionado()

    }

    private fun atualizaModeloSelecionado() {
        TODO("Not yet implemented")
    }

    private fun atualizaMotorizacaoSelecionado() {
        TODO("Not yet implemented")
    }

    private fun atualizaCoresSelecionado() {
        TODO("Not yet implemented")
    }

    private fun atualizaEstofosSelecionado() {
        TODO("Not yet implemented")
    }

    private fun atualizaJantesSelecionado() {
        TODO("Not yet implemented")
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     *
     * This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    override fun onLoaderReset(loader: Loader<Cursor>) {
        if (_binding == null) return
        binding.spinnerModelos.adapter = null
        binding.spinnerMotorizacao.adapter = null
        binding.spinnerCores.adapter = null
        binding.spinnerEstofos.adapter = null
        binding.spinnerJantes.adapter = null
    }

    fun processaOpcaoMenu(item: MenuItem): Boolean =
        when (item.itemId){
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                navegaListaCarros()
                true
            }
            else -> false
        }

    private fun guardar() {
        val idModelo = binding.spinnerModelos.selectedItemId
        if(idModelo == Spinner.INVALID_ROW_ID){
            binding.textViewModelos.error = getString(R.string.modelo_obrigatorio)
            binding.spinnerModelos.requestFocus()
            return
        }
        val idMotorizacao = binding.spinnerMotorizacao.selectedItemId
        if(idMotorizacao == Spinner.INVALID_ROW_ID){
            binding.textViewmotorizacao.error = getString(R.string.motorizacao_obrigatoria)
            binding.spinnerMotorizacao.requestFocus()
            return
        }
        val idCor = binding.spinnerCores.selectedItemId
        if(idCor == Spinner.INVALID_ROW_ID){
            binding.textViewCores.error = getString(R.string.nome_cor_obrigatoria)
            binding.spinnerCores.requestFocus()
            return
        }
        val idEstofo = binding.spinnerEstofos.selectedItemId
        if(idEstofo == Spinner.INVALID_ROW_ID){
            binding.textViewEstofo.error = getString(R.string.nome_estofo_obrigatorio)
            binding.spinnerEstofos.requestFocus()
            return
        }
        val idJante = binding.spinnerJantes.selectedItemId
        if(idJante == Spinner.INVALID_ROW_ID){
            binding.textViewJante.error = getString(R.string.nome_jante_obrigatorio)
            binding.spinnerJantes.requestFocus()
            return
        }
        
        insereCarro(idModelo, idMotorizacao, idCor, idEstofo, idJante)
        
    }

    private fun insereCarro(idModelo: Long, idMotorizacao: Long, idCor: Long, idEstofo: Long, idJante: Long) {

        val carro = Carro(Modelo(id = idModelo), Motorizacao(id = idMotorizacao), Cores(id = idCor), Estofos(id = idEstofo), Jante(id = idJante))

        val enderecoCarroInserido = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_CARROS, carro.toContentValues())

        if(enderecoCarroInserido == null){
            Snackbar.make(binding.textViewModelos , R.string.erro_guardar_carro, Snackbar.LENGTH_INDEFINITE).show()
            return
        }


        Toast.makeText(requireContext(), R.string.carro_guardado_sucesso, Toast.LENGTH_LONG).show()
        navegaListaCarros()

    }

    private fun navegaListaCarros() {
        findNavController().navigate(R.id.action_editarCarroFragment_to_listaCarrosFragment)
    }


}