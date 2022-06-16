package pt.ipg.projeto

import android.content.ContentValues

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
}