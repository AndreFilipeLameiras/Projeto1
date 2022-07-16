package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

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

    companion object{
        fun fromCursor(cursor: Cursor):Carro{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posIdModelo = cursor.getColumnIndex(TabelaBDCarros.CAMPO_MODELO_ID)
            val posIdMotorizacao = cursor.getColumnIndex(TabelaBDCarros.CAMPO_MOTORIZACAO_ID)
            val posIdCor = cursor.getColumnIndex(TabelaBDCarros.CAMPO_COR_ID)
            val posIdEstofos = cursor.getColumnIndex(TabelaBDCarros.CAMPO_ESTOFOS_ID)
            val posIdJantes = cursor.getColumnIndex(TabelaBDCarros.CAMPO_JANTES_ID)

            val id = cursor.getLong(posId)
            val idModelo = cursor.getLong(posIdModelo)
            val idMotorizacao = cursor.getLong(posIdMotorizacao)
            val idCor = cursor.getLong(posIdCor)
            val idEstofos = cursor.getLong(posIdEstofos)
            val idJantes = cursor.getLong(posIdJantes)

            return Carro(idModelo, idMotorizacao, idCor, idEstofos, idJantes, id)

        }
    }



}