package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Jante (
    var nome: String = "",
    var largura: Long,
    var altura: Long,
    var raio: Long,
    var preco: Double,
    var id: Long = -1
    ): Serializable{

    fun toContentValues(): ContentValues {
        val valores = ContentValues()

        valores.put(TabelaBDJantes.CAMPO_NOME, nome)
        valores.put(TabelaBDJantes.LARGURA, largura)
        valores.put(TabelaBDJantes.ALTURA, altura)
        valores.put(TabelaBDJantes.RAIO, raio)
        valores.put(TabelaBDJantes.PRECO, preco)

        return valores

    }


    companion object {
        fun fromCursor(cursor: Cursor): Jante{
            val posId = cursor.getColumnIndex(BaseColumns._ID)
            val posJantes = cursor.getColumnIndex(TabelaBDJantes.CAMPO_NOME)
            val posLargura = cursor.getColumnIndex(TabelaBDJantes.LARGURA)
            val posAltura = cursor.getColumnIndex(TabelaBDJantes.ALTURA)
            val posRaio = cursor.getColumnIndex(TabelaBDJantes.RAIO)
            val posPreco = cursor.getColumnIndex(TabelaBDJantes.PRECO)

            val id = cursor.getLong(posId)
            val jantes = cursor.getString(posJantes)
            val largura = cursor.getLong(posLargura)
            val altura = cursor.getLong(posAltura)
            val raio = cursor.getLong(posRaio)
            val preco = cursor.getDouble(posPreco)


            return Jante(jantes, largura, altura, raio, preco, id)
        }
    }
}