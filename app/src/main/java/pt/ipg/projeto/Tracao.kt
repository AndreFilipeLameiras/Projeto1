package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Tracao (
    var nome: String,
    var id: Long = -1
    ): Serializable {

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDTracao.CAMPO_NOME, nome)


        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor) : Tracao{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDTracao.CAMPO_NOME)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)

            return Tracao(nome, id)
        }
    }
}