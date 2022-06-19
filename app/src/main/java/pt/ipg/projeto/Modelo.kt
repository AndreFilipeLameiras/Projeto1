package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Modelo(
    var modelo: String,
    var preco: Double,
    var idMarca: Long,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDModelo.CAMPO_MODELO, modelo)
        valores.put(TabelaBDModelo.CAMPO_PRECO, preco)
        valores.put(TabelaBDModelo.CAMPO_MARCA_ID, idMarca)

        return valores
    }


    companion object {
        fun fromCursor(cursor: Cursor): Modelo {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posModelo = cursor.getColumnIndex(TabelaBDModelo.CAMPO_MODELO)
            val posPreco = cursor.getColumnIndex(TabelaBDModelo.CAMPO_PRECO)
            val posIdMarc = cursor.getColumnIndex(TabelaBDModelo.CAMPO_MARCA_ID)

            val id = cursor.getLong(posId)
            val modelo = cursor.getString(posModelo)
            val preco = cursor.getDouble(posPreco)
            val idMarca = cursor.getLong(posIdMarc)

            return Modelo(modelo, preco, idMarca, id)
        }
    }
}