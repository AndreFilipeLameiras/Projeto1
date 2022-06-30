package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDTransmissoes (db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {
       db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER KEY AUTOINCREMENT $CAMPO_NOME TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "transmissoes"
        const val CAMPO_NOME = "nome"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, CAMPO_NOME)
    }



}