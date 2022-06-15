package pt.ipg.projeto

import android.content.ContentValues

data class Jante (
    var id: Long,
    var nome: String,
    var largura: Long,
    var altura: Long,
    var raio: Long,
    var preco: Double
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
}