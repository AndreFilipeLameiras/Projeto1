package pt.ipg.projeto

import android.database.Cursor
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.loader.app.LoaderManager
import androidx.loader.content.CursorLoader
import androidx.loader.content.Loader
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import pt.ipg.projeto.databinding.FragmentListaTransmissaoBinding

class ListaTransmissoesFragment: Fragment(), LoaderManager.LoaderCallbacks<Cursor> {
    var transmissaoSelecionada : Transmissao? = null
        get() = field
        set(value) {
            if (value != field) {
                field = value
                (requireActivity() as MainActivity).mostraOpcoesAlterarEliminar(field != null)
            }
        }


    private var _binding: FragmentListaTransmissaoBinding? = null
    private var adapterTransmissoes: AdapterTransmissoes? = null

    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentListaTransmissaoBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        LoaderManager.getInstance(this).initLoader(ID_LOAD_TRANSMISSOES, null, this)

        adapterTransmissoes = AdapterTransmissoes(this)
        binding.recyclerViewTransmissoes.adapter = adapterTransmissoes
        binding.recyclerViewTransmissoes.layoutManager = LinearLayoutManager(requireContext())

        val activity = activity as MainActivity
        activity.fragment = this
        activity.idMenuAtual = R.menu.menu_lista


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null

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
        return CursorLoader(
            requireContext(),
            ContentProviderCarros.ENDERECO_TRASNMISSOES,
            TabelaBDTransmissoes.TODAS_COLUNAS,
            null,
            null,
            TabelaBDTransmissoes.CAMPO_NOME
        )
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
        adapterTransmissoes!!.cursor = data
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
        adapterTransmissoes!!.cursor = null
    }


    fun processaOpcaoMenu(item: MenuItem) : Boolean =
        when(item.itemId) {
            R.id.action_inserir -> {
                val acao = ListaTransmissoesFragmentDirections.actionListaTransmissoesFragmentToEditarTransmissaoFragment()
                findNavController().navigate(acao)
                true
            }
            R.id.action_alterar -> {
                val acao = ListaTransmissoesFragmentDirections.actionListaTransmissoesFragmentToEditarTransmissaoFragment(transmissaoSelecionada)
                findNavController().navigate(acao)
                true
            }
            R.id.action_eliminar -> {
                val acao = ListaTransmissoesFragmentDirections.actionListaTransmissoesFragmentToEliminarTransmisssaoFragment(transmissaoSelecionada!!)
                findNavController().navigate(acao)
                true
            }
            else -> false
        }

    companion object{
        const val ID_LOAD_TRANSMISSOES = 0
    }
}