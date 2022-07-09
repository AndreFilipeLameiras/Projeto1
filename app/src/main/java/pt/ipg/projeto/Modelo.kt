package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Modelo(
    var nomeModelo: String,
    var preco: Double,
    var idMarca: Long,
    var nomeMarca: String?= null,
    var id: Long = -1


) : Serializable{
    fun toContentValues(): ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDModelo.CAMPO_MODELO, nomeModelo)
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
            val posNomeMarc =  cursor.getColumnIndex(TabelaBDMarcas.CAMPO_NOME)

            val id = cursor.getLong(posId)
            val modelo = cursor.getString(posModelo)
            val preco = cursor.getDouble(posPreco)

            val idMarca = cursor.getLong(posIdMarc)
            /*val nomeMarca = cursor.getString(posNomeMarc)
            val marca = Marca(nomeMarca, idMarca)
*/
            val nomeMarca = if (posNomeMarc != 1) cursor.getString(posNomeMarc) else null
            return Modelo(modelo, preco, idMarca,  nomeMarca, id )
        }
    }
}