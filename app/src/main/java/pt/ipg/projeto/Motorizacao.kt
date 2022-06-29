package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Motorizacao (
    var potencia: Long,
    var consumo: Double,
    var emissoes: Double,
    var idTransmissoes: Long,
    var idTracao: Long,
    var idCombustivel: Long,
    var id: Long = -1
){
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDMotorizacoes.CAMPO_POTENCIA, potencia)
        valores.put(TabelaBDMotorizacoes.CAMPO_TRANSMISSOES_ID, idTransmissoes)
        valores.put(TabelaBDMotorizacoes.CAMPO_TRACAO_ID, idTracao)
        valores.put(TabelaBDMotorizacoes.CAMPO_COMBUSTIVEL_ID, idCombustivel)
        valores.put(TabelaBDMotorizacoes.CAMPO_CONSUMO, consumo)
        valores.put(TabelaBDMotorizacoes.CAMPO_EMISSOES, emissoes)

        return valores

    }

    companion object {
        fun fromCursor(cursor: Cursor): Motorizacao {
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posPotencia = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_POTENCIA)
            val posConsumo = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_CONSUMO)
            val posEmissoes = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_EMISSOES)
            val posIdTransmissao = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_TRANSMISSOES_ID)
            val posIdTracao = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_TRACAO_ID)
            val posIdCombustivel = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_COMBUSTIVEL_ID)


            val id = cursor.getLong(posId)
            val potencia = cursor.getLong(posPotencia)
            val consumo = cursor.getDouble(posConsumo)
            val emissoes = cursor.getDouble(posEmissoes)
            val idTracao = cursor.getLong(posIdTracao)
            val idTransmissao = cursor.getLong(posIdTransmissao)
            val idCombustivel = cursor.getLong(posIdCombustivel)

            return Motorizacao(potencia, consumo, emissoes, idTracao, idTransmissao, idCombustivel, id)
        }
    }


}