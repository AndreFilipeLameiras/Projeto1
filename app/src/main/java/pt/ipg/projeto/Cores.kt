package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Cores (
    var nome: String,
    var preco: Double,
    var idPintura: Long,
    var id: Long = -1
    ) {


    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDCores.CAMPO_COR, nome)
        valores.put(TabelaBDCores.PRECO, preco)
        valores.put(TabelaBDCores.CAMPO_PINTURA_ID, idPintura)


        return valores

    }

    companion object{
        fun fromCursor(cursor: Cursor) : Cores{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posNome = cursor.getColumnIndex(TabelaBDCores.CAMPO_COR)
            val posPreco = cursor.getColumnIndex(TabelaBDCores.PRECO)
            val posIdPint = cursor.getColumnIndex(TabelaBDCores.CAMPO_PINTURA_ID)

            val id = cursor.getLong(posId)
            val nome = cursor.getString(posNome)
            val preco = cursor.getDouble(posPreco)
            val idPintura = cursor.getLong(posIdPint)

            return Cores(nome, preco, idPintura, id)
        }
    }
}