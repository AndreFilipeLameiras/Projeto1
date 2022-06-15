package pt.ipg.projeto

import android.content.ContentValues

data class Tracao (
    var id: Long,
    var nome: String
    ){

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDTracao.CAMPO_NOME, nome)


        return valores

    }
}