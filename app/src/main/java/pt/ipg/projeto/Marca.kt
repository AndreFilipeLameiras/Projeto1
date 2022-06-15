package pt.ipg.projeto

import android.content.ContentValues

data class Marca(
    var id: Long,
    var nome: String
) {
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDMarcas.MARCAS, nome)

        return valores

    }

}