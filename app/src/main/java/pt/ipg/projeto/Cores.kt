package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Cores(
    var nome: String,
    var preco: Double,
    var id: Long = -1
) : Serializable {


    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDCores.CAMPO_COR, nome)
        valores.put(TabelaBDCores.PRECO, preco)



        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor) : Cores{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDCores.CAMPO_COR)
            val posPreco = cursor.getColumnIndex(TabelaBDCores.PRECO)


            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val preco = cursor.getDouble(posPreco)


            return Cores(nome, preco, id)
        }
    }
}