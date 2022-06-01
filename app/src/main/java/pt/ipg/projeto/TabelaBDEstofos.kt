package pt.ipg.projeto

import android.database.sqlite.SQLiteDatabase
import android.provider.BaseColumns

class TabelaBDEstofos (db: SQLiteDatabase): TabelaBD(db, NOME){
    override fun cria(){
        db.execSQL("CREATE TABLE $nome (${BaseColumns._ID} INTEGER PRIMARY KEY AUTOINCREMENT, $TIPO_ESTOFOS TEXT NOT NULL, $NOME_ESTOFOS TEXT NOT NULL)")
    }

    companion object{
        const val NOME = "estofos"
        const val TIPO_ESTOFOS = "tipo de estofos"
        const val NOME_ESTOFOS = "nome dos estofos"
    }

}