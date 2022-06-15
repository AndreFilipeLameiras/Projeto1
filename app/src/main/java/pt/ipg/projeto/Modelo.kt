package pt.ipg.projeto

import android.content.ContentValues

data class Modelo(
    var id: Long,
    var modelo: String,
    var idMarca: Long,
    var preco: Double
) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues()
        valores.put(TabelaBDModelo.CAMPO_MODELO, modelo)
        valores.put(TabelaBDModelo.CAMPO_PRECO, preco)
        valores.put(TabelaBDModelo.CAMPO_MARCA_ID, idMarca)

        return valores
    }
}