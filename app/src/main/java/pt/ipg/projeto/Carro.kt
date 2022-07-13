package pt.ipg.projeto

import android.content.ContentValues

data class Carro (
    var idModelo: Long,
    var idMotorizacao: Long,
    var idCor: Long,
    var idEstofos: Long,
    var idJantes: Long,
    var id: Long
){
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCarros.CAMPO_MODELO_ID, idModelo )
        valores.put(TabelaBDCarros.CAMPO_MOTORIZACAO_ID, idMotorizacao )
        valores.put(TabelaBDCarros.CAMPO_COR_ID, idCor )
        valores.put(TabelaBDCarros.CAMPO_ESTOFOS_ID, idEstofos)
        valores.put(TabelaBDCarros.CAMPO_JANTES_ID, idJantes)

        return valores
    }



}