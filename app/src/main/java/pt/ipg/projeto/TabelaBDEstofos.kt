package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDEstofos (db: SQLiteDatabase): TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $ESTOFOS TEXT NOT NULL, $PRECO DOUBLE NOT NULL)")
    }

    companion object{
        const val NOME = "estofos"
        const val ESTOFOS = "nome"
        const val PRECO = "preco"

        val TODAS_COLUNAS = arrayOf(BaseColumns._ID, TabelaBDEstofos.ESTOFOS, TabelaBDEstofos.PRECO)
    }

}