package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Motorizacao (
    var potencia: Long,
    var consumo: Double,
    var emissoes: Double,
    var transmissao: Transmissao,
    var tracao: Tracao,
    var combustivel: Combustivel,
    var id: Long = -1
){
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDMotorizacoes.CAMPO_POTENCIA, potencia)
        valores.put(TabelaBDMotorizacoes.CAMPO_TRANSMISSOES_ID, transmissao.id)
        valores.put(TabelaBDMotorizacoes.CAMPO_TRACAO_ID, tracao.id)
        valores.put(TabelaBDMotorizacoes.CAMPO_COMBUSTIVEL_ID, combustivel.id)
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
            val posNomeTransmi =  cursor.getColumnIndex(TabelaBDTransmissoes.CAMPO_NOME)
            val posNomeTrac =  cursor.getColumnIndex(TabelaBDTracao.CAMPO_NOME)
            val posNomeComb =  cursor.getColumnIndex(TabelaBDCombustivel.CAMPO_NOME)


            val id = cursor.getLong(posId)
            val potencia = cursor.getLong(posPotencia)
            val consumo = cursor.getDouble(posConsumo)
            val emissoes = cursor.getDouble(posEmissoes)

            val idTransmissao = cursor.getLong(posIdTransmissao)
            val nomeTransm = cursor.getString(posNomeTransmi)
            val transmissao = Transmissao(nomeTransm, idTransmissao)

            val idTracao = cursor.getLong(posIdTracao)
            val nomeTracao = cursor.getString(posNomeTrac)
            val tracao = Tracao(nomeTracao, idTracao)

            val idCombustivel = cursor.getLong(posIdCombustivel)
            val nomeComb = cursor.getString(posNomeComb)
            val combustivel = Combustivel(nomeComb, idCombustivel)

            return Motorizacao(potencia, consumo, emissoes,transmissao , tracao , combustivel , id)
        }
    }


}