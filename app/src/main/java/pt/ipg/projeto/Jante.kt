package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns

data class Jante (
    var nome: String,
    var largura: Long,
    var altura: Long,
    var raio: Long,
    var preco: Double,
    var id: Long = -1
    ){

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDJantes.JANTES, nome)
        valores.put(TabelaBDJantes.LARGURA, largura)
        valores.put(TabelaBDJantes.ALTURA, altura)
        valores.put(TabelaBDJantes.RAIO, raio)
        valores.put(TabelaBDJantes.PRECO, preco)

        return valores

    }


    companion object {
        fun fromCursor(cursor: Cursor): Jante{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posJantes = cursor.getColumnIndex(TabelaBDJantes.JANTES)
            val posLargura = cursor.getColumnIndex(TabelaBDJantes.LARGURA)
            val posAltura = cursor.getColumnIndex(TabelaBDJantes.ALTURA)
            val posRaio = cursor.getColumnIndex(TabelaBDJantes.RAIO)
            val posPreco = cursor.getColumnIndex(TabelaBDJantes.PRECO)

            var id = cursor.getLong(posId)
            var jantes = cursor.getString(posJantes)
            var largura = cursor.getLong(posLargura)
            var altura = cursor.getLong(posAltura)
            var raio = cursor.getLong(posRaio)
            var preco = cursor.getDouble(posPreco)


            return Jante(jantes, largura, altura, raio, preco, id)
        }
    }
}