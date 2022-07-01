package pt.ipg.projeto

import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQueryBuilder
import android.provider.BaseColumns

class TabelaBDModelo (db: SQLiteDatabase): TabelaBD(db, NOME){
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_MODELO TEXT NOT NULL, $CAMPO_PRECO , $CAMPO_MARCA_ID INTEGER NOT NULL, FOREIGN KEY($CAMPO_MARCA_ID) REFERENCES ${TabelaBDMarcas.NOME})")
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
        queryBuilder.tables = "$NOME INNER JOIN ${TabelaBDMarcas.NOME} ON ${TabelaBDMarcas.CAMPO_ID} = $CAMPO_MARCA_ID"

        return queryBuilder.query(db, columns, selection, selectionArgs, groupBy, having, orderBy)
    }


    companion object{
        const val NOME = "modelos"
        const val CAMPO_ID = "$NOME-${BaseColumns._ID}"
        const val CAMPO_MODELO = "nome"
        const val CAMPO_MARCA_ID = "marcaID"
        const val CAMPO_PRECO = "preco"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_MODELO, CAMPO_PRECO, TabelaBDMarcas.MARCAS)
    }
}