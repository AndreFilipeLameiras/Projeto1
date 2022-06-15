package pt.ipg.projeto

import android.content.ContentValues

data class Transmissao (
    var id: Long,
    var nome: String
        ) {

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDTransmissoes.CAMPO_NOME, nome)


        return valores

    }
}