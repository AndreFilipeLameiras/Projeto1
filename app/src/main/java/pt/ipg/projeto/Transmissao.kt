package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Transmissao (
    var nome: String,
    var id: Long = -1
        ) {

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDTransmissoes.CAMPO_NOME, nome)


        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor) : Transmissao{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDTransmissoes.CAMPO_NOME)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)

            return Transmissao(nome, id)
        }
    }
}