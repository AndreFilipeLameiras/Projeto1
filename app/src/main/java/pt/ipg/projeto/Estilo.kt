package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Estilo (
    var nome: String,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDEstilo.CAMPO_NOME, nome)



        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor) : Estilo{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDEstilo.CAMPO_NOME)


            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)


            return Estilo(nome, id)
        }
    }

}