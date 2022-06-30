package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Estofos(
    var nome: String,
    var preco: Double,
    var id: Long = -1
) {


    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDEstofos.ESTOFOS, nome)
        valores.put(TabelaBDEstofos.PRECO, preco)

        return valores
    }

    companion object{
        fun fromCursor(cursor: Cursor) : Estofos{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDEstofos.ESTOFOS)
            val posPreco = cursor.getColumnIndex(TabelaBDEstofos.PRECO)


            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val preco = cursor.getDouble(posPreco)


            return Estofos(nome, preco, id)
        }
    }
}