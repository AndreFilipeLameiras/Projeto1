package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDTransmissoes (db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {
       db.execSQL("CREATE TABLE $NOME (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $CAMPO_NOME TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "transmissoes"
        const val CAMPO_ID_TRANSMISSOES = "$NOME.${BaseColumns._ID}"
        const val CAMPO_NOME = "nome"

        val TODAS_COLUNAS = arrayOf(CAMPO_ID_TRANSMISSOES, CAMPO_NOME)
    }



}