package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase

class TabelaBDCoresPintura(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {

    }

    companion object{
        const val NOME = "coresPintura"

    }


}