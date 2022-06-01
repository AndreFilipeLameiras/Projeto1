package pt.ipg.projeto

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BDCarrosOpenHelper(
    context: Context?
) : SQLiteOpenHelper(context, NOME, null, VERSAO) {
    override fun onCreate(db: SQLiteDatabase?) {
        requireNotNull(db)

        TabelaBDEstofos(db).cria()
        TabelaBDMotorizacoes(db).cria()
        TabelaBDPinturas(db).cria()

    }

    override fun onUpgrade(db: SQLiteDatabase?, p1: Int, p2: Int) {

    }



    companion object{
        private const val NOME = "carros.db"
        private const val VERSAO = 1
    }
}