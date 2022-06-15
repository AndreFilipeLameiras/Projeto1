package pt.ipg.projeto

import android.content.ContentValues

data class Motorizacao (
    var id: Long,
    var potencia: Long,
    var idTransmissoes: Long,
    var idTracao: Long,
    var idCombustivel: Long,
    var consumo: Double,
    var emissoes: Double
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

}