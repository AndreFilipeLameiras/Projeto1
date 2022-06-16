package pt.ipg.projeto

import android.content.ContentValues

data class Marca(
    var nome: String,
    var id: Long = -1
) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDMarcas.MARCAS, nome)

        return valores

    }

}