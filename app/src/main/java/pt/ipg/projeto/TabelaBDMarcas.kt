package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDMarcas (db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL )")

    }


    companion object{
        const val NOME = "marcas"

        const val CAMPO_ID = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID, CAMPO_NOME)

    }
}