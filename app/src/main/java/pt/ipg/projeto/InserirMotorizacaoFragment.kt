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
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import pt.ipg.projeto.databinding.FragmentInserirMotorizacaoBinding


/**
 * A simple [Fragment] subclass.
 * Use the [InserirMotorizacaoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class InserirMotorizacaoFragment : Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    private var _binding: FragmentInserirMotorizacaoBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentInserirMotorizacaoBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        LoaderManager.getInstance(this).initLoader(ID_LOADER_TRANSMISSAO, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_TRACAO, null, this)
        LoaderManager.getInstance(this).initLoader(ID_LOADER_COMBUSTIVEL, null, this)

        val activity = requireActivity() as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_edicao
    }

    companion object{
        const val ID_LOADER_TRANSMISSAO = 0
        const val ID_LOADER_TRACAO = 0
        const val ID_LOADER_COMBUSTIVEL = 0
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
    override fun onCreateLoader(id: Int, args: Bundle?): Loader<Cursor> {
        TODO("Not yet implemented")
    }

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
        val adapterTransmissoes = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDTransmissoes.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )
        binding.spinnerTransmissao.adapter = adapterTransmissoes

        val adapterTracoes = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDTracao.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )
        binding.spinnerTacao.adapter = adapterTracoes


        val adapterCombustiveis = SimpleCursorAdapter(
            requireContext(),
            android.R.layout.simple_list_item_1,
            data,
            arrayOf(TabelaBDCombustivel.CAMPO_NOME),
            intArrayOf(android.R.id.text1),
            0
        )
        binding.spinnerCombustivel.adapter = adapterCombustiveis
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
        binding.spinnerTransmissao.adapter = null
        binding.spinnerTacao.adapter = null
        binding.spinnerCombustivel.adapter = null
    }

    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_guardar -> {
                guardar()
                true
            }
            R.id.action_cancelar -> {
                navegaListaMotorizacoes()
                true
            }
            else -> false
        }

    private fun guardar() {
        val potencia = binding.editTextPotencia.text.toString()
        if (potencia.isBlank()) {
            binding.editTextPotencia.error = getString(R.string.potencia_obrigatoria)
            binding.editTextPotencia.requestFocus()
            return
        }

        val consumo = binding.editTextConsumo.text.toString()
        if (consumo.isBlank()) {
            binding.editTextConsumo.error = getString(R.string.consumo_obrigatorio)
            binding.editTextConsumo.requestFocus()
            return
        }

        val emissoes = binding.editTextEmissoes.text.toString()
        if (emissoes.isBlank()) {
            binding.editTextEmissoes.error = getString(R.string.emissoes_obrigatorias)
            binding.editTextEmissoes.requestFocus()
            return
        }

        val idTransmissao = binding.spinnerTransmissao.selectedItemId
        if (idTransmissao == Spinner.INVALID_ROW_ID) {
            binding.textViewTransmiss.error = getString(R.string.nome_transmissao_obrigatorio)
            binding.spinnerTransmissao.requestFocus()
            return
        }

        val idTracao = binding.spinnerTacao.selectedItemId
        if (idTracao == Spinner.INVALID_ROW_ID) {
            binding.textViewTrac.error = getString(R.string.nome_tracao_obrigatorio)
            binding.spinnerTacao.requestFocus()
            return
        }

        val idCombustivel = binding.spinnerCombustivel.selectedItemId
        if (idCombustivel == Spinner.INVALID_ROW_ID) {
            binding.textViewCombustiv.error = getString(R.string.nome_combustivel_obrigatorio)
            binding.spinnerCombustivel.requestFocus()
            return
        }

        insereMotorizacao(potencia, consumo, emissoes, idTransmissao, idTracao, idCombustivel)

    }

    private fun insereMotorizacao(potencia: String, consumo: String, emissoes: String, idTransmissao: Long, idTracao: Long, idCombustivel: Long) {
        val motorizacao = Motorizacao(potencia.toString().toLong(), consumo.toString().toDouble(), emissoes.toString().toDouble(), Transmissao(nome = "" ,id = idTransmissao), Tracao(nome = "" ,id = idTracao), Combustivel(nome = "" ,id = idCombustivel) )

        val enderecoMotorizacaoInserida = requireActivity().contentResolver.insert(ContentProviderCarros.ENDERECO_MOTORIZACOES, motorizacao.toContentValues())


        if(enderecoMotorizacaoInserida == null){
            Snackbar.make(binding.editTextPotencia, R.string.erro_guardar_motorizacao, Snackbar.LENGTH_INDEFINITE).show()
            return

        }

        Toast.makeText(requireContext(), R.string.motorizacao_guardada_sucesso, Toast.LENGTH_LONG).show()
        navegaListaMotorizacoes()

    }

    private fun navegaListaMotorizacoes() {
        findNavController().navigate(R.id.action_inserirMotorizacaoFragment_to_listaMotorizacoesFragment)
    }

}