package pt.ipg.projeto

import android.database.Cursor
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdapterModelos(val fragment: ListaModelosfragment) : RecyclerView.Adapter<AdapterModelos.ViewHolderModelo>() {
    var cursor: Cursor? = null
        get() = field
        set(value) {
            if (field != value) {
                field = value
                notifyDataSetChanged()
            }
        }

    var viewHolderSelecionado : ViewHolderModelo? = null

    inner class ViewHolderModelo(itemModelo: View) : RecyclerView.ViewHolder(itemModelo), View.OnClickListener{
        val textViewNomeModelo = itemModelo.findViewById<TextView>(R.id.textViewNomeModelo)
        val textViewMarca = itemModelo.findViewById<TextView>(R.id.textViewMarca)
        val textViewPrecoModelo = itemModelo.findViewById<TextView>(R.id.textViewPrecoModelo)

        init {
            itemModelo.setOnClickListener(this)
        }



        var modelo : Modelo? = null
            get() = field
            set(value: Modelo?) {
                field = value

                textViewNomeModelo.text = modelo?.nomeModelo ?: ""
                textViewMarca.text = modelo?.nomeMarca?.nome ?: ""
                textViewPrecoModelo.text = modelo?.preco.toString()
            }

        override fun onClick(p0: View?) {
            viewHolderSelecionado?.desseleciona()
            seleciona()
        }

        private fun seleciona(){
            itemView.setBackgroundResource(android.R.color.holo_orange_light)
            viewHolderSelecionado = this
            fragment.modeloSeleccionado = modelo
        }

        private fun desseleciona(){
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderModelo {
        val itemModelo = fragment.layoutInflater.inflate(R.layout.item_modelo, parent, false)
        return ViewHolderModelo(itemModelo)

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
    override fun onBindViewHolder(holder: ViewHolderModelo, position: Int) {
        cursor!!.moveToPosition(position)
        holder.modelo = Modelo.fromCursor(cursor!!)
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        if(cursor == null) return 0

        return cursor!!.count
    }
}