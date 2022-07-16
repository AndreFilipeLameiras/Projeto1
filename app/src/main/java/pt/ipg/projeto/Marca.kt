package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Marca(
    var nome: String = "",
    var id: Long = -1
) :Serializable{
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDMarcas.CAMPO_NOME, nome)

        return valores

    }

    companion object {
        fun fromCursor(cursor: Cursor): Marca {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDMarcas.CAMPO_NOME)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)

            return Marca(nome, id)
        }
    }

}