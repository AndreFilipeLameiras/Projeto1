package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase

class TabelaBDEstofosEstilo(db: SQLiteDatabase): TabelaBD(db, NOME) {
    override fun cria() {

    }

    companion object{
        const val NOME = "estofosEstilo"

    }

}