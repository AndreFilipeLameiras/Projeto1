package pt.ipg.projeto

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDCarros(db: SQLiteDatabase) : TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_MODELO_ID TEXT NOT NULL, $CAMPO_MOTORIZACAO_ID TEXT NOT NULL, $CAMPO_COR_ID TEXT NOT NULL, $CAMPO_ESTOFOS_ID TEXT NOT NULL, $CAMPO_JANTES_ID TEXT NOT NULL, FOREIGN KEY ($CAMPO_MODELO_ID) REFERENCES ${TabelaBDModelo.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT, FOREIGN KEY ($CAMPO_MOTORIZACAO_ID) REFERENCES ${TabelaBDMotorizacoes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_COR_ID) REFERENCES ${TabelaBDCores.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_ESTOFOS_ID) REFERENCES ${TabelaBDEstofos.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT,FOREIGN KEY ($CAMPO_JANTES_ID) REFERENCES ${TabelaBDJantes.NOME}(${BaseColumns._ID}) ON DELETE RESTRICT ) ")
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
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDModelo.NOME} ON ${TabelaBDModelo.CAMPO_ID} = $CAMPO_MODELO_ID"
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDMotorizacoes.NOME} ON ${TabelaBDMotorizacoes.CAMPO_ID} = $CAMPO_MOTORIZACAO_ID"
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDCores.NOME} ON ${TabelaBDCores.CAMPO_ID} = $CAMPO_COR_ID"
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDEstofos.NOME} ON ${TabelaBDEstofos.CAMPO_ID} = $CAMPO_ESTOFOS_ID"
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDJantes.NOME} ON ${TabelaBDJantes.CAMPO_ID} = $CAMPO_JANTES_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }


    companion object{
        const val NOME = "carros"
        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"

        const val CAMPO_MODELO_ID = "modeloId"
        const val CAMPO_MOTORIZACAO_ID = "motorizacaoId"
        const val CAMPO_COR_ID = "corId"
        const val CAMPO_ESTOFOS_ID = "estofosId"
        const val CAMPO_JANTES_ID = "jantesId"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_MODELO_ID, TabelaBDModelo.CAMPO_MODELO,CAMPO_MOTORIZACAO_ID, TabelaBDMotorizacoes.CAMPO_POTENCIA, TabelaBDMotorizacoes.CAMPO_CONSUMO, CAMPO_COR_ID, TabelaBDCores.CAMPO_COR, CAMPO_ESTOFOS_ID,TabelaBDEstofos.ESTOFOS , CAMPO_JANTES_ID, TabelaBDJantes.CAMPO_NOME)
    }
}