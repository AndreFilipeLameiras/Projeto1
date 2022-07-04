package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Marca(
    var nome: String = "",
    var id: Long = -1
) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDMarcas.MARCAS, nome)

        return valores

    }

    companion object {
        fun fromCursor(cursor: Cursor): Marca {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDMarcas.MARCAS)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)

            return Marca(nome, id)
        }
    }

}