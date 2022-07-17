package pt.ipg.projeto

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterMotorizacoes(val fragment: ListaMotorizacoesFragment) :RecyclerView.Adapter<AdapterMotorizacoes.ViewHolderMotorizacoes>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderMotorizacoes? = null

    inner class ViewHolderMotorizacoes (itemMotorizacao: View) : RecyclerView.ViewHolder(itemMotorizacao), View.OnClickListener{
        val textViewPotencia = itemMotorizacao.findViewById<TextView>(R.id.textViewPotencia)
        val textViewConsumo = itemMotorizacao.findViewById<TextView>(R.id.textViewConsumo)
        val textViewEmissao = itemMotorizacao.findViewById<TextView>(R.id.textViewEmissoes)
        val textViewTransmissao = itemMotorizacao.findViewById<TextView>(R.id.textViewTransmissao)
        val textViewTracao = itemMotorizacao.findViewById<TextView>(R.id.textViewTracao)
        val textViewCombustivel = itemMotorizacao.findViewById<TextView>(R.id.textViewCombustivel)

        init {
            itemMotorizacao.setOnClickListener(this)
        }


        var motorizacao: Motorizacao? = null
            get() = field
            set(value: Motorizacao?) {
                field = value

                textViewPotencia.text = motorizacao?.potencia.toString()
                textViewConsumo.text = motorizacao?.consumo.toString()
                textViewEmissao.text = motorizacao?.emissoes.toString()
                textViewTransmissao.text = motorizacao?.transmissao?.nome?: ""
                textViewTracao.text = motorizacao?.tracao?.nome?: ""
                textViewCombustivel.text = motorizacao?.combustivel?.nome?: ""
            }

        override fun onClick(p0: View?) {
            viewHolderSelecionado?.desSeleciona()
            seleciona()
        }

        private fun seleciona() {
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
        }

        private fun desSeleciona(){
            itemView.setBackgroundResource(android.R.color.white)
        }

    }

    /**
     * Called when RecyclerView needs a new [ViewHolder] of the given type to represent
     * an item.
     *
     *
     * This new ViewHolder should be constructed with a new View that can represent the items
     * of the given type. You can either create a new View manually or inflate it from an XML
     * layout file.
     *
     *
     * The new ViewHolder will be used to display items of the adapter using
     * [.onBindViewHolder]. Since it will be re-used to display
     * different items in the data set, it is a good idea to cache references to sub views of
     * the View to avoid unnecessary [View.findViewById] calls.
     *
     * @param parent The ViewGroup into which the new View will be added after it is bound to
     * an adapter position.
     * @param viewType The view type of the new View.
     *
     * @return A new ViewHolder that holds a View of the given view type.
     * @see .getItemViewType
     * @see .onBindViewHolder
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderMotorizacoes {
        val itemMotorizacao = fragment.layoutInflater.inflate(R.layout.item_motorizacao, parent, false)
        return ViewHolderMotorizacoes(itemMotorizacao)
    }

    /**
     * Called by RecyclerView to display the data at the specified position. This method should
     * update the contents of the [ViewHolder.itemView] to reflect the item at the given
     * position.
     *
     *
     * Note that unlike [android.widget.ListView], RecyclerView will not call this method
     * again if the position of the item changes in the data set unless the item itself is
     * invalidated or the new position cannot be determined. For this reason, you should only
     * use the `position` parameter while acquiring the related data item inside
     * this method and should not keep a copy of it. If you need the position of an item later
     * on (e.g. in a click listener), use [ViewHolder.getAdapterPosition] which will
     * have the updated adapter position.
     *
     * Override [.onBindViewHolder] instead if Adapter can
     * handle efficient partial bind.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     * item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    override fun onBindViewHolder(holder: ViewHolderMotorizacoes, position: Int) {
        cursor!!.moveToPosition(position)
        holder.motorizacao = Motorizacao.fromCursor(cursor!!)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        if (cursor == null) return 0

        return cursor!!.count
    }
}