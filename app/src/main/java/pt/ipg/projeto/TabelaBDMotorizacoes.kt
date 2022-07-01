package pt.ipg.projeto

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDMotorizacoes (db: SQLiteDatabase) : TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_POTENCIA TEXT NOT NULL, $CAMPO_TRANSMISSOES_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_TRANSMISSOES_ID) REFERENCES ${TabelaBDTransmissoes.NOME}, $CAMPO_TRACAO_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_TRACAO_ID) REFERENCES ${TabelaBDTracao.NOME}, $CAMPO_COMBUSTIVEL_ID INTEGER NOT NULL, FOREIGN KEY ($CAMPO_COMBUSTIVEL_ID) REFERENCES ${TabelaBDCombustivel.NOME}, $CAMPO_CONSUMO DOUBLE NOT NULL, $CAMPO_EMISSOES DOUBLE NOT NULL)")


    }

    override fun query(
        columns: Array<String>,
        selection: String?,
        selectionArgs: Array<String>?,
        groupBy: String?,
        having: String?,
        orderBy: String?
    ): Cursor {
        val queryBuilder = SQLiteQueryBuilder()
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDTransmissoes.NOME} ON ${TabelaBDTransmissoes.CAMPO_ID_TRANSMISSOES} = $CAMPO_TRANSMISSOES_ID"
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDTracao.NOME} ON ${TabelaBDTracao.CAMPO_ID_TRACOES} = $CAMPO_TRACAO_ID"
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDCombustivel.NOME} ON ${TabelaBDCombustivel.CAMPO_ID_COMBUSTIVEL} = $CAMPO_COMBUSTIVEL_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }

    companion object{
        const val NOME = "motorizacoes"

        const val CAMPO_ID_TRANSMISSOES = "$NOME.${BaseColumns._ID}"
        const val CAMPO_ID_TRACOES = "$NOME.${BaseColumns._ID}"
        const val CAMPO_ID_COMBUSTIVEL = "$NOME.${BaseColumns._ID}"
        const val CAMPO_POTENCIA = "potencia"
        const val CAMPO_CONSUMO = "consumo"
        const val CAMPO_EMISSOES = "emissoes"
        const val CAMPO_TRANSMISSOES_ID = "transmissoesId"
        const val CAMPO_TRACAO_ID = "tracaoId"
        const val CAMPO_COMBUSTIVEL_ID = "combustivelID"


        val TODAS_COLUNAS = arrayOf(CAMPO_ID_TRANSMISSOES, CAMPO_ID_TRACOES, CAMPO_ID_COMBUSTIVEL, CAMPO_POTENCIA, CAMPO_CONSUMO, CAMPO_EMISSOES, CAMPO_TRANSMISSOES_ID, CAMPO_TRACAO_ID,
            CAMPO_COMBUSTIVEL_ID)
    }
}