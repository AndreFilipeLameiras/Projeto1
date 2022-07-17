package pt.ipg.projeto

import android.content.ContentValues
import android.database.Cursor
import android.provider.BaseColumns
import java.io.Serializable

data class Carro (
    var modelo: Modelo,
    var motorizacao: Motorizacao,
    var cores: Cores,
    var estofos: Estofos,
    var jante: Jante,
    var id: Long
) : Serializable {
    fun toContentValues(): ContentValues{
        val valores = ContentValues()

        valores.put(TabelaBDCarros.CAMPO_MODELO_ID, modelo.id )
        valores.put(TabelaBDCarros.CAMPO_MOTORIZACAO_ID, motorizacao.id)
        valores.put(TabelaBDCarros.CAMPO_COR_ID, cores.id )
        valores.put(TabelaBDCarros.CAMPO_ESTOFOS_ID, estofos.id)
        valores.put(TabelaBDCarros.CAMPO_JANTES_ID, jante.id)

        return valores
    }


    companion object{
        fun fromCursor(cursor: Cursor):Carro{
            val posId = cursor.getColumnIndex(BaseColumns._ID)

            val posIdModelo = cursor.getColumnIndex(TabelaBDCarros.CAMPO_MODELO_ID)
            val posNomeModelo =  cursor.getColumnIndex(TabelaBDModelo.CAMPO_MODELO)
            val posPrecoModelo = cursor.getColumnIndex(TabelaBDModelo.CAMPO_PRECO)
            val posMarcaModelo = cursor.getColumnIndex(TabelaBDModelo.CAMPO_MARCA_ID)

            val posIdMotorizacao = cursor.getColumnIndex(TabelaBDCarros.CAMPO_MOTORIZACAO_ID)
            val posPotenciaMotorizacao =  cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_POTENCIA)
            val posConsumoMotorizacao = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_CONSUMO)
            val posEmissoesMotorizacao = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_EMISSOES)
            val posTransmissaoMotorizacao =  cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_TRANSMISSOES_ID)
            val posTracaoMotorizacao = cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_TRACAO_ID)
            val posCombustivelMotorizacao =  cursor.getColumnIndex(TabelaBDMotorizacoes.CAMPO_COMBUSTIVEL_ID)


            val posIdCor = cursor.getColumnIndex(TabelaBDCarros.CAMPO_COR_ID)
            val posNomeCor =  cursor.getColumnIndex(TabelaBDCores.CAMPO_COR)
            val posPrecoCor = cursor.getColumnIndex(TabelaBDCores.PRECO)

            val posIdEstofos = cursor.getColumnIndex(TabelaBDCarros.CAMPO_ESTOFOS_ID)
            val posNomeEstofo =  cursor.getColumnIndex(TabelaBDEstofos.ESTOFOS)
            val posPrecoEstofo = cursor.getColumnIndex(TabelaBDEstofos.PRECO)

            val posIdJantes = cursor.getColumnIndex(TabelaBDCarros.CAMPO_JANTES_ID)
            val posNomeJante =  cursor.getColumnIndex(TabelaBDJantes.CAMPO_NOME)
            val posLarguraJante = cursor.getColumnIndex(TabelaBDJantes.LARGURA)
            val posAlturaJante = cursor.getColumnIndex(TabelaBDJantes.ALTURA)
            val posRaioJante = cursor.getColumnIndex(TabelaBDJantes.RAIO)
            val posPrecoJante = cursor.getColumnIndex(TabelaBDJantes.PRECO)


            val id = cursor.getLong(posId)

            val idModelo = cursor.getLong(posIdModelo)
            val nomeModelo = cursor.getString(posNomeModelo)
            val precoModelo = cursor.getString(posPrecoModelo)
            val marcaModelo = cursor.getString(posMarcaModelo)
            val modelo = Modelo(nomeModelo, precoModelo.toDouble(), Marca("", marcaModelo.toLong()) ,idModelo)

            val idMotorizacao = cursor.getLong(posIdMotorizacao)
            val potenciaMotor = cursor.getString(posPotenciaMotorizacao)
            val consumoMotor = cursor.getString(posConsumoMotorizacao)
            val emissaoMotor = cursor.getString(posEmissoesMotorizacao)
            val transmissaoMotor = cursor.getString(posTransmissaoMotorizacao)
            val tracaoMotor = cursor.getString(posTracaoMotorizacao)
            val combustivelMotor = cursor.getString(posCombustivelMotorizacao)
             val motorizacao = Motorizacao(potenciaMotor.toLong(), consumoMotor.toDouble(), emissaoMotor.toDouble(), Transmissao("", transmissaoMotor.toLong()),
                Tracao("", tracaoMotor.toLong()) , Combustivel("" , combustivelMotor.toLong()), idMotorizacao)

            val idCor = cursor.getLong(posIdCor)
            val nomeCor = cursor.getString(posNomeCor)
            val precoCor = cursor.getString(posPrecoCor)
            val cor = Cores(nomeCor, precoCor.toDouble(), idCor )

            val idEstofos = cursor.getLong(posIdEstofos)
            val nomeEstofo = cursor.getString(posNomeEstofo)
            val precoEstofo = cursor.getString(posPrecoEstofo)
            val estofos = Estofos(nomeEstofo, precoEstofo.toDouble(), idEstofos)

            val idJantes = cursor.getLong(posIdJantes)
            val nomeJante = cursor.getString(posNomeJante)
            val larguraJante = cursor.getString(posLarguraJante)
            val alturaJante = cursor.getString(posAlturaJante)
            val raioJante = cursor.getString(posRaioJante)
            val precoJante = cursor.getString(posPrecoJante)
            val jante = Jante(nomeJante, larguraJante.toLong(), alturaJante.toLong(), raioJante.toLong(), precoJante.toDouble(), idJantes)

            return Carro(modelo, motorizacao, cor, estofos, jante, id )

        }
    }



}